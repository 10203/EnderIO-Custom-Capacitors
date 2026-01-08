package com.zulu.customcapacitors;

import com.zulu.customcapacitors.compat.GuideMeCompat;
import com.zulu.customcapacitors.component.ModDataComponents;
import com.zulu.customcapacitors.item.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(CustomCapacitors.MOD_ID)
public class CustomCapacitors {
    public static final String MOD_ID = "custom_capacitors";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public CustomCapacitors(IEventBus bus) {
        ModDataComponents.register(bus);
        ModItems.register(bus);

        GuideMeCompat.init();
    }
}
