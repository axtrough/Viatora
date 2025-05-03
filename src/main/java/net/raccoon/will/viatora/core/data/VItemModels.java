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

    }

    //SPACING

    public ItemModelBuilder complexBlock(Block block) {
        return withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath(),
                ResourceLocation.fromNamespaceAndPath("viatora", "block/"));
    }

    public ItemModelBuilder complexItem(Item item) {
        return withExistingParent(BuiltInRegistries.ITEM.getKey(item).getPath(),
                ResourceLocation.fromNamespaceAndPath("viatora", "item/"));
    }
}
