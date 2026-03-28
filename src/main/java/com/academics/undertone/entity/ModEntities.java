package com.academics.undertone.entity;

import com.academics.undertone.Undertone;
import com.academics.undertone.entity.custom.GrockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Undertone.MODID);

    public static final Supplier<EntityType<GrockEntity>> GROCK = ENTITY_TYPES.register("grock",
            () -> EntityType.Builder.of(GrockEntity::new, MobCategory.MONSTER)
                    .sized(1f, 1f) // hitbox size of the entity (width, height)
                    .fireImmune()
                    .build("grock"));

    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }

}
