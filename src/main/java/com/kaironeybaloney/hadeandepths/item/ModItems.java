package com.kaironeybaloney.hadeandepths.item;

import com.kaironeybaloney.hadeandepths.HadeanDepths;
import com.kaironeybaloney.hadeandepths.block.ModBlocks;
import com.kaironeybaloney.hadeandepths.item.custom.*;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collections;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(HadeanDepths.MODID);


    public static final DeferredItem<Item> FISH_BONE = ITEMS.registerItem("fish_bone",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> GLOWY_GOOP = ITEMS.registerItem("glowy_goop",
            properties -> new GlowInkSacItem(properties.stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> SHARP_TOOTH = ITEMS.registerItem("sharp_tooth",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> SHELL_FRAGMENT = ITEMS.registerItem("shell_fragment",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> LARGE_SCALES = ITEMS.registerItem("large_scales",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> SQUID_EYE = ITEMS.registerItem("squid_eye",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> SAND_DOLLAR = ITEMS.registerItem("sand_dollar",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> MAGMATIC_UPGRADE_SMITHING_TEMPLATE = ITEMS.registerItem("magmatic_upgrade_smithing_template",
            properties -> new MagmaticUpgradeSmithingTemplateItem(properties.stacksTo(64).rarity((Rarity.UNCOMMON))));

    public static final DeferredItem<Item> GLASS_JAR = ITEMS.registerItem("glass_jar",
            properties -> new GlassJarItem(properties.stacksTo(16).rarity(Rarity.COMMON)));
    public static final DeferredItem<Item> WATER_JAR = ITEMS.registerItem("water_jar",
            properties -> new Item(properties.stacksTo(1).rarity(Rarity.COMMON)));

    public static final DeferredItem<Item> RAW_FILLET = ITEMS.registerItem("raw_fillet",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.3F)
                    .build())));
    public static final DeferredItem<Item> COOKED_FILLET = ITEMS.registerItem("cooked_fillet",
            properties -> new Item(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(6)
                    .saturationModifier(1.3F)
                    .build())));

    public static final DeferredItem<Item> REAPER_FIN = ITEMS.registerItem("reaper_fin",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.3F)
                    .build()), FishRarity.RARE, FishType.OCEANIC));
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
                    .build()), FishRarity.COMMON, FishType.OCEANIC));
    public static final DeferredItem<Item> BLUE_NITELITE = ITEMS.registerItem("blue_nitelite",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.2F)
                    .build()), FishRarity.COMMON, FishType.OCEANIC));
    public static final DeferredItem<Item> GREEN_NITELITE = ITEMS.registerItem("green_nitelite",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.2F)
                    .build()), FishRarity.COMMON, FishType.OCEANIC));
    public static final DeferredItem<Item> TRILOBITE = ITEMS.registerItem("trilobite",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.2F)
                    .build()), FishRarity.COMMON, FishType.ANCIENT));
    public static final DeferredItem<Item> NAUTILUS = ITEMS.registerItem("nautilus",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.2F)
                    .build()), FishRarity.UNCOMMON, FishType.ANCIENT));
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

    public static final DeferredItem<Item> GREAT_WHITE_SHARK = ITEMS.registerItem("great_white_shark",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON), FishRarity.LEGENDARY, FishType.OCEANIC));
    public static final DeferredItem<Item> TUNA = ITEMS.registerItem("tuna",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON), FishRarity.LEGENDARY, FishType.OCEANIC));
    public static final DeferredItem<Item> COLOSSAL_SQUID = ITEMS.registerItem("colossal_squid",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON), FishRarity.LEGENDARY, FishType.ABYSSAL));
    public static final DeferredItem<Item> DUNKLEOSTEUS = ITEMS.registerItem("dunkleosteus",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON), FishRarity.LEGENDARY, FishType.ANCIENT));
    public static final DeferredItem<Item> SEA_SERPENT = ITEMS.registerItem("sea_serpent",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON), FishRarity.LEGENDARY, FishType.FABLED));
    public static final DeferredItem<Item> AMETHYST_GROUPER = ITEMS.registerItem("amethyst_grouper",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON), FishRarity.LEGENDARY, FishType.CAVERNOUS));
    public static final DeferredItem<Item> GIANT_CATFISH = ITEMS.registerItem("giant_catfish",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON), FishRarity.LEGENDARY, FishType.INLAND));
    public static final DeferredItem<Item> STURGEON = ITEMS.registerItem("sturgeon",
            properties -> new FishItem(properties.stacksTo(64).rarity(Rarity.COMMON), FishRarity.LEGENDARY, FishType.INLAND));

    public static final DeferredItem<Item> TWIG_ROD = ITEMS.registerItem("twig_rod",
            properties -> new TwigRodItem(properties.stacksTo(1).rarity(Rarity.COMMON).durability(32)));
    public static final DeferredItem<Item> REINFORCED_ROD = ITEMS.registerItem("reinforced_rod",
            properties -> new ReinforcedRodItem(properties.stacksTo(1).rarity(Rarity.COMMON).durability(128)));
    public static final DeferredItem<Item> NETHERITE_ROD = ITEMS.registerItem("netherite_rod",
            properties -> new NetheriteRodItem(properties.stacksTo(1).rarity(Rarity.COMMON).durability(256)));
    public static final DeferredItem<Item> MAGMATIC_ROD = ITEMS.registerItem("magmatic_rod",
            properties -> new MagmaticRodItem(properties.stacksTo(1).rarity(Rarity.COMMON).durability(512)));

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
