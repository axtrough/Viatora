package net.raccoon.will.viatora.core.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.raccoon.will.viatora.Viatora;
import net.raccoon.will.viatora.common.recipe.BeehiveColoring;

import java.util.function.Supplier;

public class VRecipeSerializer {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, Viatora.MODID);

    public static final Supplier<SimpleCraftingRecipeSerializer<?>> BEEHIVE_COLORING =
            RECIPE_SERIALIZERS.register("beehive_coloring", () -> new SimpleCraftingRecipeSerializer<>(BeehiveColoring::new));


    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
    }
}