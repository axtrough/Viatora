package net.raccoon.will.viatora.core.data;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.VariantBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.raccoon.will.viatora.Viatora;
import net.raccoon.will.viatora.core.registry.VBlocks;

import java.util.List;

public class VBlockStates extends BlockStateProvider {

    public VBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Viatora.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
    }



    //SPACING

    private void blockWithItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
