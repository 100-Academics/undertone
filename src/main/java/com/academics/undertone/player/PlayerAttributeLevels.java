package com.academics.undertone.player;

import com.academics.undertone.entity.attachments.ModAttachments;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

public class PlayerAttributeLevels {
    public static final class Entry {
        private final String id;
        private final DeferredHolder<Attribute, Attribute> attribute;
        private final Supplier<AttachmentType<Integer>> levelAttachment;
        private final int maxLevel;

        public Entry(String id, DeferredHolder<Attribute, Attribute> attribute, Supplier<AttachmentType<Integer>> levelAttachment, int maxLevel) {
            this.id = id;
            this.attribute = attribute;
            this.levelAttachment = levelAttachment;
            this.maxLevel = maxLevel;
        }

        public String id() {
            return id;
        }

        public DeferredHolder<Attribute, Attribute> attribute() {
            return attribute;
        }

        public Supplier<AttachmentType<Integer>> levelAttachment() {
            return levelAttachment;
        }

        public int maxLevel() {
            return maxLevel;
        }

        public int index(Entry entry) {
            return switch (entry.id) {
                case "fire_resistance" -> 0;
                case "resistance" -> 1;
                case "strength" -> 2;
                case "water_breathing" -> 3;
                case "haste" -> 4;
                case "health" -> 5;
                case "saturation" -> 6;
                case "regeneration" -> 7;
                case "absorption" -> 8;
                case "luck" -> 9;
                default -> throw new IllegalArgumentException("Unknown entry: " + entry.id());
            };
        }
    }

    private static final Map<String, Entry> ENTRIES = new LinkedHashMap<>();

    public static final Entry FIRE_RESISTANCE = register(new Entry("fire_resistance", ModChangeAttributes.FIRE_RESISTANCE, ModAttachments.FIRE_RESISTANCE_LEVEL, 5));
    public static final Entry RESISTANCE = register(new Entry("resistance", ModChangeAttributes.RESISTANCE, ModAttachments.RESISTANCE_LEVEL, 5));
    public static final Entry STRENGTH = register(new Entry("strength", ModChangeAttributes.STRENGTH, ModAttachments.STRENGTH_LEVEL, 5));
    public static final Entry WATER_BREATHING = register(new Entry("water_breathing", ModChangeAttributes.WATER_BREATHING, ModAttachments.WATER_BREATHING_LEVEL, 5));
    public static final Entry HASTE = register(new Entry("haste", ModChangeAttributes.HASTE, ModAttachments.HASTE_LEVEL, 5));
    public static final Entry HEALTH = register(new Entry("health", ModChangeAttributes.HEALTH, ModAttachments.HEALTH_LEVEL, 20));
    public static final Entry SATURATION = register(new Entry("saturation", ModChangeAttributes.SATURATION, ModAttachments.SATURATION_LEVEL, 20));
    public static final Entry REGENERATION = register(new Entry("regeneration", ModChangeAttributes.REGENERATION, ModAttachments.REGENERATION_LEVEL, 20));
    public static final Entry ABSORPTION = register(new Entry("absorption", ModChangeAttributes.ABSORPTION, ModAttachments.ABSORPTION_LEVEL, 20));
    public static final Entry LUCK = register(new Entry("luck", ModChangeAttributes.LUCK, ModAttachments.LUCK_LEVEL, 5));

    private static final int[] costs = new int[]{100, 500, 650, 350, 750, 550, 400, 800, 780, 350};

    private static Entry register(Entry entry) {
        ENTRIES.put(entry.id(), entry);
        return entry;
    }

    public static Map<String, Entry> entries() {
        return ENTRIES;
    }

    public static Entry byId(String id) {
        return ENTRIES.get(id.toLowerCase(Locale.ROOT));
    }

    public static Entry byIndex(int index) {
        return switch (index) {
            case 0 -> FIRE_RESISTANCE;
            case 1 -> RESISTANCE;
            case 2 -> STRENGTH;
            case 3 -> WATER_BREATHING;
            case 4 -> HASTE;
            case 5 -> HEALTH;
            case 6 -> SATURATION;
            case 7 -> REGENERATION;
            case 8 -> ABSORPTION;
            case 9 -> LUCK;
            default -> null;
        };
    }

    public static int getLevel(Player player, Entry entry) {
        return clampLevel(player.getData(entry.levelAttachment().get()), entry.maxLevel());
    }

    public static int setLevel(Player player, Entry entry, int newLevel) {
        int clamped = clampLevel(newLevel, entry.maxLevel());
        player.setData(entry.levelAttachment().get(), clamped);
        return clamped;
    }

    public static int addLevel(Player player, Entry entry, int delta) {
        int current = getLevel(player, entry);
        return setLevel(player, entry, current + delta);
    }

    public static void clearAll(Player player) {
        for (Entry entry : ENTRIES.values()) {
            setLevel(player, entry, 0);
        }
    }

    private static int clampLevel(int value, int maxLevel) {
        return Math.max(0, Math.min(maxLevel, value));
    }

    public static int getCostForLevel(Entry entry, int level){
        int entryIndex = entry.index(entry);
        if(entryIndex < 0 || entryIndex >= costs.length){
            throw new IllegalArgumentException("Invalid entry index: " + entry);
        }
        return (int) (costs[entryIndex] * ((level + 1) * 1.25));
    }
}

