package net.raccoon.will.viatora.core.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.raccoon.will.viatora.Viatora;
import net.raccoon.will.viatora.common.block.ColoredBeehiveBlock;

import java.util.function.Supplier;

public class VBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Viatora.MODID);

    public static final DeferredBlock<Block> RED_BEEHIVE = registerBlock("red_beehive", () -> new ColoredBeehiveBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEEHIVE).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> ORANGE_BEEHIVE = registerBlock("orange_beehive", () -> new ColoredBeehiveBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEEHIVE).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> YELLOW_BEEHIVE = registerBlock("yellow_beehive", () -> new ColoredBeehiveBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEEHIVE).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> LIME_BEEHIVE = registerBlock("lime_beehive", () -> new ColoredBeehiveBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEEHIVE).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> GREEN_BEEHIVE = registerBlock("green_beehive", () -> new ColoredBeehiveBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEEHIVE).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> CYAN_BEEHIVE = registerBlock("cyan_beehive", () -> new ColoredBeehiveBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEEHIVE).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> LIGHT_BLUE_BEEHIVE = registerBlock("light_blue_beehive", () -> new ColoredBeehiveBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEEHIVE).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> BLUE_BEEHIVE = registerBlock("blue_beehive", () -> new ColoredBeehiveBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEEHIVE).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> PURPLE_BEEHIVE = registerBlock("purple_beehive", () -> new ColoredBeehiveBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEEHIVE).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> MAGENTA_BEEHIVE = registerBlock("magenta_beehive", () -> new ColoredBeehiveBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEEHIVE).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> PINK_BEEHIVE = registerBlock("pink_beehive", () -> new ColoredBeehiveBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEEHIVE).sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> WHITE_BEEHIVE = registerBlock("white_beehive", () -> new ColoredBeehiveBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEEHIVE).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> LIGHT_GRAY_BEEHIVE = registerBlock("light_gray_beehive", () -> new ColoredBeehiveBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEEHIVE).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> GRAY_BEEHIVE = registerBlock("gray_beehive", () -> new ColoredBeehiveBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEEHIVE).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> BLACK_BEEHIVE = registerBlock("black_beehive", () -> new ColoredBeehiveBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEEHIVE).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> BROWN_BEEHIVE = registerBlock("brown_beehive", () -> new ColoredBeehiveBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEEHIVE).sound(SoundType.WOOD)));


    public static final DeferredBlock<Block> COLORED_BEEHIVE = registerBlock("colored_beehive",
            () -> new ColoredBeehiveBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEEHIVE).sound(SoundType.WOOD)));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block, boolean registerItem) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        if (registerItem) {
            VItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties()));
        }
        return toReturn;
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        return registerBlock(name, block, true);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

