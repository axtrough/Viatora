package net.raccoon.will.viatora.core.data;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.raccoon.will.viatora.Viatora;
import net.raccoon.will.viatora.core.registry.VBlocks;

public class VBlockStates extends BlockStateProvider {
    public VBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Viatora.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() { //BlockStates

        blockWithItem(VBlocks.RED_BEEHIVE);

    }

    //SPACING

    private void blockWithItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
