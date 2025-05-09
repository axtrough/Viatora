package net.raccoon.will.viatora.common.enums;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.raccoon.will.viatora.ViatoraConfig;

import java.util.function.Function;

@SuppressWarnings("deprecation")
public enum TrampleEnum {
    FEATHER_FALLING((entity) -> {
        if (entity instanceof LivingEntity livingEntity) {
            for (ItemStack itemStack : livingEntity.getArmorSlots()) {
                if (itemStack.getItem() instanceof ArmorItem armorItem) {
                    if(armorItem.getEquipmentSlot() == EquipmentSlot.FEET) {
                        livingEntity.level().registryAccess();
                        HolderLookup.RegistryLookup<Enchantment> lookup =
                                livingEntity.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
                        if (EnchantmentHelper.getItemEnchantmentLevel(lookup.getOrThrow(Enchantments.FEATHER_FALLING), itemStack)
                                >= ViatoraConfig.FEATHER_FALLING_LEVEL.get()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }),
    NEVER((entity) -> true),
    ALWAYS((entity) -> false);

    private final Function<Entity, Boolean> function;

    TrampleEnum(Function<Entity, Boolean> function) {
        this.function = function;
    }

    public Function<Entity, Boolean> getFunction() {
        return function;
    }
}