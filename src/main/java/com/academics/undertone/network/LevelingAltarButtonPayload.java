package com.academics.undertone.network;

import com.academics.undertone.Undertone;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record LevelingAltarButtonPayload(int containerId, int buttonId) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<LevelingAltarButtonPayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Undertone.MODID, "leveling_altar_button"));
    public static final StreamCodec<RegistryFriendlyByteBuf, LevelingAltarButtonPayload> STREAM_CODEC =
            StreamCodec.composite(ByteBufCodecs.INT, LevelingAltarButtonPayload::containerId,
                    ByteBufCodecs.INT, LevelingAltarButtonPayload::buttonId,
                    LevelingAltarButtonPayload::new);

    @Override
    public Type<LevelingAltarButtonPayload> type() {
        return TYPE;
    }
}

