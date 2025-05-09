package net.raccoon.will.viatora.core.mixins.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.raccoon.will.viatora.common.block.ColoredBeehiveBlock;
import net.raccoon.will.viatora.core.registry.VBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BeehiveBlock.class)
public class BeehiveBlockMixin {


    @Inject(method = "useItemOn", at = @At("TAIL"), cancellable = true)
    public void onDyeUse(ItemStack itemStack, BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<ItemInteractionResult> cir) {
        ItemStack stack = player.getItemInHand(hand);

        if (!(stack.getItem() instanceof DyeItem dyeItem)) return;
        player.playSound(SoundEvents.DYE_USE);

        DyeColor dyeColor = dyeItem.getDyeColor();

        if (dyeColor == null) return;

        BlockState newState = switch (dyeColor) {
            case RED -> VBlocks.RED_BEEHIVE.get().defaultBlockState();
            case ORANGE -> VBlocks.ORANGE_BEEHIVE.get().defaultBlockState();
            case YELLOW -> VBlocks.YELLOW_BEEHIVE.get().defaultBlockState();
            case LIME -> VBlocks.LIME_BEEHIVE.get().defaultBlockState();
            case GREEN -> VBlocks.GREEN_BEEHIVE.get().defaultBlockState();
            case CYAN -> VBlocks.CYAN_BEEHIVE.get().defaultBlockState();
            case LIGHT_BLUE -> VBlocks.LIGHT_BLUE_BEEHIVE.get().defaultBlockState();
            case BLUE -> VBlocks.BLUE_BEEHIVE.get().defaultBlockState();
            case PURPLE -> VBlocks.PURPLE_BEEHIVE.get().defaultBlockState();
            case MAGENTA -> VBlocks.MAGENTA_BEEHIVE.get().defaultBlockState();
            case PINK -> VBlocks.PINK_BEEHIVE.get().defaultBlockState();
            case WHITE -> VBlocks.WHITE_BEEHIVE.get().defaultBlockState();
            case LIGHT_GRAY -> VBlocks.LIGHT_GRAY_BEEHIVE.get().defaultBlockState();
            case GRAY -> VBlocks.GRAY_BEEHIVE.get().defaultBlockState();
            case BLACK -> VBlocks.BLACK_BEEHIVE.get().defaultBlockState();
            case BROWN -> VBlocks.BROWN_BEEHIVE.get().defaultBlockState();
            default -> null;
        };

        if (newState != null) {
            boolean isVanillaBeehive = !(blockState.getBlock() instanceof ColoredBeehiveBlock);

            if (isVanillaBeehive) {
                newState = newState
                        .setValue(BeehiveBlock.FACING, blockState.getValue(BeehiveBlock.FACING).getOpposite())
                    .setValue(BeehiveBlock.HONEY_LEVEL, blockState.getValue(BeehiveBlock.HONEY_LEVEL));
            } else {
                newState = newState
                    .setValue(BeehiveBlock.FACING, blockState.getValue(BeehiveBlock.FACING))
                    .setValue(BeehiveBlock.HONEY_LEVEL, blockState.getValue(BeehiveBlock.HONEY_LEVEL));
            }

            level.setBlockAndUpdate(pos, newState);
            if (!player.isCreative()) stack.shrink(1);
            cir.setReturnValue(ItemInteractionResult.SUCCESS);
        } else {
            cir.setReturnValue(ItemInteractionResult.FAIL);
        }
    }
}
