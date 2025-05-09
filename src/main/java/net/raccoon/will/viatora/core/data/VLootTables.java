package net.raccoon.will.viatora.core.data;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.raccoon.will.viatora.core.registry.VBlocks;

import java.util.Set;

public class VLootTables extends BlockLootSubProvider {
    public VLootTables(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() { //LootTables
        this.dropSelf(VBlocks.RED_BEEHIVE.get());
        this.dropSelf(VBlocks.ORANGE_BEEHIVE.get());
        this.dropSelf(VBlocks.YELLOW_BEEHIVE.get());
        this.dropSelf(VBlocks.LIME_BEEHIVE.get());
        this.dropSelf(VBlocks.GREEN_BEEHIVE.get());
        this.dropSelf(VBlocks.CYAN_BEEHIVE.get());
        this.dropSelf(VBlocks.LIGHT_BLUE_BEEHIVE.get());
        this.dropSelf(VBlocks.BLUE_BEEHIVE.get());
        this.dropSelf(VBlocks.PURPLE_BEEHIVE.get());
        this.dropSelf(VBlocks.MAGENTA_BEEHIVE.get());
        this.dropSelf(VBlocks.PINK_BEEHIVE.get());
        this.dropSelf(VBlocks.WHITE_BEEHIVE.get());
        this.dropSelf(VBlocks.LIGHT_GRAY_BEEHIVE.get());
        this.dropSelf(VBlocks.GRAY_BEEHIVE.get());
        this.dropSelf(VBlocks.BLACK_BEEHIVE.get());
        this.dropSelf(VBlocks.BROWN_BEEHIVE.get());
        this.dropSelf(VBlocks.COLORED_BEEHIVE.get());
    }

    //SPACING

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock,
                LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }
        @Override
    protected Iterable<Block> getKnownBlocks() {
        return VBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
