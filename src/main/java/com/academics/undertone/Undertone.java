package com.academics.undertone;

import com.academics.undertone.block.ModBlocks;
import com.academics.undertone.block.entity.ModBlockEntities;
import com.academics.undertone.command.ModCommands;
import com.academics.undertone.entity.ModEntities;
import com.academics.undertone.entity.attachments.ModAttachments;
import com.academics.undertone.entity.client.Grock.GrockRenderer;
import com.academics.undertone.entity.client.Ailake.AilakeRenderer;
import com.academics.undertone.entity.client.Rudahh.RudahhRenderer;
import com.academics.undertone.item.ModArmorMaterials;
import com.academics.undertone.item.ModCreativeModeTabs;
import com.academics.undertone.item.ModItems;
import com.academics.undertone.player.ModChangeAttributes;
import com.academics.undertone.network.ModPayloads;
import com.academics.undertone.screen.ModMenuTypes;
import net.minecraft.client.renderer.entity.EntityRenderers;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.storage.LevelResource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;




// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Undertone.MODID)
public class Undertone {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "undertone";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger(); // ** if you want to use the logger, import Undertone.LOGGER and then call Undertone.LOGGER.info("XYZ")
    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Undertone(IEventBus modEventBus, ModContainer modContainer) {

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (Undertone) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(ModCommands.class);

        ModCreativeModeTabs.register(modEventBus); // Register the deferred registers for our mod content. This will allow the game to recognize and load our items, blocks, creative mode tabs, etc etc.
        ModArmorMaterials.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModEntities.register(modEventBus);

        ModAttachments.register(modEventBus);
        ModChangeAttributes.register(modEventBus);
        modEventBus.addListener(ModPayloads::register);

        ModMenuTypes.register(modEventBus);
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

    @SubscribeEvent
    public void onServerAboutToStart(ServerAboutToStartEvent event) {
        syncFtbQuestsForNewWorld(event.getServer().getWorldPath(LevelResource.ROOT));
    }

    private void syncFtbQuestsForNewWorld(Path worldRoot) {
        Path syncMarker = worldRoot.resolve(".undertone_ftbquests_synced");

        if (Files.exists(syncMarker)) {
            return;
        }

        Path gameDir = FMLPaths.GAMEDIR.get().toAbsolutePath().normalize();
        Path projectConfigDir = gameDir.getParent() != null
                ? gameDir.getParent().resolve("config")
                : gameDir.resolve("config");

        Path sourceDir = projectConfigDir.resolve("ftbquests");
        Path targetDir = FMLPaths.CONFIGDIR.get().toAbsolutePath().normalize().resolve("ftbquests");

        if (!Files.isDirectory(sourceDir)) {
            LOGGER.warn("Skipping FTB Quests sync: source folder not found at {}", sourceDir);
            return;
        }

        try {
            copyDirectory(sourceDir, targetDir);
            Files.createDirectories(worldRoot);
            Files.writeString(syncMarker, "synced", StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            LOGGER.info("Synced FTB Quests config for world {} from {} to {}", worldRoot.getFileName(), sourceDir, targetDir);
        } catch (IOException exception) {
            LOGGER.error("Failed to sync FTB Quests config for world {}", worldRoot.getFileName(), exception);
        }
    }

    private void copyDirectory(Path sourceDir, Path targetDir) throws IOException {
        Files.walkFileTree(sourceDir, new SimpleFileVisitor<>() {
            @Override
            public java.nio.file.FileVisitResult preVisitDirectory(Path directory, BasicFileAttributes attributes) throws IOException {
                Path relativePath = sourceDir.relativize(directory);
                Path destination = targetDir.resolve(relativePath);
                Files.createDirectories(destination);
                return java.nio.file.FileVisitResult.CONTINUE;
            }

            @Override
            public java.nio.file.FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
                Path relativePath = sourceDir.relativize(file);
                Path destination = targetDir.resolve(relativePath);
                Files.copy(file, destination, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
                return java.nio.file.FileVisitResult.CONTINUE;
            }
        });
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = Undertone.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    static class ClientModEvents {
        @SubscribeEvent
        static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.AILAKE.get(), AilakeRenderer::new);
            EntityRenderers.register(ModEntities.GROCK.get(), GrockRenderer::new);
            EntityRenderers.register(ModEntities.RUDAHH.get(), RudahhRenderer::new);
        }
    }
}
