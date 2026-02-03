package com.zulu.customcapacitors.compat.kubejs;

import com.zulu.customcapacitors.CustomCapacitors;
import com.zulu.customcapacitors.component.ModDataComponents;
import com.enderio.enderio.api.capacitor.CapacitorData;
import dev.latvian.mods.kubejs.item.ItemBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import java.util.Map;

public class CapacitorBuilder extends ItemBuilder {
    public int lvl = 1;
    public boolean glow = false;

    public CapacitorBuilder(ResourceLocation id) {
        super(ResourceLocation.fromNamespaceAndPath(CustomCapacitors.MOD_ID, id.getPath()));
        maxStackSize(64);
    }

    public CapacitorBuilder level(int l) {
        lvl = l;
        return this;
    }

    public CapacitorBuilder glowing(boolean g) {
        glow = g;
        this.glow = g;
        return this;
    }

    @Override
    public Item createObject() {
        return new CapItem(this);
    }

    public static class CapItem extends Item {
        private final CapacitorBuilder b;
        private final CapacitorData data;

        public CapItem(CapacitorBuilder b) {
            super(b.createItemProperties());
            this.b = b;
            this.data = new CapacitorData(b.lvl, Map.of());
        }

        @Override
        public void verifyComponentsAfterLoad(ItemStack s) {
            if (s.get(ModDataComponents.CAPACITOR_DATA.get()) == null)
                s.set(ModDataComponents.CAPACITOR_DATA.get(), data);
        }

        @Override
        public boolean isFoil(ItemStack s) {
            return b.glow;
        }

        public CapacitorData getData() {
            return data;
        }
    }
}
