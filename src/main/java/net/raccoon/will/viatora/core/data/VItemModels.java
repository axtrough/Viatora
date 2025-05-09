package net.raccoon.will.viatora.core.data;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
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
        blockItem(VBlocks.RED_BEEHIVE.get(), "red");
        blockItem(VBlocks.ORANGE_BEEHIVE.get(), "orange");
        blockItem(VBlocks.YELLOW_BEEHIVE.get(), "yellow");
        blockItem(VBlocks.LIME_BEEHIVE.get(), "lime");
        blockItem(VBlocks.GREEN_BEEHIVE.get(), "green");
        blockItem(VBlocks.CYAN_BEEHIVE.get(), "cyan");
        blockItem(VBlocks.LIGHT_BLUE_BEEHIVE.get(), "light_blue");
        blockItem(VBlocks.BLUE_BEEHIVE.get(), "blue");
        blockItem(VBlocks.PURPLE_BEEHIVE.get(), "purple");
        blockItem(VBlocks.MAGENTA_BEEHIVE.get(), "magenta");
        blockItem(VBlocks.PINK_BEEHIVE.get(), "pink");
        blockItem(VBlocks.WHITE_BEEHIVE.get(), "white");
        blockItem(VBlocks.LIGHT_GRAY_BEEHIVE.get(), "light_gray");
        blockItem(VBlocks.GRAY_BEEHIVE.get(), "gray");
        blockItem(VBlocks.BLACK_BEEHIVE.get(), "black");
        blockItem(VBlocks.BROWN_BEEHIVE.get(), "brown");

    }

    //SPACING

    private void blockItem(Block block, String colorName) {
        String modelPath = "block/beehive/" + colorName + "_beehive";
        withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath(), modLoc(modelPath));
    }
}
