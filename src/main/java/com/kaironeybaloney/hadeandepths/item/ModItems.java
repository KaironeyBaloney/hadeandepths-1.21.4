package com.kaironeybaloney.hadeandepths.item;

import com.kaironeybaloney.hadeandepths.HadeanDepths;
import com.kaironeybaloney.hadeandepths.block.ModBlocks;
import com.kaironeybaloney.hadeandepths.item.armor.ModArmorMaterials;
import com.kaironeybaloney.hadeandepths.item.armor.ModToolMaterials;
import com.kaironeybaloney.hadeandepths.item.custom.*;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collections;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(HadeanDepths.MODID);

    public static final DeferredItem<Item> FISH_BONE = ITEMS.registerItem("fish_bone",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> HUGE_BONE = ITEMS.registerItem("huge_bone",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> GIANT_WHISKER = ITEMS.registerItem("giant_whisker",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> GLOWY_GOOP = ITEMS.registerItem("glowy_goop",
            properties -> new GlowInkSacItem(properties.stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> SHARP_TOOTH = ITEMS.registerItem("sharp_tooth",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> SHARK_TOOTH = ITEMS.registerItem("shark_tooth",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> FORKED_SHARK_TOOTH = ITEMS.registerItem("forked_shark_tooth",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> SERPENT_FIN = ITEMS.registerItem("serpent_fin",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> HUGE_AMETHYST_SHARD = ITEMS.registerItem("huge_amethyst_shard",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> PHANTOM_JELLY_SILK = ITEMS.registerItem("phantom_jelly_silk",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> SHELL_FRAGMENT = ITEMS.registerItem("shell_fragment",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> LARGE_SCALES = ITEMS.registerItem("large_scales",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> SQUID_EYE = ITEMS.registerItem("squid_eye",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> PRISMARINE_ROD = ITEMS.registerItem("prismarine_rod",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> LEGENDARY_HEART = ITEMS.registerItem("legendary_heart",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> DEEP_SEA_AMALGAM = ITEMS.registerItem("deep_sea_amalgam",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> DEEP_SEA_RESIDUE = ITEMS.registerItem("deep_sea_residue",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> DUNKLEOSTEUS_PLATING = ITEMS.registerItem("dunkleosteus_plating",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> SAND_DOLLAR = ITEMS.registerItem("sand_dollar",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> MAGMATIC_UPGRADE_SMITHING_TEMPLATE = ITEMS.registerItem("magmatic_upgrade_smithing_template",
            properties -> new MagmaticUpgradeSmithingTemplateItem(properties.stacksTo(64).rarity((Rarity.UNCOMMON))));
    public static final DeferredItem<Item> TOOTH_ARROW = ITEMS.registerItem("tooth_arrow",
            properties -> new ToothArrowItem(properties.stacksTo(64).rarity((Rarity.COMMON))));

    public static final DeferredItem<Item> GLASS_JAR = ITEMS.registerItem("glass_jar",
            properties -> new GlassJarItem(properties.stacksTo(16).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> WATER_JAR = ITEMS.registerItem("water_jar",
            properties -> new Item(properties.stacksTo(1).rarity(Rarity.COMMON)));

    public static final DeferredItem<Item> BLUBBER = ITEMS.registerItem("blubber",
            properties -> new FuelItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(4)
                    .saturationModifier(0.5F)
                    .build()), 3200));
    public static final DeferredItem<Item> PUFFERFISH_LIVER = ITEMS.registerItem("pufferfish_liver",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON).component(DataComponents.CONSUMABLE, Consumable.builder()
                    .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.POISON, 450,4))).build())));
    public static final DeferredItem<Item> RAW_FILLET = ITEMS.registerItem("raw_fillet",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.3F)
                    .build())));
    public static final DeferredItem<Item> COOKED_FILLET = ITEMS.registerItem("cooked_fillet",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(6)
                    .saturationModifier(0.6F)
                    .build())));
    public static final DeferredItem<Item> RAW_SHELLFISH = ITEMS.registerItem("raw_shellfish",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.3F)
                    .build())));
    public static final DeferredItem<Item> COOKED_SHELLFISH = ITEMS.registerItem("cooked_shellfish",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(4)
                    .saturationModifier(0.6F)
                    .build())));
    public static final DeferredItem<Item> RAW_HUGE_FILLET = ITEMS.registerItem("raw_huge_fillet",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(6)
                    .saturationModifier(0.5F)
                    .build())));
    public static final DeferredItem<Item> COOKED_HUGE_FILLET = ITEMS.registerItem("cooked_huge_fillet",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(12)
                    .saturationModifier(0.6F)
                    .build())));
    public static final DeferredItem<Item> RAW_TUNA_FILLET = ITEMS.registerItem("raw_tuna_fillet",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(6)
                    .saturationModifier(0.6F)
                    .build())));
    public static final DeferredItem<Item> COOKED_TUNA_FILLET = ITEMS.registerItem("cooked_tuna_fillet",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(12)
                    .saturationModifier(0.6F)
                    .build())));
    public static final DeferredItem<Item> FISH_STEW = ITEMS.registerItem("fish_stew",
            properties -> new Item(properties.stacksTo(16).rarity(Rarity.COMMON).usingConvertsTo(Items.BOWL).food(new FoodProperties.Builder()
                    .nutrition(12)
                    .saturationModifier(0.6F)
                    .build())));
    public static final DeferredItem<Item> FISH_SANDWICH = ITEMS.registerItem("fish_sandwich",
            properties -> new Item(properties.stacksTo(16).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(8)
                    .saturationModifier(0.8F)
                    .build())));
    public static final DeferredItem<Item> TENTACLE = ITEMS.registerItem("tentacle",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.3F)
                    .build())));
    public static final DeferredItem<Item> COOKED_TENTACLE = ITEMS.registerItem("cooked_tentacle",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(5)
                    .saturationModifier(1F)
                    .build())));
    public static final DeferredItem<Item> FIN = ITEMS.registerItem("fin",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(1)
                    .saturationModifier(0.5F)
                    .build())));

    public static final DeferredItem<Item> BAKED_FIN = ITEMS.registerItem("baked_fin",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.5F)
                    .build())));

    public static final DeferredItem<Item> REAPER_FIN = ITEMS.registerItem("reaper_fin",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.3F)
                    .build()), FishRarity.UNCOMMON, FishType.OCEANIC));
    public static final DeferredItem<Item> FISH = ITEMS.registerItem("fish",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.3F)
                    .build()), FishRarity.RARE, FishType.FABLED));
    public static final DeferredItem<Item> MINN_O_WISP = ITEMS.registerItem("minn_o_wisp",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.3F)
                    .build()), FishRarity.RARE, FishType.FABLED));
    public static final DeferredItem<Item> ZOMBIE_FISH = ITEMS.registerItem("zombie_fish",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.2F)
                    .build()), FishRarity.UNCOMMON, FishType.INLAND));
    public static final DeferredItem<Item> GOLD_FISH = ITEMS.registerItem("gold_fish",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.3F)
                    .build()), FishRarity.RARE, FishType.CAVERNOUS));
    public static final DeferredItem<Item> EEL = ITEMS.registerItem("eel",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.2F)
                    .build()), FishRarity.COMMON, FishType.OCEANIC));
    public static final DeferredItem<Item> ELECTRIC_EEL = ITEMS.registerItem("electric_eel",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.2F)
                    .build()), FishRarity.UNCOMMON, FishType.OCEANIC));
    public static final DeferredItem<Item> CARP = ITEMS.registerItem("carp",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.2F)
                    .build()), FishRarity.COMMON, FishType.INLAND));
    public static final DeferredItem<Item> LAMPLIGHTER = ITEMS.registerItem("lamplighter",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.2F)
                    .build()), FishRarity.UNCOMMON, FishType.ABYSSAL));
    public static final DeferredItem<Item> PINK_NITELITE = ITEMS.registerItem("pink_nitelite",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.2F)
                    .build()), FishRarity.COMMON, FishType.OCEANIC, FishAttachPoint.TOP_RIGHT));
    public static final DeferredItem<Item> BLUE_NITELITE = ITEMS.registerItem("blue_nitelite",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.2F)
                    .build()), FishRarity.COMMON, FishType.GLACIAL, FishAttachPoint.TOP_RIGHT));
    public static final DeferredItem<Item> GREEN_NITELITE = ITEMS.registerItem("green_nitelite",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.2F)
                    .build()), FishRarity.COMMON, FishType.OCEANIC, FishAttachPoint.TOP_RIGHT));
    public static final DeferredItem<Item> TRILOBITE = ITEMS.registerItem("trilobite",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.2F)
                    .build()), FishRarity.COMMON, FishType.ANCIENT));
    public static final DeferredItem<Item> NAUTILUS = ITEMS.registerItem("nautilus",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.2F)
                    .build()), FishRarity.RARE, FishType.ANCIENT));
    public static final DeferredItem<Item> GLOW_FISH = ITEMS.registerItem("glow_fish",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.2F)
                    .build()), FishRarity.UNCOMMON, FishType.CAVERNOUS));
    public static final DeferredItem<Item> COELACANTH = ITEMS.registerItem("coelacanth",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.3F)
                    .build()), FishRarity.RARE, FishType.ANCIENT));
    public static final DeferredItem<Item> HORSESHOE_CRAB = ITEMS.registerItem("horseshoe_crab",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.2F)
                    .build()), FishRarity.COMMON, FishType.ANCIENT));
    public static final DeferredItem<Item> GHOST_FISH = ITEMS.registerItem("ghost_fish",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.2F)
                    .build()), FishRarity.COMMON, FishType.CAVERNOUS));
    public static final DeferredItem<Item> CAVE_CARP = ITEMS.registerItem("cave_carp",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.2F)
                    .build()), FishRarity.COMMON, FishType.CAVERNOUS));
    public static final DeferredItem<Item> BLOBFISH = ITEMS.registerItem("blobfish",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.3F)
                    .build()), FishRarity.RARE, FishType.ABYSSAL));
    public static final DeferredItem<Item> LANTERN_FISH = ITEMS.registerItem("lantern_fish",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.2F)
                    .build()), FishRarity.COMMON, FishType.ABYSSAL));
    public static final DeferredItem<Item> BARRACUDA = ITEMS.registerItem("barracuda",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.3F)
                    .build()), FishRarity.RARE, FishType.OCEANIC));
    public static final DeferredItem<Item> TIGER_FISH = ITEMS.registerItem("tiger_fish",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.3F)
                    .build()), FishRarity.RARE, FishType.INLAND));
    public static final DeferredItem<Item> ICY_KRILL = ITEMS.registerItem("icy_krill",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.3F)
                    .build()), FishRarity.COMMON, FishType.GLACIAL));
    public static final DeferredItem<Item> TROUT = ITEMS.registerItem("trout",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.4F)
                    .build()), FishRarity.COMMON, FishType.INLAND));
    public static final DeferredItem<Item> FUR_BEARING_TROUT = ITEMS.registerItem("fur_bearing_trout",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.4F)
                    .build()), FishRarity.UNCOMMON, FishType.FABLED));
    public static final DeferredItem<Item> ICICLE_FISH = ITEMS.registerItem("icicle_fish",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.5F)
                    .build()), FishRarity.UNCOMMON, FishType.GLACIAL));
    public static final DeferredItem<Item> FROZEN_FISH = ITEMS.registerItem("frozen_fish",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.5F)
                    .build()), FishRarity.RARE, FishType.GLACIAL, FishAttachPoint.TOP_MIDDLE));
    public static final DeferredItem<Item> BASSIGATOR = ITEMS.registerItem("bassigator",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.5F)
                    .build()), FishRarity.RARE, FishType.FABLED));
    public static final DeferredItem<Item> SLACK_JAW = ITEMS.registerItem("slack_jaw",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.5F)
                    .build()), FishRarity.UNCOMMON, FishType.ABYSSAL));
    public static final DeferredItem<Item> RED_HERRING = ITEMS.registerItem("red_herring",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.5F)
                    .build()), FishRarity.COMMON, FishType.FABLED));
    public static final DeferredItem<Item> METAMORFIN = ITEMS.registerItem("metamorfin",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).fireResistant().food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.5F)
                    .build()), FishRarity.COMMON, FishType.NETHEROUS));
    public static final DeferredItem<Item> LAVA_JELLY = ITEMS.registerItem("lava_jelly",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).fireResistant().food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.5F)
                    .build()), FishRarity.UNCOMMON, FishType.MAGMATIC, FishAttachPoint.TOP_RIGHT));
    public static final DeferredItem<Item> TWISTED_EEL = ITEMS.registerItem("twisted_eel",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).fireResistant().food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.5F)
                    .build()), FishRarity.COMMON, FishType.NETHEROUS));
    public static final DeferredItem<Item> HELIOS = ITEMS.registerItem("helios",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).fireResistant().food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.5F)
                    .build()), FishRarity.RARE, FishType.MAGMATIC));
    public static final DeferredItem<Item> MAGMA_GUT = ITEMS.registerItem("magma_gut",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).fireResistant().food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.5F)
                    .build()), FishRarity.UNCOMMON, FishType.MAGMATIC));
    public static final DeferredItem<Item> OBSIDIAN_SHARD_FIN = ITEMS.registerItem("obsidian_shard_fin",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).fireResistant().food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.5F)
                    .build()), FishRarity.COMMON, FishType.MAGMATIC));
    public static final DeferredItem<Item> BRUTISH_HOG_SUCKER = ITEMS.registerItem("brutish_hog_sucker",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).fireResistant().food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.5F)
                    .build()), FishRarity.RARE, FishType.NETHEROUS));
    public static final DeferredItem<Item> HOG_SUCKER = ITEMS.registerItem("hog_sucker",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).fireResistant().food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.5F)
                    .build()), FishRarity.UNCOMMON, FishType.NETHEROUS));
    public static final DeferredItem<Item> ZOMBIE_HOG_SUCKER = ITEMS.registerItem("zombie_hog_sucker",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).fireResistant().food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.5F)
                    .build()), FishRarity.COMMON, FishType.NETHEROUS));
    public static final DeferredItem<Item> CRIMSON_SHROOMLITE = ITEMS.registerItem("crimson_shroomlite",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).fireResistant().food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.5F)
                    .build()), FishRarity.UNCOMMON, FishType.NETHEROUS, FishAttachPoint.TOP_RIGHT));
    public static final DeferredItem<Item> WARPED_SHROOMLITE = ITEMS.registerItem("warped_shroomlite",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).fireResistant().food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.5F)
                    .build()), FishRarity.UNCOMMON, FishType.NETHEROUS, FishAttachPoint.TOP_RIGHT));
    public static final DeferredItem<Item> INFERNO_EEL = ITEMS.registerItem("inferno_eel",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).fireResistant().food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.5F)
                    .build()), FishRarity.UNCOMMON, FishType.MAGMATIC));
    public static final DeferredItem<Item> SOULFERNO_EEL = ITEMS.registerItem("soulferno_eel",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).fireResistant().food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.5F)
                    .build()), FishRarity.RARE, FishType.NETHEROUS));




    public static final DeferredItem<Item> GREAT_WHITE_SHARK = ITEMS.registerItem("great_white_shark",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.RARE), FishRarity.LEGENDARY, FishType.OCEANIC));
    public static final DeferredItem<Item> TUNA = ITEMS.registerItem("tuna",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.RARE), FishRarity.LEGENDARY, FishType.OCEANIC));
    public static final DeferredItem<Item> COLOSSAL_SQUID = ITEMS.registerItem("colossal_squid",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.RARE), FishRarity.LEGENDARY, FishType.ABYSSAL, FishAttachPoint.TOP_RIGHT));
    public static final DeferredItem<Item> DUNKLEOSTEUS = ITEMS.registerItem("dunkleosteus",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.RARE), FishRarity.LEGENDARY, FishType.ANCIENT));
    public static final DeferredItem<Item> SEA_SERPENT = ITEMS.registerItem("sea_serpent",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.RARE), FishRarity.LEGENDARY, FishType.FABLED));
    public static final DeferredItem<Item> AMETHYST_GROUPER = ITEMS.registerItem("amethyst_grouper",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.RARE), FishRarity.LEGENDARY, FishType.CAVERNOUS));
    public static final DeferredItem<Item> GIANT_CATFISH = ITEMS.registerItem("giant_catfish",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.RARE), FishRarity.LEGENDARY, FishType.INLAND));
    public static final DeferredItem<Item> STURGEON = ITEMS.registerItem("sturgeon",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.RARE), FishRarity.LEGENDARY, FishType.INLAND));
    public static final DeferredItem<Item> FRILLED_SHARK = ITEMS.registerItem("frilled_shark",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.RARE), FishRarity.LEGENDARY, FishType.ABYSSAL));
    public static final DeferredItem<Item> ORCA = ITEMS.registerItem("orca",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.RARE), FishRarity.LEGENDARY, FishType.GLACIAL));
    public static final DeferredItem<Item> PHANTOM_JELLYFISH = ITEMS.registerItem("phantom_jellyfish",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.RARE), FishRarity.LEGENDARY, FishType.ABYSSAL, FishAttachPoint.TOP_RIGHT));
    public static final DeferredItem<Item> MEGALODON = ITEMS.registerItem("megalodon",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.RARE), FishRarity.LEGENDARY, FishType.ANCIENT));
    public static final DeferredItem<Item> NETHERITIC_CONTRAPTION = ITEMS.registerItem("netheritic_contraption",
            properties -> new FishItem(properties.stacksTo(64).fireResistant().rarity(Rarity.RARE), FishRarity.LEGENDARY, FishType.NETHEROUS));

    public static final DeferredItem<Item> BUG_FISH = ITEMS.registerItem("bug_fish",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON), FishRarity.IMPOSSIBLE, FishType.UNEXPLAINED));



    public static final DeferredItem<Item> TWIG_ROD = ITEMS.registerItem("twig_rod",
            properties -> new TwigRodItem(properties.stacksTo(1).rarity(Rarity.COMMON).durability(32)));
    public static final DeferredItem<Item> IRON_FISHING_ROD = ITEMS.registerItem("iron_fishing_rod",
            properties -> new IronFishingRodItem(properties.stacksTo(1).rarity(Rarity.COMMON).durability(64)));
    public static final DeferredItem<Item> REINFORCED_ROD = ITEMS.registerItem("reinforced_rod",
            properties -> new ReinforcedRodItem(properties.stacksTo(1).rarity(Rarity.COMMON).durability(128)));
    public static final DeferredItem<Item> NETHERITE_ROD = ITEMS.registerItem("netherite_rod",
            properties -> new NetheriteRodItem(properties.stacksTo(1).rarity(Rarity.COMMON).durability(256).fireResistant()));
    public static final DeferredItem<Item> MAGMATIC_ROD = ITEMS.registerItem("magmatic_rod",
            properties -> new MagmaticRodItem(properties.stacksTo(1).rarity(Rarity.UNCOMMON).durability(512).fireResistant()));
    public static final DeferredItem<Item> SERPENT_BONE_BOW = ITEMS.registerItem("serpent_bone_bow",
            properties -> new SerpentBoneBowItem(properties.stacksTo(1).rarity(Rarity.UNCOMMON).durability(512)));
    public static final DeferredItem<Item> TIDAL_PICKAXE = ITEMS.registerItem("tidal_pickaxe",
            properties -> new PickaxeItem(ModToolMaterials.TIDAL_TOOL, 2, -2.8f, properties.rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> TIDAL_SHOVEL = ITEMS.registerItem("tidal_shovel",
            properties -> new ShovelItem(ModToolMaterials.TIDAL_TOOL, 2.5f, 0, properties.rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> TIDAL_AXE = ITEMS.registerItem("tidal_axe",
            properties -> new AxeItem(ModToolMaterials.TIDAL_TOOL, 6, -3f, properties.rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> TIDAL_HOE = ITEMS.registerItem("tidal_hoe",
            properties -> new HoeItem(ModToolMaterials.TIDAL_TOOL, -2, 0, properties.rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> TIDAL_SWORD = ITEMS.registerItem("tidal_sword",
            properties -> new SwordItem(ModToolMaterials.TIDAL_TOOL, 4, -2.4f, properties.rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> TIDAL_MORNING_STAR = ITEMS.registerItem("tidal_morning_star",
            properties -> new TidalMorningStarItem(ModToolMaterials.TIDAL_TOOL, 5, -2.6f, 20, properties.rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> TIDAL_SLEDGE = ITEMS.registerItem("tidal_sledge",
            properties -> new TidalSledgeItem(ModToolMaterials.TIDAL_TOOL, 9, -3.2f, properties.rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<Item> TIDAL_HELMET = ITEMS.registerItem("tidal_helmet",
            properties -> new TidalArmorItem(ModArmorMaterials.TIDAL_ARMOR_MATERIAL, ArmorType.HELMET, properties.rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> TIDAL_CHESTPLATE = ITEMS.registerItem("tidal_chestplate",
            properties -> new TidalArmorItem(ModArmorMaterials.TIDAL_ARMOR_MATERIAL, ArmorType.CHESTPLATE, properties.rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> TIDAL_LEGGINGS = ITEMS.registerItem("tidal_leggings",
            properties -> new TidalArmorItem(ModArmorMaterials.TIDAL_ARMOR_MATERIAL, ArmorType.LEGGINGS, properties.rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> TIDAL_BOOTS = ITEMS.registerItem("tidal_boots",
            properties -> new TidalArmorItem(ModArmorMaterials.TIDAL_ARMOR_MATERIAL, ArmorType.BOOTS, properties.rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<Item> BLUE_NITELITE_JELLYFISH = ITEMS.registerItem("blue_nitelite_jar_jellyfish",
            properties -> new Item(properties.stacksTo(1).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> PINK_NITELITE_JELLYFISH = ITEMS.registerItem("pink_nitelite_jar_jellyfish",
            properties -> new Item(properties.stacksTo(1).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> GREEN_NITELITE_JELLYFISH = ITEMS.registerItem("green_nitelite_jar_jellyfish",
            properties -> new Item(properties.stacksTo(1).rarity(Rarity.COMMON)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
