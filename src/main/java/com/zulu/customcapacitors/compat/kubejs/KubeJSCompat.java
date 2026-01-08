package com.zulu.customcapacitors.compat.kubejs;

import com.zulu.customcapacitors.CustomCapacitors;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.registry.BuilderTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;

public class KubeJSCompat implements KubeJSPlugin {
    @Override
    public void registerBuilderTypes(BuilderTypeRegistry reg) {
        reg.of(Registries.ITEM, r -> r.add(ResourceLocation.fromNamespaceAndPath(CustomCapacitors.MOD_ID, "capacitor"),
                CapacitorBuilder.class, CapacitorBuilder::new));
    }
}
