package com.kaironeybaloney.hadeandepths.entity;

import com.kaironeybaloney.hadeandepths.HadeanDepths;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, HadeanDepths.MODID);

    public static ResourceKey<EntityType<?>> HANGING_FISH_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("hanging_fish"));

    public static final Supplier<EntityType<HangingFishEntity>> HANGING_FISH_LEGENDARY =
            ENTITY_TYPES.register("hanging_fish_legendary", () -> EntityType.Builder.of(HangingFishEntity::new, MobCategory.MISC)
                    .sized(0.5f, 1.5f).build(HANGING_FISH_KEY));
    public static final Supplier<EntityType<HangingFishEntity>> HANGING_FISH_COMMON =
            ENTITY_TYPES.register("hanging_fish_common", () -> EntityType.Builder.of(HangingFishEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.75f).build(HANGING_FISH_KEY));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
