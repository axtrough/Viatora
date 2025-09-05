package net.raccoon.will.viatora.core.data;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.raccoon.will.viatora.Viatora;
import net.raccoon.will.viatora.core.registry.VBlocks;

public class VItemModels extends ItemModelProvider {
    public VItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Viatora.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() { //ItemModels
        beehiveItem(VBlocks.RED_BEEHIVE.get(), "red");
        beehiveItem(VBlocks.ORANGE_BEEHIVE.get(), "orange");
        beehiveItem(VBlocks.YELLOW_BEEHIVE.get(), "yellow");
        beehiveItem(VBlocks.LIME_BEEHIVE.get(), "lime");
        beehiveItem(VBlocks.GREEN_BEEHIVE.get(), "green");
        beehiveItem(VBlocks.CYAN_BEEHIVE.get(), "cyan");
        beehiveItem(VBlocks.LIGHT_BLUE_BEEHIVE.get(), "light_blue");
        beehiveItem(VBlocks.BLUE_BEEHIVE.get(), "blue");
        beehiveItem(VBlocks.PURPLE_BEEHIVE.get(), "purple");
        beehiveItem(VBlocks.MAGENTA_BEEHIVE.get(), "magenta");
        beehiveItem(VBlocks.PINK_BEEHIVE.get(), "pink");
        beehiveItem(VBlocks.WHITE_BEEHIVE.get(), "white");
        beehiveItem(VBlocks.LIGHT_GRAY_BEEHIVE.get(), "light_gray");
        beehiveItem(VBlocks.GRAY_BEEHIVE.get(), "gray");
        beehiveItem(VBlocks.BLACK_BEEHIVE.get(), "black");
        beehiveItem(VBlocks.BROWN_BEEHIVE.get(), "brown");

    }

    //SPACING

    private void beehiveItem(Block block, String colorName) {
        String modelPath = "block/beehive/" + colorName + "_beehive";
        withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath(), modLoc(modelPath));
    }
}