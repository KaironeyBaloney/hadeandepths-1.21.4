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
    public static ResourceKey<EntityType<?>> TOOTH_ARROW_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("tooth_arrow"));
    public static ResourceKey<EntityType<?>> FLAME_SEEKER_ARROW_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("flame_seeker_arrow"));
    public static ResourceKey<EntityType<?>> WOODEN_FISHING_BOBBER_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("wooden_fishing_bobber"));
    public static ResourceKey<EntityType<?>> IRON_FISHING_BOBBER_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("iron_fishing_bobber"));
    public static ResourceKey<EntityType<?>> DIAMOND_FISHING_BOBBER_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("diamond_fishing_bobber"));
    public static ResourceKey<EntityType<?>> NETHERITE_FISHING_BOBBER_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("netherite_fishing_bobber"));
    public static ResourceKey<EntityType<?>> MAGMATIC_FISHING_BOBBER_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace("magmatic_fishing_bobber"));

    public static final Supplier<EntityType<HangingFishEntity>> HANGING_FISH_LEGENDARY =
            ENTITY_TYPES.register("hanging_fish_legendary", () -> EntityType.Builder.of(HangingFishEntity::new, MobCategory.MISC)
                    .sized(0.5f, 1.5f).build(HANGING_FISH_KEY));
    public static final Supplier<EntityType<HangingFishEntity>> HANGING_FISH_COMMON =
            ENTITY_TYPES.register("hanging_fish_common", () -> EntityType.Builder.of(HangingFishEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.75f).build(HANGING_FISH_KEY));

    public static final Supplier<EntityType<ToothArrowEntity>> TOOTH_ARROW =
            ENTITY_TYPES.register("tooth_arrow", () -> EntityType.Builder.<ToothArrowEntity>of(ToothArrowEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.75f).build(TOOTH_ARROW_KEY));
    public static final Supplier<EntityType<FlameSeekerArrowEntity>> FLAME_SEEKER_ARROW =
            ENTITY_TYPES.register("flame_seeker_arrow", () -> EntityType.Builder.<FlameSeekerArrowEntity>of(FlameSeekerArrowEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.75f).build(FLAME_SEEKER_ARROW_KEY));

    public static final Supplier<EntityType<WoodenFishingHook>> WOODEN_FISHING_BOBBER =
            ENTITY_TYPES.register("wooden_fishing_bobber", () -> EntityType.Builder.<WoodenFishingHook>of(WoodenFishingHook::new, MobCategory.MISC)
                    .sized(0.25f, 0.25f).build(WOODEN_FISHING_BOBBER_KEY));
    public static final Supplier<EntityType<IronFishingHook>> IRON_FISHING_BOBBER =
            ENTITY_TYPES.register("iron_fishing_bobber", () -> EntityType.Builder.<IronFishingHook>of(IronFishingHook::new, MobCategory.MISC)
                    .sized(0.25f, 0.25f).build(IRON_FISHING_BOBBER_KEY));
    public static final Supplier<EntityType<DiamondFishingHook>> DIAMOND_FISHING_BOBBER =
            ENTITY_TYPES.register("diamond_fishing_bobber", () -> EntityType.Builder.<DiamondFishingHook>of(DiamondFishingHook::new, MobCategory.MISC)
                    .sized(0.25f, 0.25f).build(DIAMOND_FISHING_BOBBER_KEY));
    public static final Supplier<EntityType<NetheriteFishingHook>> NETHERITE_FISHING_BOBBER =
            ENTITY_TYPES.register("netherite_fishing_bobber", () -> EntityType.Builder.<NetheriteFishingHook>of(NetheriteFishingHook::new, MobCategory.MISC)
                    .sized(0.25f, 0.25f).build(NETHERITE_FISHING_BOBBER_KEY));
    public static final Supplier<EntityType<MagmaticFishingHook>> MAGMATIC_FISHING_BOBBER =
            ENTITY_TYPES.register("magmatic_fishing_bobber", () -> EntityType.Builder.<MagmaticFishingHook>of(MagmaticFishingHook::new, MobCategory.MISC)
                    .sized(0.25f, 0.25f).build(MAGMATIC_FISHING_BOBBER_KEY));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
