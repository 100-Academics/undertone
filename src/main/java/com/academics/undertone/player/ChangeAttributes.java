package com.academics.undertone.player;

import com.academics.undertone.Undertone;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Undertone.MODID)
public class ChangeAttributes {
    private static final int EFFECT_REFRESH_TICKS = 40;

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        if (player.level().isClientSide || player.tickCount % 20 != 0) {
            return;
        }

        // Keep runtime attributes in sync with persisted command-granted levels.
        syncAttributeFromLevel(player, PlayerAttributeLevels.FIRE_RESISTANCE);
        syncAttributeFromLevel(player, PlayerAttributeLevels.RESISTANCE);
        syncAttributeFromLevel(player, PlayerAttributeLevels.STRENGTH);
        syncAttributeFromLevel(player, PlayerAttributeLevels.WATER_BREATHING);
        syncAttributeFromLevel(player, PlayerAttributeLevels.HASTE);
        syncAttributeFromLevel(player, PlayerAttributeLevels.HEALTH);
        syncAttributeFromLevel(player, PlayerAttributeLevels.SATURATION);
        syncAttributeFromLevel(player, PlayerAttributeLevels.REGENERATION);
        syncAttributeFromLevel(player, PlayerAttributeLevels.ABSORPTION);

        applyEffectFromLevel(player, PlayerAttributeLevels.FIRE_RESISTANCE, MobEffects.FIRE_RESISTANCE);
        applyEffectFromLevel(player, PlayerAttributeLevels.RESISTANCE, MobEffects.DAMAGE_RESISTANCE);
        applyEffectFromLevel(player, PlayerAttributeLevels.STRENGTH, MobEffects.DAMAGE_BOOST);
        applyEffectFromLevel(player, PlayerAttributeLevels.WATER_BREATHING, MobEffects.WATER_BREATHING);
        applyEffectFromLevel(player, PlayerAttributeLevels.HASTE, MobEffects.DIG_SPEED);
        applyEffectFromLevel(player, PlayerAttributeLevels.HEALTH, MobEffects.HEALTH_BOOST);
        applyEffectFromLevel(player, PlayerAttributeLevels.ABSORPTION, MobEffects.ABSORPTION);

        applySaturation(player);
        applyRegeneration(player);
    }

    private static void syncAttributeFromLevel(Player player, PlayerAttributeLevels.Entry entry) {
        AttributeInstance instance = player.getAttribute(entry.attribute());
        if (instance == null) {
            return;
        }

        int level = PlayerAttributeLevels.getLevel(player, entry);
        if (instance.getBaseValue() != level) {
            instance.setBaseValue(level);
        }
    }

    private static void applyEffectFromLevel(Player player, PlayerAttributeLevels.Entry entry, Holder<MobEffect> effect) {
        int level = PlayerAttributeLevels.getLevel(player, entry);

        if (level <= 0) {
            return;
        }

        // No explicit remove call here so we do not clear potion effects from other sources.
        player.addEffect(new MobEffectInstance(effect, EFFECT_REFRESH_TICKS, level - 1, false, false, false));
    }

    private static void applyEffectFromLevel(Player player, PlayerAttributeLevels.Entry entry, MobEffect effect) {
        applyEffectFromLevel(player, entry, Holder.direct(effect));
    }

    private static void applySaturation(Player player) {
        double value = player.getAttributeValue(ModChangeAttributes.SATURATION);

        if (value <= 0.0D) {
            return;
        }

        float saturationAfter = Math.min(player.getFoodData().getFoodLevel(), player.getFoodData().getSaturationLevel() + (float) value);
        player.getFoodData().setSaturation(saturationAfter);
    }

    private static void applyRegeneration(Player player) {
        double value = player.getAttributeValue(ModChangeAttributes.REGENERATION);

        if (value <= 0.0D) {
            return;
        }

        player.heal((float) value);
    }
}
