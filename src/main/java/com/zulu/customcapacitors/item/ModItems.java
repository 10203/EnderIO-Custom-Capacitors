package com.zulu.customcapacitors.item;

import com.zulu.customcapacitors.CustomCapacitors;
import com.zulu.customcapacitors.capacitor.ConfigLoader;
import com.zulu.customcapacitors.compat.GuideMeCompat;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM,
                        CustomCapacitors.MOD_ID);
        public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister
                        .create(Registries.CREATIVE_MODE_TAB, CustomCapacitors.MOD_ID);

        public static final DeferredHolder<Item, Item> GUIDE_HINT = ITEMS.register("guide_hint",
                        () -> new Item(new Item.Properties().stacksTo(1)) {
                                @Override
                                public Component getName(ItemStack stack) {
                                        return Component.literal("Hold G to open Guide");
                                }
                        });

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = TABS.register("tab",
                        () -> CreativeModeTab.builder()
                                        .icon(() -> ConfigLoader.getItems().stream().findFirst()
                                                        .map(h -> h.get().getDefaultInstance())
                                                        .orElse(net.minecraft.world.item.Items.REDSTONE
                                                                        .getDefaultInstance()))
                                        .title(Component.translatable("itemGroup.custom_capacitors.tab"))
                                        .displayItems((p, o) -> {
                                                if (ModList.get().isLoaded("guideme")) {
                                                        ItemStack guideBook = GuideMeCompat.createGuideItem();
                                                        if (!guideBook.isEmpty()) {
                                                                o.accept(guideBook);
                                                        }
                                                }
                                                o.accept(GUIDE_HINT.get());
                                                ConfigLoader.getItems().forEach(h -> o.accept(h.get()));
                                        })
                                        .build());

        public static void register(IEventBus bus) {
                ConfigLoader.load();
                ConfigLoader.register(ITEMS);
                ITEMS.register(bus);
                TABS.register(bus);
        }
}
