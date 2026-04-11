package com.academics.undertone.entity.attachments;

import com.academics.undertone.Undertone;
import com.mojang.serialization.Codec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachments {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Undertone.MODID);

    public static final Supplier<AttachmentType<Integer>> ORBS = ATTACHMENT_TYPES.register(
            "orbs", () -> AttachmentType.builder(() -> 0)
                    .serialize(Codec.INT)
                    .copyOnDeath()
                    .build()
    );

    public static final Supplier<AttachmentType<Integer>> FIRE_RESISTANCE_LEVEL = registerIntLevel("fire_resistance_level");
    public static final Supplier<AttachmentType<Integer>> RESISTANCE_LEVEL = registerIntLevel("resistance_level");
    public static final Supplier<AttachmentType<Integer>> STRENGTH_LEVEL = registerIntLevel("strength_level");
    public static final Supplier<AttachmentType<Integer>> WATER_BREATHING_LEVEL = registerIntLevel("water_breathing_level");
    public static final Supplier<AttachmentType<Integer>> HASTE_LEVEL = registerIntLevel("haste_level");
    public static final Supplier<AttachmentType<Integer>> HEALTH_LEVEL = registerIntLevel("health_level");
    public static final Supplier<AttachmentType<Integer>> SATURATION_LEVEL = registerIntLevel("saturation_level");
    public static final Supplier<AttachmentType<Integer>> REGENERATION_LEVEL = registerIntLevel("regeneration_level");
    public static final Supplier<AttachmentType<Integer>> ABSORPTION_LEVEL = registerIntLevel("absorption_level");
    public static final Supplier<AttachmentType<Integer>> LUCK_LEVEL = registerIntLevel("luck_level");

    private static Supplier<AttachmentType<Integer>> registerIntLevel(String id) {
        return ATTACHMENT_TYPES.register(id, () -> AttachmentType.builder(() -> 0)
                .serialize(Codec.INT)
                .copyOnDeath()
                .build());
    }

    public static void register(IEventBus bus) {
        ATTACHMENT_TYPES.register(bus);
    }
}
