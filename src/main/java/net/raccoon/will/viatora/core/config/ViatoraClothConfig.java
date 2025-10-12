package net.raccoon.will.viatora.core.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
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

import java.util.function.Function;

import static net.raccoon.will.viatora.Viatora.config;


@Config(name = "viatora")
public class ViatoraClothConfig implements ConfigData {


    @ConfigEntry.Gui.Tooltip(count = 1)
    public boolean ThrowablesHit = true;

    @ConfigEntry.Gui.Tooltip(count = 1)
    @ConfigEntry.BoundedDiscrete(min = 1, max = 4)
    public int TrampleLevel = 1;

    @ConfigEntry.Gui.Tooltip(count = 1)
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public TrampleEnum TrampleOption = TrampleEnum.FEATHER_FALLING;

    public enum TrampleEnum {
        FEATHER_FALLING((entity) -> {
            ViatoraClothConfig config = AutoConfig.getConfigHolder(ViatoraClothConfig.class).getConfig();
            int level = config.TrampleLevel;
            if (entity instanceof LivingEntity livingEntity) {
                for (ItemStack itemStack : livingEntity.getArmorSlots()) {
                    if (itemStack.getItem() instanceof ArmorItem armorItem) {
                        if (armorItem.getEquipmentSlot() == EquipmentSlot.FEET) {
                            livingEntity.level().registryAccess();
                            HolderLookup.RegistryLookup<Enchantment> lookup =
                                    livingEntity.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
                            if (EnchantmentHelper.getItemEnchantmentLevel(lookup.getOrThrow(Enchantments.FEATHER_FALLING), itemStack)
                                    >= config.TrampleLevel) {
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

        public boolean apply(Entity entity) {
            return function.apply(entity);
        }
    }

}
