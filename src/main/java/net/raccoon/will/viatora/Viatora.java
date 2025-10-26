package net.raccoon.will.viatora;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.raccoon.will.viatora.core.config.CommonConfig;
import net.raccoon.will.viatora.core.config.ViatoraClothConfig;
import net.raccoon.will.viatora.core.misc.VCreativeTab;
import net.raccoon.will.viatora.core.misc.ViatoraPoi;
import net.raccoon.will.viatora.core.registry.*;


@Mod(Viatora.MODID)
public class Viatora {
    public static final String MODID = "viatora";
    public static ViatoraClothConfig config;


    public Viatora(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);

        //Busses 🚌
        VItems.register(modEventBus);
        VBlocks.register(modEventBus);
        VEntities.register(modEventBus);
        VCreativeTab.register(modEventBus);
        VBlockEntities.register(modEventBus);
        ViatoraPoi.register(modEventBus);
        VRecipeSerializer.register(modEventBus);

        //Config
        if (!ModList.get().isLoaded("cloth_config")) {
            modContainer.registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC);
        } else {
        AutoConfig.register(ViatoraClothConfig.class, GsonConfigSerializer::new);
        modContainer.registerExtensionPoint(IConfigScreenFactory.class,
                (mc, parent) -> AutoConfig.getConfigScreen(ViatoraClothConfig.class, parent).get());
        }
    }


    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @SubscribeEvent
    public void onFarmlandTrample(BlockEvent.FarmlandTrampleEvent event) {
        ViatoraClothConfig config = AutoConfig.getConfigHolder(ViatoraClothConfig.class).getConfig();
        if (config.TrampleOption.apply(event.getEntity()) || CommonConfig.TYPE.get().getFunction().apply(event.getEntity())) {
            event.setCanceled(true);
        }
    }

    @EventBusSubscriber(modid = Viatora.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}