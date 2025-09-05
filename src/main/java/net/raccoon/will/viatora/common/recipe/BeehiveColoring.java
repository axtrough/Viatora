package net.raccoon.will.viatora.common.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.raccoon.will.viatora.common.block.ColoredBeehiveBlock;
import net.raccoon.will.viatora.core.registry.VRecipeSerializer;

public class BeehiveColoring extends CustomRecipe {
    public BeehiveColoring(CraftingBookCategory category) {
        super(category);
    }

    public boolean matches(CraftingInput input, Level level) {
        int i = 0;
        int j = 0;

        for (int k = 0; k < input.size(); k++) {
            ItemStack itemstack = input.getItem(k);
            if (!itemstack.isEmpty()) {
                if (Block.byItem(itemstack.getItem()) instanceof BeehiveBlock) {
                    i++;
                } else {
                    if (!itemstack.is(Tags.Items.DYES)) {
                        return false;
                    }

                    j++;
                }

                if (j > 1 || i > 1) {
                    return false;
                }
            }
        }

        return i == 1 && j == 1;
    }

    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack itemstack = ItemStack.EMPTY;
        DyeColor dyecolor = DyeColor.WHITE;

        for (int i = 0; i < input.size(); i++) {
            ItemStack itemstack1 = input.getItem(i);
            if (!itemstack1.isEmpty()) {
                Item item = itemstack1.getItem();
                if (Block.byItem(item) instanceof BeehiveBlock) {
                    itemstack = itemstack1;
                } else {
                    DyeColor tmp = DyeColor.getColor(itemstack1);
                    if (tmp != null) dyecolor = tmp;
                }
            }
        }

        Block block = ColoredBeehiveBlock.getBlockbyColor(dyecolor);
        return itemstack.transmuteCopy(block, 1);
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return VRecipeSerializer.BEEHIVE_COLORING.get();
    }
}

