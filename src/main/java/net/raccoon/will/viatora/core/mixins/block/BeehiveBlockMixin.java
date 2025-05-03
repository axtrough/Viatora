package net.raccoon.will.viatora.core.mixins.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.Tags;
import net.raccoon.will.viatora.core.registry.VBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BeehiveBlock.class)
public class BeehiveBlockMixin {

    @Inject(method = "useItemOn", at = @At("TAIL"), cancellable = true)
    public void useDye(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult, CallbackInfoReturnable<InteractionResult> cir) {
        if (itemStack.is(Tags.Items.DYES)) {
            level.setBlock(blockPos, VBlocks.RED_BEEHIVE.get().defaultBlockState(), 3);
            level.playSound(player, blockPos, SoundEvents.HORSE_DEATH, SoundSource.AMBIENT, 1f, 1f);
            player.displayClientMessage(Component.literal("you dyed it!"), true);
        }
    }

}