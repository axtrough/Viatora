package net.raccoon.will.viatora;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.raccoon.will.viatora.common.enums.TrampleEnum;

import java.util.Arrays;

public class ViatoraConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.EnumValue<TrampleEnum> TYPE;
    public static final ModConfigSpec.IntValue FEATHER_FALLING_LEVEL;
    public static final ModConfigSpec.BooleanValue VANILLA_STYLED_DYED_BEEHIVES;

    static {
        BUILDER.comment("Viatora Config");

        TYPE = BUILDER.comment("Trample prevention type")
                .defineEnum("type", TrampleEnum.FEATHER_FALLING,
                        Arrays.asList(TrampleEnum.values()));

        FEATHER_FALLING_LEVEL = BUILDER.comment("Min Feather Falling level required to be unable to trample")
                .defineInRange("featherFallingLevel", 1, 1, 4);

        VANILLA_STYLED_DYED_BEEHIVES = BUILDER.comment("Use vanilla-style textures for dyed beehives (true = vanilla-styled, false = custom)")
                .define("vanillaStyledDyedBeehives", false);

        SPEC = BUILDER.build();
    }
}

