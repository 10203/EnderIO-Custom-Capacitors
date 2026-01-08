package com.zulu.customcapacitors.component;

import com.enderio.base.api.capacitor.CapacitorData;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents {
    @SuppressWarnings("deprecation")
    private static final DeferredRegister<DataComponentType<?>> REG = DeferredRegister.createDataComponents("enderio");

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CapacitorData>> CAPACITOR_DATA = REG
            .register("capacitor_data",
                    () -> DataComponentType.<CapacitorData>builder().persistent(CapacitorData.CODEC).build());

    public static void register(IEventBus bus) {
        REG.register(bus);
    }
}
