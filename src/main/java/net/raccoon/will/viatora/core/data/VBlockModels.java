package net.raccoon.will.viatora.core.data;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.raccoon.will.viatora.Viatora;

public class VBlockModels extends BlockModelProvider {
    public VBlockModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Viatora.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        createBeehiveModels("red");
        createBeehiveModels("orange");
        createBeehiveModels("yellow");
        createBeehiveModels("lime");
        createBeehiveModels("green");
        createBeehiveModels("cyan");
        createBeehiveModels("light_blue");
        createBeehiveModels("blue");
        createBeehiveModels("purple");
        createBeehiveModels("magenta");
        createBeehiveModels("pink");
        createBeehiveModels("white");
        createBeehiveModels("light_gray");
        createBeehiveModels("gray");
        createBeehiveModels("black");
        createBeehiveModels("brown");
    }

    private void createBeehiveModels(String colorName) {
        createBeehiveModel(colorName, false);
        
        createBeehiveModel(colorName, true);
    }

    private void createBeehiveModel(String colorName, boolean honeyFilled) {
        String modelName = colorName + "_beehive" + (honeyFilled ? "_honey" : "");
        String textureFront = "block/beehive/" + colorName + "_beehive_front" + (honeyFilled ? "_honey" : "");
        String textureSide = "block/beehive/" + colorName + "_beehive_side";
        String textureEnd = "block/beehive/" + colorName + "_beehive_end";

        this.withExistingParent(modelName, ResourceLocation.fromNamespaceAndPath("minecraft", "block/orientable"))
                .texture("front", modLoc(textureFront))
                .texture("side", modLoc(textureSide))
                .texture("top", modLoc(textureEnd))
                .texture("bottom", modLoc(textureEnd));
    }
}