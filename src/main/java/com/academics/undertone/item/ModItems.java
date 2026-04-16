package com.academics.undertone.item;

import com.academics.undertone.Undertone;
import com.academics.undertone.item.custom.BlockSwapperItem;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Undertone.MODID); // create new deferred register for items with our modid. This is necessary for each new type of thing you add, whether it be items, blocks, entities, etc etc.

    public static final DeferredItem<Item> ZINC_INGOT = ITEMS.register("zinc_ingot", // register new item with name "zinc_ingot" and properties defined in the supplier function
            () -> new Item(new Item.Properties())); // () -> indicates that this is a supplier function, which is necessary for deferred registration. The item properties can be customized as needed, but for a simple item like an ingot, the default properties are usually sufficient.

    public static final DeferredItem<Item> RAW_ZINC = ITEMS.register("raw_zinc", // same as above
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> WEAK_DUNGONIUM_INGOT = ITEMS.register("weak_dungonium_ingot", // register new item with name "zinc_ingot" and properties defined in the supplier function
            () -> new Item(new Item.Properties())); // () -> indicates that this is a supplier function, which is necessary for deferred registration. The item properties can be customized as needed, but for a simple item like an ingot, the default properties are usually sufficient.

    public static final DeferredItem<Item> RAW_WEAK_DUNGONIUM = ITEMS.register("raw_weak_dungonium", // same as above
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> BLOCK_SWAPPER = ITEMS.register("block_swapper",
            () -> new BlockSwapperItem(new Item.Properties().durability(64).stacksTo(1)));
                  // ** ^^^^ when registering a complex item, make sure to call the class instead of Item.

    public static final DeferredItem<ArmorItem> WEAK_DUNGONIUM_HELMET = registerArmorItem("weak_dungonium_helmet", ModArmorMaterials.WEAK_DUNGONIUM, ArmorItem.Type.HELMET);
    public static final DeferredItem<ArmorItem> WEAK_DUNGONIUM_CHESTPLATE = registerArmorItem("weak_dungonium_chestplate", ModArmorMaterials.WEAK_DUNGONIUM, ArmorItem.Type.CHESTPLATE);
    public static final DeferredItem<ArmorItem> WEAK_DUNGONIUM_LEGGINGS = registerArmorItem("weak_dungonium_leggings", ModArmorMaterials.WEAK_DUNGONIUM, ArmorItem.Type.LEGGINGS);
    public static final DeferredItem<ArmorItem> WEAK_DUNGONIUM_BOOTS = registerArmorItem("weak_dungonium_boots", ModArmorMaterials.WEAK_DUNGONIUM, ArmorItem.Type.BOOTS);


    private static DeferredItem<ArmorItem> registerArmorItem(String name, Holder<ArmorMaterial> material, ArmorItem.Type type) {
        return ITEMS.register(name,
                () -> new ArmorItem(material, type, new Item.Properties().durability(type.getDurability(25))));
    }

    public static void register(IEventBus bus) { // this is to allow the game to register our items at the appropriate time during mod loading. This should always be called from the constructor of your main mod class, and the bus parameter should be the mod event bus passed into the constructor.
        ITEMS.register(bus);                     // you call this in the Undertone file.
    }


}
