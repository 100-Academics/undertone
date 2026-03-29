package com.academics.undertone.events;

import com.academics.undertone.Undertone;
import com.academics.undertone.entity.ModEntities;
import com.academics.undertone.entity.client.GrockModel;
import com.academics.undertone.entity.custom.GrockEntity;
import com.academics.undertone.screen.ModMenuTypes;
import com.academics.undertone.screen.custom.LevelingAltarScreen;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = Undertone.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.GROCK.get(), GrockEntity.createAttributes().build());
    }

    @EventBusSubscriber(modid = Undertone.MODID, bus = EventBusSubscriber.Bus.MOD, value = net.neoforged.api.distmarker.Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(GrockModel.LAYER_LOCATION, GrockModel::createBodyLayer);
        }

        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(ModMenuTypes.LEVELING_ALTAR_MENU.get(), LevelingAltarScreen::new);
        }
    }
}
