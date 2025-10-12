package net.raccoon.will.viatora.core.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
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
import net.raccoon.will.viatora.core.registry.VBlockEntities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BeehiveBlock.class)
public class BeehiveBlockMixin {

    @Unique
    private void viatora$transferData(Level level, BeehiveBlockEntity oldBE, BlockEntity newBE) {
        newBE.loadCustomOnly(oldBE.saveWithFullMetadata(level.registryAccess()), level.registryAccess());
    }

    @Unique
    private boolean viatora$replaceBlock(Level level, BlockPos pos, BlockState oldState, BlockState newState, BeehiveBlockEntity oldBE, BlockEntityType<?> type, Player player, ItemStack stack, SoundEvent sound) {
        level.removeBlockEntity(pos);
        level.setBlock(pos, newState, 3);
        BlockEntity newBE = type.create(pos, newState);

        if (newBE == null) return false;
        viatora$transferData(level, oldBE, newBE);
        level.setBlockEntity(newBE);

        if (!player.isCreative()) stack.shrink(1);

        level.playSound(player, pos, sound, SoundSource.AMBIENT, 1F, 1F);
        level.playSound(player, pos, SoundEvents.WOOD_PLACE, SoundSource.AMBIENT, 1F, 1F);
        return true;
    }

    @Inject(method = "useItemOn", at = @At("TAIL"), cancellable = true)
    public void onItemUse(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player,
                          InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<ItemInteractionResult> cir) {
        if (!(level.getBlockEntity(pos) instanceof BeehiveBlockEntity oldBE)) return;

        ItemStack stack = player.getItemInHand(hand);
        BlockState newState = state.setValue(BeehiveBlock.FACING, state.getValue(BeehiveBlock.FACING))
                .setValue(BeehiveBlock.HONEY_LEVEL, state.getValue(BeehiveBlock.HONEY_LEVEL));

        if (stack.is(Items.POTION) && state.getBlock() instanceof ColoredBeehiveBlock) {
            if (viatora$replaceBlock(level, pos, state, Blocks.BEEHIVE.defaultBlockState()
                            .setValue(BeehiveBlock.FACING, state.getValue(BeehiveBlock.FACING))
                            .setValue(BeehiveBlock.HONEY_LEVEL, state.getValue(BeehiveBlock.HONEY_LEVEL)),
                    oldBE, BlockEntityType.BEEHIVE, player, stack, SoundEvents.BOTTLE_EMPTY)) {
                if (!player.isCreative()) player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
                cir.setReturnValue(ItemInteractionResult.SUCCESS);
            }
            return;
        }

        if (stack.getItem() instanceof DyeItem dyeItem) {
            DyeColor color = dyeItem.getDyeColor();
            if (color == null) return;

            Block target = ColoredBeehiveBlock.getBlockbyColor(color);
            if (!(target instanceof ColoredBeehiveBlock) || state.is(target)) {
                if (state.is(target)) cir.setReturnValue(ItemInteractionResult.FAIL);
                return;
            }

            if (viatora$replaceBlock(level, pos, state, target.defaultBlockState()
                            .setValue(BeehiveBlock.FACING, state.getValue(BeehiveBlock.FACING))
                            .setValue(BeehiveBlock.HONEY_LEVEL, state.getValue(BeehiveBlock.HONEY_LEVEL)),
                    oldBE, VBlockEntities.BEEHIVE.get(), player, stack, SoundEvents.DYE_USE)) {
                cir.setReturnValue(ItemInteractionResult.SUCCESS);
            } else {
                cir.setReturnValue(ItemInteractionResult.FAIL);
            }
        }
    }
}