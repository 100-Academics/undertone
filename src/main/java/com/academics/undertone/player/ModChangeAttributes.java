package com.academics.undertone.player;

import com.academics.undertone.Undertone;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = Undertone.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModChangeAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, Undertone.MODID);

    public static final DeferredHolder<Attribute, Attribute> FIRE_RESISTANCE = register("fire_resistance", 0.0D, 0.0D, 5.0D);
    public static final DeferredHolder<Attribute, Attribute> RESISTANCE = register("resistance", 0.0D, 0.0D, 5.0D);
    public static final DeferredHolder<Attribute, Attribute> STRENGTH = register("strength", 0.0D, 0.0D, 5.0D);
    public static final DeferredHolder<Attribute, Attribute> WATER_BREATHING = register("water_breathing", 0.0D, 0.0D, 5.0D);
    public static final DeferredHolder<Attribute, Attribute> HASTE = register("haste", 0.0D, 0.0D, 5.0D);
    public static final DeferredHolder<Attribute, Attribute> HEALTH = register("health", 0.0D, 0.0D, 20.0D);
    public static final DeferredHolder<Attribute, Attribute> SATURATION = register("saturation", 0.0D, 0.0D, 20.0D);
    public static final DeferredHolder<Attribute, Attribute> REGENERATION = register("regeneration", 0.0D, 0.0D, 20.0D);
    public static final DeferredHolder<Attribute, Attribute> ABSORPTION = register("absorption", 0.0D, 0.0D, 20.0D);
    public static final DeferredHolder<Attribute, Attribute> LUCK = register("luck", 0.0D, 0.0D, 5.0D);

    private static DeferredHolder<Attribute, Attribute> register(String id, double defaultValue, double min, double max) {
        return ATTRIBUTES.register(id,
                () -> new RangedAttribute("attribute.name." + Undertone.MODID + "." + id, defaultValue, min, max)
                        .setSyncable(true));
    }

    public static void register(IEventBus bus) {
        ATTRIBUTES.register(bus);
    }

    @SubscribeEvent
    public static void addPlayerAttributes(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, FIRE_RESISTANCE);
        event.add(EntityType.PLAYER, RESISTANCE);
        event.add(EntityType.PLAYER, STRENGTH);
        event.add(EntityType.PLAYER, WATER_BREATHING);
        event.add(EntityType.PLAYER, HASTE);
        event.add(EntityType.PLAYER, HEALTH);
        event.add(EntityType.PLAYER, SATURATION);
        event.add(EntityType.PLAYER, REGENERATION);
        event.add(EntityType.PLAYER, ABSORPTION);
        event.add(EntityType.PLAYER, LUCK);
    }
}
