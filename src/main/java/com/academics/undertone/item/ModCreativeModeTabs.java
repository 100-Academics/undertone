package com.academics.undertone.item;

import com.academics.undertone.Undertone;
import com.academics.undertone.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
        public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Undertone.MODID); // create new deferred register for creative mode tabs with our modid. This is necessary for each new type of thing you add, whether it be items, blocks, entities, etc etc.

        public static final Supplier<CreativeModeTab> UNDERTONE_TAB = CREATIVE_MODE_TAB.register("undertone_tab", // register new creative mode tab with name "undertone_tab" and properties defined in the supplier function
                () -> CreativeModeTab.builder()
                        .icon(() -> ModItems.ZINC_INGOT.get().getDefaultInstance()) // use .withTabsBefore() if you want to specify the position of your tab
                                                                                    // proper syntax looks like .withTabsBefore(ResourceLocation.fromNamespaceAndPath(Undertone.MODID, "tab_name"))
                        .title(Component.translatable("creativetab.undertone.undertone"))
                        .displayItems((itemDisplayParameters, output) -> { // this is where you specify which items should be displayed in this creative mode tab. You can add items, blocks, or any other type of item stack that can be displayed in the creative inventory. The output parameter is a consumer that accepts item stacks to be displayed in the tab.
                            output.accept(ModItems.RAW_ZINC);                                         // only add items or properties inside of the code encompassed by .builder() and .build() for them to be added to the creative tab.
                            output.accept(ModItems.ZINC_INGOT);
                            output.accept(ModBlocks.ZINC_ORE);
                            output.accept(ModBlocks.ZINC_BLOCK);
                        })
                        .build());
        public static void register(IEventBus bus) { // this is to allow the game to register our creative mode tab at the appropriate time during mod loading. This should always be called from the constructor of your main mod class, and the bus parameter should be the mod event bus passed into the constructor.
            CREATIVE_MODE_TAB.register(bus);
        }

}
