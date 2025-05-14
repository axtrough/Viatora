package net.raccoon.will.viatora.core.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.raccoon.will.viatora.Viatora;

import java.util.function.Supplier;

public class VCreativeTab {
    public static DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Viatora.MODID);

    public static final Supplier<CreativeModeTab> VIATORA_TAB = CREATIVE_MODE_TAB.register("viatora_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(VBlocks.RED_BEEHIVE.get()))
                    .title(Component.translatable("creativetab.viatora.viatora_tab"))
                    .displayItems((parameters, output) -> {
                        output.accept(VBlocks.WHITE_BEEHIVE);
                        output.accept(VBlocks.LIGHT_GRAY_BEEHIVE);
                        output.accept(VBlocks.GRAY_BEEHIVE);
                        output.accept(VBlocks.BLACK_BEEHIVE);
                        output.accept(VBlocks.BROWN_BEEHIVE);

                        output.accept(VBlocks.RED_BEEHIVE);
                        output.accept(VBlocks.ORANGE_BEEHIVE);
                        output.accept(VBlocks.YELLOW_BEEHIVE);
                        output.accept(VBlocks.LIME_BEEHIVE);
                        output.accept(VBlocks.GREEN_BEEHIVE);
                        output.accept(VBlocks.CYAN_BEEHIVE);
                        output.accept(VBlocks.LIGHT_BLUE_BEEHIVE);
                        output.accept(VBlocks.BLUE_BEEHIVE);
                        output.accept(VBlocks.PURPLE_BEEHIVE);
                        output.accept(VBlocks.MAGENTA_BEEHIVE);
                        output.accept(VBlocks.PINK_BEEHIVE);


                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
