package com.academics.undertone.item;

import com.academics.undertone.Undertone;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

// ** READ ME ** //
// ** Every item (or other object) must be registered with the game. For items, that is done here.
// ** The DeferredRegister system is a way to register items (or blocks, entities, etc) in a way that is safe and compatible with the game's loading process.
// ** It allows you to define your items in a static context, but the actual registration happens at the appropriate time during mod loading, which prevents issues with items being accessed before they are registered or other timing-related problems.
// ** Each item is registered with a unique id (i.e "zinc_ingot" (each name must be lowercase and only contain a-z and _)) as well as given various properties inside the supplier function.
// ** After doing so here, you must make JSON files using that unique id under resources/assets/undertone in the models and textures folders to define the appearance of the item in game. You can copy the JSON files for an existing item and change the name and texture reference to match your new item, which is usually easier than writing them from scratch. For a simple item like an ingot, you can use the existing iron_ingot JSON files as a template, or one of the ones already made.
// ** The model JSON file should be placed in resources/assets/undertone/models/item and the texture PNG file should be placed in resources/assets/undertone/textures/item.
// ** You then have to go to resources/assets/undertone/lang/en_us.json and add a line to define the display name of your item in game, which should be in the format "item.undertone.zinc_ingot": "Zinc Ingot", where the first part is "item." followed by your modid, followed by the unique id of your item, and the second part is the display name you want to appear in game.
// ** This is necessary for the item to have a proper name in game instead of just showing up as its unique id (i.e "item.undertone.zinc_ingot") when you hover over it in the inventory or have it in your hand.
// ** You can also add translations for other languages by adding the appropriate entries to the corresponding lang JSON files (i.e "item.undertone.zinc_ingot": "Lingot de Zinc" in fr_fr.json for a French translation).
// ** You must then also add the item to a creative mode tab, which is done in ModCreativeModeTabs.java. If you do not do this, the item will still be registered and can be obtained through commands or other means, but it will not be visible in the creative inventory and may be more difficult to obtain for testing purposes.
// ** You can also choose to not add it to any creative mode tab if you want it to be a more "hidden" item that can only be obtained through specific means, but for most items you will want to add them to at least one creative mode tab.
// ** Finally, you must call the register method for this class from the constructor of your main mod class (Undertone.java) and pass in the mod event bus. This allows the game to recognize and register your items at the appropriate time during mod loading. If you do not do this, your items will not be registered and will not be available in game.
// ** This last step is done for you.
// ** Blocks require a bit more work, just look at what's been done already.
// ** README ** //

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Undertone.MODID); // create new deferred register for items with our modid. This is necessary for each new type of thing you add, whether it be items, blocks, entities, etc etc.

    public static final DeferredItem<Item> ZINC_INGOT = ITEMS.register("zinc_ingot", // register new item with name "zinc_ingot" and properties defined in the supplier function
            () -> new Item(new Item.Properties())); // () -> indicates that this is a supplier function, which is necessary for deferred registration. The item properties can be customized as needed, but for a simple item like an ingot, the default properties are usually sufficient.

    public static final DeferredItem<Item> RAW_ZINC = ITEMS.register("raw_zinc", // same as above
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus bus) { // this is to allow the game to register our items at the appropriate time during mod loading. This should always be called from the constructor of your main mod class, and the bus parameter should be the mod event bus passed into the constructor.
        ITEMS.register(bus);                     // you call this in the Undertone file.
    }



}
