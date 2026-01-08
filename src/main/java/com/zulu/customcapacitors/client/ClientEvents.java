package com.zulu.customcapacitors.client;

import com.zulu.customcapacitors.CustomCapacitors;
import com.zulu.customcapacitors.capacitor.ConfigLoader;
import com.zulu.customcapacitors.item.EnergyCapacitorItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = CustomCapacitors.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        ConfigLoader.getItems().forEach(holder -> {
            if (holder.get() instanceof EnergyCapacitorItem item) {
                int color = item.getColor();
                event.register((stack, layer) -> layer == 1 ? (color | 0xFF000000) : 0xFFFFFFFF, item);
            }
        });
    }

    @SubscribeEvent
    public static void registerModels(ModelEvent.RegisterAdditional event) {
        var parentModelLoc = ModelResourceLocation.standalone(
                ResourceLocation.fromNamespaceAndPath(CustomCapacitors.MOD_ID, "item/capacitor"));
        event.register(parentModelLoc);
    }

    @SubscribeEvent
    public static void modifyModels(ModelEvent.ModifyBakingResult event) {
        var models = event.getModels();
        var parentLoc = ModelResourceLocation.standalone(
                ResourceLocation.fromNamespaceAndPath(CustomCapacitors.MOD_ID, "item/capacitor"));
        var parentModel = models.get(parentLoc);

        if (parentModel != null) {
            ConfigLoader.getData().forEach(data -> {
                var itemLoc = new ModelResourceLocation(
                        ResourceLocation.fromNamespaceAndPath(CustomCapacitors.MOD_ID, data.id()), "inventory");
                models.put(itemLoc, parentModel);
            });
        }
    }
}
