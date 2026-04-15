package com.academics.undertone.entity;

import com.academics.undertone.Undertone;
import com.academics.undertone.entity.custom.AilakeEntity;
import com.academics.undertone.entity.custom.GrockEntity;
import com.academics.undertone.entity.custom.RudahhEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
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

    public static final Supplier<EntityType<RudahhEntity>> RUDAHH = ENTITY_TYPES.register("rudahh",
            () -> EntityType.Builder.of(RudahhEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.8f)
                    .fireImmune()
                    .build("rudahh"));

    public static final Supplier<EntityType<AilakeEntity>> AILAKE = ENTITY_TYPES.register("ailake",
            () -> EntityType.Builder.of(AilakeEntity::new, MobCategory.MONSTER)
                    .sized(1f, 1f) //TODO set up better later
                    .fireImmune()
                    .build("ailake"));

    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }

}
