package net.raccoon.will.viatora.core.misc;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.raccoon.will.viatora.Viatora;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.raccoon.will.viatora.core.registry.VBlocks.*;

public class ViatoraPoi {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, Viatora.MODID);

    public static final DeferredHolder<PoiType, PoiType> BEEHIVE = POI_TYPES.register("beehive", () ->
            new PoiType(
                    Stream.of(
                        RED_BEEHIVE.get(),
                                    ORANGE_BEEHIVE.get(),
                                    YELLOW_BEEHIVE.get(),
                                    LIME_BEEHIVE.get(),
                                    GREEN_BEEHIVE.get(),
                                    CYAN_BEEHIVE.get(),
                                    LIGHT_BLUE_BEEHIVE.get(),
                                    BLUE_BEEHIVE.get(),
                                    PURPLE_BEEHIVE.get(),
                                    MAGENTA_BEEHIVE.get(),
                                    PINK_BEEHIVE.get(),
                                    WHITE_BEEHIVE.get(),
                                    LIGHT_GRAY_BEEHIVE.get(),
                                    GRAY_BEEHIVE.get(),
                                    BLACK_BEEHIVE.get(),
                                    BROWN_BEEHIVE.get()
                            )
                            .flatMap(block -> block.getStateDefinition().getPossibleStates().stream())
                            .collect(Collectors.toSet()),
                    0, 1
            ));
    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
    }
}