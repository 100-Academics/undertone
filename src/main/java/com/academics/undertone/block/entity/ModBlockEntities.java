package com.academics.undertone.block.entity;

import com.academics.undertone.Undertone;
import com.academics.undertone.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Undertone.MODID);

    public static final Supplier<BlockEntityType<LevelingAltarEntity>> LEVELING_ALTAR_BE =
            BLOCK_ENTITIES.register("leveling_altar_be", () -> BlockEntityType.Builder.of(
                    LevelingAltarEntity::new, ModBlocks.LEVELING_ALTAR.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
