package com.academics.undertone.item;

import com.academics.undertone.Undertone;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ModArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS =
            DeferredRegister.create(Registries.ARMOR_MATERIAL, Undertone.MODID);

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> FIRST_DUNGEON = registerMaterial(
            "first_dungeon",
            3, 8, 6, 3,
            12,
            SoundEvents.ARMOR_EQUIP_IRON,
            1.5F,
            0.05F
    );

    // SAMPLE ARMOR SET (commented on purpose):
    // Use this as a template when adding new sets later.
    //
    // Example uncraftable/dungeon-only material:
    // public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SAMPLE_DUNGEON = registerMaterial(
    //         "sample_dungeon",
    //         2, 6, 5, 2,
    //         10,
    //         SoundEvents.ARMOR_EQUIP_CHAIN,
    //         0.0F,
    //         0.0F
    // );
    //
    // Example craftable material (custom repair ingredient):
    // public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SAMPLE_CRAFTABLE = registerMaterial(
    //         "sample_craftable",
    //         3, 7, 6, 3,
    //         18,
    //         SoundEvents.ARMOR_EQUIP_IRON,
    //         1.0F,
    //         0.0F,
    //         () -> Ingredient.of(ModItems.ZINC_INGOT.get())
    // );

    private static DeferredHolder<ArmorMaterial, ArmorMaterial> registerMaterial(
            String name,
            int helmetDefense,
            int chestDefense,
            int leggingsDefense,
            int bootsDefense,
            int enchantmentValue,
            Holder<SoundEvent> equipSound,
            float toughness,
            float knockbackResistance
    ) {
        return registerMaterial(
                name,
                helmetDefense,
                chestDefense,
                leggingsDefense,
                bootsDefense,
                enchantmentValue,
                equipSound,
                toughness,
                knockbackResistance,
                () -> Ingredient.EMPTY
        );
    }

    private static DeferredHolder<ArmorMaterial, ArmorMaterial> registerMaterial(
            String name,
            int helmetDefense,
            int chestDefense,
            int leggingsDefense,
            int bootsDefense,
            int enchantmentValue,
            Holder<SoundEvent> equipSound,
            float toughness,
            float knockbackResistance,
            Supplier<Ingredient> repairIngredient
    ) {
        Map<ArmorItem.Type, Integer> defenseMap = Map.of(
                ArmorItem.Type.HELMET, helmetDefense,
                ArmorItem.Type.CHESTPLATE, chestDefense,
                ArmorItem.Type.LEGGINGS, leggingsDefense,
                ArmorItem.Type.BOOTS, bootsDefense
        );

        List<ArmorMaterial.Layer> layers = List.of(
                new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Undertone.MODID, name))
        );

        return ARMOR_MATERIALS.register(name, () -> new ArmorMaterial(
                defenseMap,
                enchantmentValue,
                equipSound,
                repairIngredient,
                layers,
                toughness,
                knockbackResistance
        ));
    }

    public static void register(IEventBus bus) {
        ARMOR_MATERIALS.register(bus);
    }
}
