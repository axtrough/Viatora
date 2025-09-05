package net.raccoon.will.viatora.core.mixin;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TraceableEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.raccoon.will.viatora.Viatora.config;

@Mixin(Projectile.class)
public abstract class ProjectileMixin extends Entity implements TraceableEntity {
    public ProjectileMixin(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow
    public abstract Entity getOwner();

    @Inject(method = "onHitEntity", at = @At("HEAD"), cancellable = true)
    public void onHitPlayer(EntityHitResult result, CallbackInfo ci) {
        Entity hit = result.getEntity();

        if (this.getType() == EntityType.EGG || this.getType() == EntityType.SNOWBALL)
            if (hit instanceof Player player) {
                if (config.ThrowablesHit) {
                    player.invulnerableTime = 0;
                    player.hurt(this.damageSources().thrown(this, this.getOwner()), 0.00420F);
                    if (this.getOwner() instanceof Player thrower) {
                        thrower.playSound(SoundEvents.ARROW_HIT_PLAYER);
                    }
                }

            }

    }
}

