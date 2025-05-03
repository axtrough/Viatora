package net.raccoon.will.viatora.core.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.raccoon.will.viatora.Viatora;

import java.util.function.Supplier;

public class VBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Viatora.MODID);

    public static final DeferredBlock<Block> RED_BEEHIVE = registerBlock("red_beehive", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BEEHIVE).sound(SoundType.WOOD)));



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

    public static void register(IEventBus eventBus) {BLOCKS.register(eventBus);
    }
}

