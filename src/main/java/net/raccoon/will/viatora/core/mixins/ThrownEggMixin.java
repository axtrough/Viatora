package net.raccoon.will.viatora.core.mixins;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.raccoon.will.viatora.ViatoraConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThrownEgg.class)
public abstract class ThrownEggMixin extends Projectile {

    protected ThrownEggMixin(EntityType<? extends Projectile> projectile, Level level) {
        super(projectile , level);
    }

    //same here as in SnowballMixin
    @Inject(method = "onHitEntity", at = @At("HEAD"), cancellable = true)
    public void onHitPlayer(EntityHitResult result, CallbackInfo ci) {
        Entity hit = result.getEntity();

        if (hit instanceof Player player) {
            if (ViatoraConfig.THROWABLE_HIT_ENABLED.get()) {
                player.playSound(SoundEvents.ARROW_HIT_PLAYER, 0.18F, 0.45F);
                player.hurt(this.damageSources().thrown(this, this.getOwner()), 0.01F);

            }
        }
    }
}