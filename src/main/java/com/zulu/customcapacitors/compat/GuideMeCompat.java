package com.zulu.customcapacitors.compat;

import com.zulu.customcapacitors.CustomCapacitors;
import guideme.Guide;
import guideme.GuideItemSettings;
import guideme.Guides;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;

import java.util.List;
import java.util.Optional;

public class GuideMeCompat {
    private static Guide guide;

    public static void init() {
        if (!ModList.get().isLoaded("guideme"))
            return;

        try {
            ResourceLocation guideId = ResourceLocation.fromNamespaceAndPath(CustomCapacitors.MOD_ID, "guide");
            GuideItemSettings itemSettings = new GuideItemSettings(
                    Optional.of(Component.literal("Custom Capacitors Guide")),
                    List.of(Component.literal("Add custom capacitor tiers")),
                    Optional.empty());

            guide = Guide.builder(guideId)
                    .defaultNamespace(CustomCapacitors.MOD_ID)
                    .startPage(ResourceLocation.fromNamespaceAndPath(CustomCapacitors.MOD_ID, "index.md"))
                    .itemSettings(itemSettings)
                    .build();
        } catch (Exception e) {
            CustomCapacitors.LOGGER.error("[CustomCapacitors] Failed to init GuideMe", e);
        }
    }

    public static Guide getGuide() {
        return guide;
    }

    public static ItemStack createGuideItem() {
        return guide == null ? ItemStack.EMPTY : Guides.createGuideItem(guide.getId());
    }
}
