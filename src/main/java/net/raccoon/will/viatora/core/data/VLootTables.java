package net.raccoon.will.viatora.core.data;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.CopyBlockState;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.raccoon.will.viatora.core.registry.VBlocks;

import java.util.Set;

public class VLootTables extends BlockLootSubProvider {
    public VLootTables(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() { //LootTables
        this.add(VBlocks.RED_BEEHIVE.get(), beehiveDrop(VBlocks.RED_BEEHIVE.get()));
        this.add(VBlocks.ORANGE_BEEHIVE.get(), beehiveDrop(VBlocks.ORANGE_BEEHIVE.get()));
        this.add(VBlocks.YELLOW_BEEHIVE.get(), beehiveDrop(VBlocks.YELLOW_BEEHIVE.get()));
        this.add(VBlocks.LIME_BEEHIVE.get(), beehiveDrop(VBlocks.LIME_BEEHIVE.get()));
        this.add(VBlocks.GREEN_BEEHIVE.get(), beehiveDrop(VBlocks.GREEN_BEEHIVE.get()));
        this.add(VBlocks.CYAN_BEEHIVE.get(), beehiveDrop(VBlocks.CYAN_BEEHIVE.get()));
        this.add(VBlocks.LIGHT_BLUE_BEEHIVE.get(), beehiveDrop(VBlocks.LIGHT_BLUE_BEEHIVE.get()));
        this.add(VBlocks.BLUE_BEEHIVE.get(), beehiveDrop(VBlocks.BLUE_BEEHIVE.get()));
        this.add(VBlocks.PURPLE_BEEHIVE.get(), beehiveDrop(VBlocks.PURPLE_BEEHIVE.get()));
        this.add(VBlocks.MAGENTA_BEEHIVE.get(), beehiveDrop(VBlocks.MAGENTA_BEEHIVE.get()));
        this.add(VBlocks.PINK_BEEHIVE.get(), beehiveDrop(VBlocks.PINK_BEEHIVE.get()));
        this.add(VBlocks.WHITE_BEEHIVE.get(), beehiveDrop(VBlocks.WHITE_BEEHIVE.get()));
        this.add(VBlocks.LIGHT_GRAY_BEEHIVE.get(), beehiveDrop(VBlocks.LIGHT_GRAY_BEEHIVE.get()));
        this.add(VBlocks.GRAY_BEEHIVE.get(), beehiveDrop(VBlocks.GRAY_BEEHIVE.get()));
        this.add(VBlocks.BLACK_BEEHIVE.get(), beehiveDrop(VBlocks.BLACK_BEEHIVE.get()));
        this.add(VBlocks.BROWN_BEEHIVE.get(), beehiveDrop(VBlocks.BROWN_BEEHIVE.get()));
        this.add(VBlocks.COLORED_BEEHIVE.get(), beehiveDrop(VBlocks.COLORED_BEEHIVE.get()));
    }

    //SPACING

    protected LootTable.Builder beehiveDrop(Block block) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(((LootPoolSingletonContainer.Builder<?>) LootItem.lootTableItem(block).when(this.hasSilkTouch()))
                        .apply(CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY)
                                .include(DataComponents.BEES)).apply(CopyBlockState.copyState(block)
                                .copy(BeehiveBlock.HONEY_LEVEL)).otherwise(LootItem.lootTableItem(block))));
    }

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
