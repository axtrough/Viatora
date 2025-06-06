package net.raccoon.will.viatora.core.mixins;

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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.raccoon.will.viatora.common.block.ColoredBeehiveBlock;
import net.raccoon.will.viatora.common.block.ColoredBeehiveBlockEntity;
import net.raccoon.will.viatora.registry.VBlockEntities;
import net.raccoon.will.viatora.registry.VBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BeehiveBlock.class)
public class BeehiveBlockMixin {

    @Inject(method = "useItemOn", at = @At("TAIL"), cancellable = true)
    public void onDyeUse(ItemStack itemStack, BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<ItemInteractionResult> cir) {
        ItemStack stack = player.getItemInHand(hand);

        if (stack.is(Items.POTION) && blockState.getBlock() instanceof ColoredBeehiveBlock) {
            BlockState vanillaState = Blocks.BEEHIVE.defaultBlockState()
                    .setValue(BeehiveBlock.FACING, blockState.getValue(BeehiveBlock.FACING))
                    .setValue(BeehiveBlock.HONEY_LEVEL, blockState.getValue(BeehiveBlock.HONEY_LEVEL));

            if (level.getBlockEntity(pos) instanceof BeehiveBlockEntity coloredBeehive) {
                CompoundTag nbt = coloredBeehive.saveWithFullMetadata(level.registryAccess());

                level.removeBlockEntity(pos);
                level.setBlock(pos, vanillaState, 3);

                BlockEntity newBeehive = BlockEntityType.BEEHIVE.create(pos, vanillaState);
                if (newBeehive != null) {
                    newBeehive.loadCustomOnly(nbt, level.registryAccess());
                    level.setBlockEntity(newBeehive);
                }

                if (!player.isCreative()) {
                    stack.shrink(1);
                    player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
                }

                level.playSound(player, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.AMBIENT, 1F, 1F);
                level.playSound(player, pos, SoundEvents.WOOD_PLACE, SoundSource.AMBIENT, 1F, 1F);
                cir.setReturnValue(ItemInteractionResult.SUCCESS);
            }
            return;
        }

        if (!(stack.getItem() instanceof DyeItem dyeItem)) return;

        DyeColor dyeColor = dyeItem.getDyeColor();
        if (dyeColor == null) return;

        if (blockState.getBlock() instanceof ColoredBeehiveBlock currentBlock) {
            boolean isSameColor = switch (dyeColor) {
                case RED -> currentBlock == VBlocks.RED_BEEHIVE.get();
                case ORANGE -> currentBlock == VBlocks.ORANGE_BEEHIVE.get();
                case YELLOW -> currentBlock == VBlocks.YELLOW_BEEHIVE.get();
                case LIME -> currentBlock == VBlocks.LIME_BEEHIVE.get();
                case GREEN -> currentBlock == VBlocks.GREEN_BEEHIVE.get();
                case CYAN -> currentBlock == VBlocks.CYAN_BEEHIVE.get();
                case LIGHT_BLUE -> currentBlock == VBlocks.LIGHT_BLUE_BEEHIVE.get();
                case BLUE -> currentBlock == VBlocks.BLUE_BEEHIVE.get();
                case PURPLE -> currentBlock == VBlocks.PURPLE_BEEHIVE.get();
                case MAGENTA -> currentBlock == VBlocks.MAGENTA_BEEHIVE.get();
                case PINK -> currentBlock == VBlocks.PINK_BEEHIVE.get();
                case WHITE -> currentBlock == VBlocks.WHITE_BEEHIVE.get();
                case LIGHT_GRAY -> currentBlock == VBlocks.LIGHT_GRAY_BEEHIVE.get();
                case GRAY -> currentBlock == VBlocks.GRAY_BEEHIVE.get();
                case BLACK -> currentBlock == VBlocks.BLACK_BEEHIVE.get();
                case BROWN -> currentBlock == VBlocks.BROWN_BEEHIVE.get();
            };
            //no more consuming dye for no reason. the beehives are hungy
            if (isSameColor) {
                cir.setReturnValue(ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION);
                return;
            }
        }

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
            newState = newState
                    .setValue(BeehiveBlock.FACING, blockState.getValue(BeehiveBlock.FACING))
                    .setValue(BeehiveBlock.HONEY_LEVEL, blockState.getValue(BeehiveBlock.HONEY_LEVEL));
        }

        if (level.getBlockEntity(pos) instanceof BeehiveBlockEntity oldBeehive) {
            CompoundTag nbt = oldBeehive.saveWithFullMetadata(level.registryAccess());

            level.removeBlockEntity(pos);
            level.setBlock(pos, newState, 3);

            BlockEntityType<ColoredBeehiveBlockEntity> type = VBlockEntities.BEEHIVE.get();
            BlockEntity newBeehive = type.create(pos, newState);

            if (newBeehive != null) {
                newBeehive.loadCustomOnly(nbt, level.registryAccess());
                level.setBlockEntity(newBeehive);

            } else {
                level.setBlockAndUpdate(pos, newState);
            }

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