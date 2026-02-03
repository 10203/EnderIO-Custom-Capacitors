package com.zulu.customcapacitors.item;

import com.zulu.customcapacitors.component.ModDataComponents;
import com.enderio.enderio.api.capacitor.CapacitorData;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class EnergyCapacitorItem extends Item {
    private final CapacitorData data;
    private final String name;
    private final boolean glow;
    private final int color;

    public EnergyCapacitorItem(Properties props, CapacitorData data, String name, boolean glow, int color) {
        super(props);
        this.data = data;
        this.name = name;
        this.glow = glow;
        this.color = color;
    }

    public CapacitorData getCapacitorData() {
        return data;
    }

    public float getLevel() {
        return data.base();
    }

    public int getColor() {
        return color;
    }

    @Override
    public void verifyComponentsAfterLoad(ItemStack stack) {
        if (stack.get(ModDataComponents.CAPACITOR_DATA.get()) == null)
            stack.set(ModDataComponents.CAPACITOR_DATA.get(), data);
    }

    @Override
    public Component getName(ItemStack stack) {
        return name != null ? Component.literal(name) : super.getName(stack);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return glow;
    }
}
