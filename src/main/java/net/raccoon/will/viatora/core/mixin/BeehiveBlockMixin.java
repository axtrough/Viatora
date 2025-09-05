package net.raccoon.will.viatora.core.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.raccoon.will.viatora.common.block.ColoredBeehiveBlock;
import net.raccoon.will.viatora.common.block.ColoredBeehiveBlockEntity;
import net.raccoon.will.viatora.core.registry.VBlockEntities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BeehiveBlock.class)
public class BeehiveBlockMixin {

    @Unique
    public void viatora$transferData(Level level, BlockPos pos, BlockState state, BlockEntity oldBlockEntity, BlockEntity newBlockEntity) {
        CompoundTag nbt = oldBlockEntity.saveWithFullMetadata(level.registryAccess());
        newBlockEntity.loadCustomOnly(nbt, level.registryAccess());
    }

    @Inject(method = "useItemOn", at = @At("TAIL"), cancellable = true)
    public void onBottleUse(ItemStack itemStack, BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<ItemInteractionResult> cir) {
        ItemStack stack = player.getItemInHand(hand);
        BlockState vanillaState = Blocks.BEEHIVE.defaultBlockState()
                .setValue(BeehiveBlock.FACING, blockState.getValue(BeehiveBlock.FACING))
                .setValue(BeehiveBlock.HONEY_LEVEL, blockState.getValue(BeehiveBlock.HONEY_LEVEL));
        if (stack.is(Items.POTION) && blockState.getBlock() instanceof ColoredBeehiveBlock) {

            if (level.getBlockEntity(pos) instanceof ColoredBeehiveBlockEntity oldBeehive) {
                level.removeBlockEntity(pos);
                level.setBlock(pos, vanillaState, 3);

                BlockEntity newBeehive = BlockEntityType.BEEHIVE.create(pos, vanillaState);
                if (newBeehive != null) {
                    viatora$transferData(level, pos, blockState, oldBeehive, newBeehive);
                    level.setBlockEntity(newBeehive);

                    if (!player.isCreative()) {
                        stack.shrink(1);
                        player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
                    }

                    level.playSound(player, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.AMBIENT, 1F, 1F);
                    level.playSound(player, pos, SoundEvents.WOOD_PLACE, SoundSource.AMBIENT, 1F, 1F);
                    cir.setReturnValue(ItemInteractionResult.SUCCESS);
                }
            }
        }
    }

    @Inject(method = "useItemOn", at = @At("TAIL"), cancellable = true)
    public void onDyeUse(ItemStack itemStack, BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<ItemInteractionResult> cir) {
        ItemStack stack = player.getItemInHand(hand);
        if (!(stack.getItem() instanceof DyeItem dyeItem)) return;

        DyeColor dyeColor = dyeItem.getDyeColor();
        if (dyeColor == null) return;

        Block targetBlock = ColoredBeehiveBlock.getBlockbyColor(dyeColor);
        if (!(targetBlock instanceof ColoredBeehiveBlock)) return;

        if (blockState.getBlock() instanceof BeehiveBlock) {
            if (blockState.is(targetBlock)) {
                cir.setReturnValue(ItemInteractionResult.FAIL);
                return;
            }

            BlockState newState = targetBlock.defaultBlockState()
                    .setValue(BeehiveBlock.FACING, blockState.getValue(BeehiveBlock.FACING))
                    .setValue(BeehiveBlock.HONEY_LEVEL, blockState.getValue(BeehiveBlock.HONEY_LEVEL));

            if (level.getBlockEntity(pos) instanceof BeehiveBlockEntity oldBeehive) {
                level.removeBlockEntity(pos);
                level.setBlock(pos, newState, 3);

                BlockEntityType<ColoredBeehiveBlockEntity> type = VBlockEntities.BEEHIVE.get();
                BlockEntity newBeehive = type.create(pos, newState);

                if (newBeehive != null) {
                    viatora$transferData(level, pos, blockState, oldBeehive, newBeehive);
                    level.setBlockEntity(newBeehive);

                    if (!player.isCreative()) {
                        stack.shrink(1);
                    }

                    level.playSound(player, pos, SoundEvents.DYE_USE, SoundSource.AMBIENT, 1F, 1F);
                    level.playSound(player, pos, SoundEvents.WOOD_PLACE, SoundSource.AMBIENT, 1F, 1F);
                    cir.setReturnValue(ItemInteractionResult.SUCCESS);
                } else {
                    cir.setReturnValue(ItemInteractionResult.FAIL);
                }

            }
        }
    }
}