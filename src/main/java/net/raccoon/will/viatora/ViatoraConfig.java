package net.raccoon.will.viatora;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.raccoon.will.viatora.common.enums.TrampleEnum;

import java.util.Arrays;

public class ViatoraConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.EnumValue<TrampleEnum> TYPE;
    public static final ModConfigSpec.IntValue FEATHER_FALLING_LEVEL;

    static {
        BUILDER.comment("Viatora Config");

        TYPE = BUILDER.comment("Trample prevention type")
                .defineEnum("type", TrampleEnum.FEATHER_FALLING,
                        Arrays.asList(TrampleEnum.values()));

        FEATHER_FALLING_LEVEL = BUILDER.comment("Min Feather Falling level required to be unable to trample")
                .defineInRange("featherFallingLevel", 1, 1, 4);

        SPEC = BUILDER.build();
    }
}

