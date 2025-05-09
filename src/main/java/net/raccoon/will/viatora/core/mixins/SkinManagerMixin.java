package net.raccoon.will.viatora.core.mixins;

import com.mojang.authlib.minecraft.MinecraftProfileTextures;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Mixin(SkinManager.class)
public final class SkinManagerMixin {

    private static final Set<UUID> CAPED_PLAYERS = Set.of(
            UUID.fromString("1fb0e60d-80ac-4f32-a8af-26153cfa7e07")
    );

    private static final ResourceLocation CAPE_TEXTURE = ResourceLocation.fromNamespaceAndPath("viatora", "textures/cape/viatora_cape.png");

    @Inject(method = "registerTextures", at = @At("RETURN"), cancellable = true)
    private void registerCapeForSpecificPlayers(UUID uuid, MinecraftProfileTextures profileTextures,
                                                CallbackInfoReturnable<CompletableFuture<PlayerSkin>> info) {
        if (CAPED_PLAYERS.contains(uuid)) {
            var newPlayerSkin = info.getReturnValue();

            info.setReturnValue(newPlayerSkin.thenApply(playerSkin -> {
                if (playerSkin.capeTexture() == null) {
                    return new PlayerSkin(
                            playerSkin.texture(),
                            playerSkin.textureUrl(),
                            CAPE_TEXTURE,
                            CAPE_TEXTURE,
                            playerSkin.model(),
                            playerSkin.secure()
                    );
                }
                return playerSkin;
            }));
        }
    }
}
