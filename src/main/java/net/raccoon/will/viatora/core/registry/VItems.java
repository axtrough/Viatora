package net.raccoon.will.viatora.core.registry;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.raccoon.will.viatora.Viatora;


public class VItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Viatora.MODID);


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
