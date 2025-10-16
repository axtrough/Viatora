package net.raccoon.will.viatora.core.mixin;

import com.mojang.authlib.minecraft.MinecraftProfileTextures;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static net.raccoon.will.viatora.core.misc.dev.PlayerCapeUUIDs.PLAYER_CAPES;

@Mixin(SkinManager.class)
public final class SkinManagerMixin {

    @Inject(method = "registerTextures", at = @At("RETURN"), cancellable = true)
    private void registerCapeForSpecificPlayer(UUID uuid, MinecraftProfileTextures profileTextures, CallbackInfoReturnable<CompletableFuture<PlayerSkin>> info) {
        if (PLAYER_CAPES.containsKey(uuid)) {
            ResourceLocation capeTexture = PLAYER_CAPES.get(uuid);
            CompletableFuture<PlayerSkin> newPlayerSkin = info.getReturnValue();

            info.setReturnValue(newPlayerSkin.thenApply(playerSkin -> new PlayerSkin(playerSkin.texture(),
                    playerSkin.textureUrl(),
                    capeTexture,
                    capeTexture,
                    playerSkin.model(),
                    playerSkin.secure()
            )));
        }
    }
}