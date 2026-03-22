package com.academics.undertone;

import com.academics.undertone.block.ModBlocks;
import com.academics.undertone.item.ModCreativeModeTabs;
import com.academics.undertone.item.ModItems;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraft.client.Minecraft;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

// ** README ** \\
// ** IT IS VERY IMPORTANT THAT AFTER YOUR BUILD YOU CLICK ON THE LITTLE ELEPHANT ON THE RIGHT SIDE OF YOUR SCREEN (SHOULD SAY "GRADLE" WHEN YOU HOVER OVER IT)
// ** AND THEN CLICK "BUILD SOURCES", AS THIS LETS YOU SEE MINECRAFT'S CODE SO YOU CAN BASE YOUR OWN OFF OF IT.
// ** IF YOU ARE EVER UNSURE ABOUT HOW TO DO SOMETHING OR HOW TO USE A PARTICULAR CLASS OR METHOD, CHECK THE MINECRAFT CODE TO SEE HOW IT WORKS
// ** AND HOW IT IS USED IN THE VANILLA GAME.
// ** THIS IS THE BEST WAY TO LEARN HOW TO USE THE MINECRAFT CODE AND TO FIGURE OUT HOW TO IMPLEMENT THE FEATURES YOU WANT IN THE MOD.
// ** README ** \\


// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Undertone.MODID)
public class Undertone {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "undertone";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Undertone(IEventBus modEventBus, ModContainer modContainer) {

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (Undertone) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus); // Register the deferred registers for our mod content. This will allow the game to recognize and load our items, blocks, creative mode tabs, etc etc.
        ModItems.register(modEventBus); // see above
        ModBlocks.register(modEventBus); // see above

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) { // ignore for now

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.ZINC_INGOT);
            event.accept(ModItems.RAW_ZINC);
        }
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(ModBlocks.ZINC_ORE);
            event.accept(ModBlocks.ZINC_BLOCK);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = Undertone.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    static class ClientModEvents {
        @SubscribeEvent
        static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
