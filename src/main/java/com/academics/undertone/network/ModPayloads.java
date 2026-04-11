package com.academics.undertone.network;

import com.academics.undertone.Undertone;
import com.academics.undertone.player.PlayerAttributeLevels;
import com.academics.undertone.screen.custom.LevelingAltarMenu;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public final class ModPayloads {
    private ModPayloads() {
    }

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        event.registrar(Undertone.MODID)
                .playToServer(LevelingAltarButtonPayload.TYPE, LevelingAltarButtonPayload.STREAM_CODEC, (payload, context) ->
                        context.enqueueWork(() -> handleLevelingAltarButton(context.player(), payload.containerId(), payload.buttonId())));
    }

    private static void handleLevelingAltarButton(Player player, int containerId, int buttonId) {
        if (!(player.containerMenu instanceof LevelingAltarMenu altarMenu) || altarMenu.containerId != containerId) {
            return;
        }

        PlayerAttributeLevels.Entry entry = PlayerAttributeLevels.byIndex(buttonId);
        if (entry != null) {
            altarMenu.changeLevel(entry);
        }
    }
}

