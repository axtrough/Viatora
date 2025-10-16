package net.raccoon.will.viatora.core.misc.dev;

import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.UUID;

public class PlayerCapeUUIDs {

    public static final Map<UUID, ResourceLocation> PLAYER_CAPES = Map.ofEntries(
            Map.entry(UUID.fromString("1fb0e60d-80ac-4f32-a8af-26153cfa7e07"), // will
                    ResourceLocation.fromNamespaceAndPath("viatora", "textures/cape/viatora_cape.png")),

            Map.entry(UUID.fromString("ae0613d2-f9bb-44a6-8236-1f648cb99a6d"), // faz
                    ResourceLocation.fromNamespaceAndPath("viatora", "textures/cape/viatora_cape.png"))
    );
}
