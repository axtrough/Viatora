package net.raccoon.will.viatora.core.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.Arrays;

public class CommonConfig {
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.EnumValue<ViatoraClothConfig.TrampleEnum> TYPE;
    public static final ModConfigSpec.IntValue FEATHER_FALLING_LEVEL;
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec.BooleanValue THROWABLE_HIT_ENABLED;

    static {
        BUILDER.comment("Viatora Config");

        TYPE = BUILDER.comment("Trample prevention type")
                .defineEnum("type", ViatoraClothConfig.TrampleEnum.FEATHER_FALLING,
                        Arrays.asList(ViatoraClothConfig.TrampleEnum.values()));

        THROWABLE_HIT_ENABLED = BUILDER.comment("Throwables deal knockback")
                .define("throwableHitEnabled", true);

        FEATHER_FALLING_LEVEL = BUILDER.comment("Feather falling level required to trample")
                .defineInRange("featherFallingLevel", 1, 1, 4);

        SPEC = BUILDER.build();
    }
}
