package net.raccoon.will.viatora.core.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;



@Config(name = "viatora")
public class ViatoraConfig implements ConfigData {

    public boolean ThrowablesHit = true;

    @ConfigEntry.BoundedDiscrete(min = 1, max = 4)
    public int TrampleLevel = 1;

}
