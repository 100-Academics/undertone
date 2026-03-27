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


    public static void register(IEventBus bus) {
        ATTACHMENT_TYPES.register(bus);
    }
}
