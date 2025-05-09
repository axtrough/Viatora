package net.raccoon.will.viatora.core.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.raccoon.will.viatora.Viatora;
import net.raccoon.will.viatora.common.block.ColoredBeehiveBlockEntity;

import java.util.function.Supplier;

public class VBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Viatora.MODID);

    public static final Supplier<BlockEntityType<ColoredBeehiveBlockEntity>> COLORED_BEEHIVE_BE = BLOCK_ENTITIES.register("colored_beehive_be",
            () -> BlockEntityType.Builder.of(ColoredBeehiveBlockEntity::new,
                    VBlocks.RED_BEEHIVE.get(),
                    VBlocks.ORANGE_BEEHIVE.get(),
                    VBlocks.YELLOW_BEEHIVE.get(),
                    VBlocks.LIME_BEEHIVE.get(),
                    VBlocks.GREEN_BEEHIVE.get(),
                    VBlocks.CYAN_BEEHIVE.get(),
                    VBlocks.LIGHT_BLUE_BEEHIVE.get(),
                    VBlocks.BLUE_BEEHIVE.get(),
                    VBlocks.PURPLE_BEEHIVE.get(),
                    VBlocks.MAGENTA_BEEHIVE.get(),
                    VBlocks.PINK_BEEHIVE.get(),
                    VBlocks.WHITE_BEEHIVE.get(),
                    VBlocks.LIGHT_GRAY_BEEHIVE.get(),
                    VBlocks.GRAY_BEEHIVE.get(),
                    VBlocks.BLACK_BEEHIVE.get(),
                    VBlocks.BROWN_BEEHIVE.get(),

                    VBlocks.COLORED_BEEHIVE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
