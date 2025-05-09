package net.raccoon.will.viatora;

import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.raccoon.will.viatora.core.registry.VBlockEntities;
import net.raccoon.will.viatora.core.registry.VBlocks;
import net.raccoon.will.viatora.core.registry.VItems;
import net.raccoon.will.viatora.core.registry.ViatoraPoi;
import org.slf4j.Logger;

@Mod(Viatora.MODID)
public class Viatora {
    public static final String MODID = "viatora";
    private static final Logger LOGGER = LogUtils.getLogger();


    public Viatora(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);
        modContainer.registerConfig(ModConfig.Type.COMMON, ViatoraConfig.SPEC);

        VItems.register(modEventBus);
        VBlocks.register(modEventBus);
        VBlockEntities.register(modEventBus);
        ViatoraPoi.POI_TYPES.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }

    @SubscribeEvent
    public void onFarmlandTrample(BlockEvent.FarmlandTrampleEvent event) {
        if (ViatoraConfig.TYPE.get().getFunction().apply(event.getEntity())) {
            event.setCanceled(true);
        }
    }
}
