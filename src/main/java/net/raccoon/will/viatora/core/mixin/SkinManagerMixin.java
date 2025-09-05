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

@Mixin(SkinManager.class)
public final class SkinManagerMixin {

    private static final Map<UUID, ResourceLocation> PLAYER_CAPES = Map.ofEntries(
Map.entry(UUID.fromString("1fb0e60d-80ac-4f32-a8af-26153cfa7e07"), // will
                ResourceLocation.fromNamespaceAndPath("viatora", "textures/cape/viatora_cape.png")),

            Map.entry(UUID.fromString("ae0613d2-f9bb-44a6-8236-1f648cb99a6d"), // faz
                ResourceLocation.fromNamespaceAndPath("viatora", "textures/cape/viatora_cape.png"))
    );

    @Inject(method = "registerTextures", at = @At("RETURN"), cancellable = true)
    private void registerCapeForSpecificPlayer(UUID uuid, MinecraftProfileTextures profileTextures,
                                                CallbackInfoReturnable<CompletableFuture<PlayerSkin>> info) {
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