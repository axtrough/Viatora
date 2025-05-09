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
        beehiveBlock(VBlocks.RED_BEEHIVE, "red");
        beehiveBlock(VBlocks.ORANGE_BEEHIVE, "orange");
        beehiveBlock(VBlocks.YELLOW_BEEHIVE, "yellow");
        beehiveBlock(VBlocks.LIME_BEEHIVE, "lime");
        beehiveBlock(VBlocks.GREEN_BEEHIVE, "green");
        beehiveBlock(VBlocks.CYAN_BEEHIVE, "cyan");
        beehiveBlock(VBlocks.LIGHT_BLUE_BEEHIVE, "light_blue");
        beehiveBlock(VBlocks.BLUE_BEEHIVE, "blue");
        beehiveBlock(VBlocks.PURPLE_BEEHIVE, "purple");
        beehiveBlock(VBlocks.MAGENTA_BEEHIVE, "magenta");
        beehiveBlock(VBlocks.PINK_BEEHIVE, "pink");
        beehiveBlock(VBlocks.WHITE_BEEHIVE, "white");
        beehiveBlock(VBlocks.LIGHT_GRAY_BEEHIVE, "light_gray");
        beehiveBlock(VBlocks.GRAY_BEEHIVE, "gray");
        beehiveBlock(VBlocks.BLACK_BEEHIVE, "black");
        beehiveBlock(VBlocks.BROWN_BEEHIVE, "brown");
    }

    private void beehiveBlock(DeferredBlock<Block> block, String colorName) {
        String baseModelName = colorName + "_beehive";
        String honeyModelName = colorName + "_beehive_honey";

        // Get model references
        ModelFile baseModel = models().getExistingFile(modLoc("block/beehive/" + baseModelName));
        ModelFile honeyModel = models().getExistingFile(modLoc("block/beehive/" + honeyModelName));

        VariantBlockStateBuilder builder = getVariantBuilder(block.get());

        for (Direction direction : List.of(Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST)) {
            int yRot = (int) direction.toYRot();

            for (int honeyLevel = 0; honeyLevel <= 5; honeyLevel++) {
                ModelFile model = honeyLevel == 5 ? honeyModel : baseModel;

                builder.partialState()
                        .with(HorizontalDirectionalBlock.FACING, direction)
                        .with(BlockStateProperties.LEVEL_HONEY, honeyLevel)
                        .modelForState()
                        .modelFile(model)
                        .rotationY(yRot)
                        .addModel();
            }
        }
    }



    //SPACING

    private void blockWithItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
