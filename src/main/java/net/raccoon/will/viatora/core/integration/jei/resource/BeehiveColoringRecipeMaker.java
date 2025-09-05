package net.raccoon.will.viatora.core.integration.jei.resource;

import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.raccoon.will.viatora.Viatora;
import net.raccoon.will.viatora.common.block.ColoredBeehiveBlock;

import java.util.Arrays;
import java.util.List;

public class BeehiveColoringRecipeMaker {
    private static final String path = ".beehivecoloring";


    public static List<RecipeHolder<CraftingRecipe>> createRecipes(RecipeCategory misc) {
        Ingredient baseBeehiveIngredient = Ingredient.of(Blocks.BEEHIVE);
        return Arrays.stream(DyeColor.values())
                .map(color -> createRecipe(color, baseBeehiveIngredient))
                .toList();
    }

    private static RecipeHolder<CraftingRecipe> createRecipe(DyeColor color, Ingredient baseBeehiveIngredient) {
        Ingredient colorIngredient = Ingredient.of(DyeItem.byColor(color));
        NonNullList<Ingredient> inputs = NonNullList.of(Ingredient.EMPTY, baseBeehiveIngredient, colorIngredient);
        Block coloredBeehiveBox = ColoredBeehiveBlock.getBlockbyColor(color);
        ItemStack output = new ItemStack(coloredBeehiveBox);
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Viatora.MODID, path + "." + output.getDescriptionId());
        CraftingRecipe recipe = new ShapelessRecipe(path, CraftingBookCategory.MISC, output, inputs);
        return new RecipeHolder<>(id, recipe);
    }

    private BeehiveColoringRecipeMaker() {
    }
}