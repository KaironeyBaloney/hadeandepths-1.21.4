package com.kaironeybaloney.hadeandepths.datagen.advancements;

import com.kaironeybaloney.hadeandepths.block.ModBlocks;
import com.kaironeybaloney.hadeandepths.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModAdvancementGenerator implements AdvancementSubProvider {
    @Override
    public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer) {
        createCollectAllFishAdvancement(consumer);
        createCollectAllOceanicFishAdvancement(consumer);
        createCollectAllAbyssalFishAdvancement(consumer);
        createCollectAllInlandFishAdvancement(consumer);
        createCollectAllAncientFishAdvancement(consumer);
        createCollectAllFabledFishAdvancement(consumer);
        createCollectAllCavernousFishAdvancement(consumer);
        createFindDavyJonesLockerAdvancement(consumer);
        createNiteliteJarAdvancement(consumer);
        createButcheringHookAdvancement(consumer);
        createToothArrowAdvancement(consumer);
        createCollectFirstLegendaryFishAdvancement(consumer);
        createCollectAllGlacialFishAdvancement(consumer);
        createDeepSeaResidueAdvancement(consumer);
        createCollectAllTidalArmorAdvancement(consumer);
        createTidalSledgeAdvancement(consumer);
        createTidalMorningStarAdvancement(consumer);
        createTidalBowAdvancement(consumer);
    }

    void createCollectAllGlacialFishAdvancement(Consumer<AdvancementHolder> consumer)
    {
        {
            final List<Supplier<Item>> GLACIAL_FISH_ITEMS = List.of(
                    ModItems.ICY_KRILL, ModItems.ICICLE_FISH, ModItems.FROZEN_FISH, ModItems.ORCA, ModItems.BLUE_NITELITE
            );
            Advancement.Builder builder = Advancement.Builder.advancement()
                    .display(
                            ModItems.FROZEN_FISH,
                            Component.literal("Ice Fishing"),
                            Component.literal("Collect every Glacial Fish"),
                            null, AdvancementType.GOAL,
                            true, true, false
                    );

            for (Supplier<Item> fishSupplier : GLACIAL_FISH_ITEMS) {
                Item fishItem = fishSupplier.get();
                ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(fishItem);

                builder.addCriterion("has_" + itemId.getPath(),
                        InventoryChangeTrigger.TriggerInstance.hasItems(fishItem));
            }

            builder.parent(ResourceLocation.fromNamespaceAndPath("hadeandepths", "fishing/first_fish"));

            builder.save(consumer, "hadeandepths:collect_all_glacial_fish");
        }
    }

    void createCollectFirstLegendaryFishAdvancement(Consumer<AdvancementHolder> consumer)
    {
        {
            final List<Supplier<Item>> LEGENDARY_FISH_ITEMS = List.of(
                    ModItems.DUNKLEOSTEUS, ModItems.SEA_SERPENT, ModItems.GIANT_CATFISH, ModItems.GREAT_WHITE_SHARK, ModItems.COLOSSAL_SQUID, ModItems.STURGEON,
                    ModItems.AMETHYST_GROUPER, ModItems.TUNA, ModItems.FRILLED_SHARK, ModItems.PHANTOM_JELLYFISH, ModItems.MEGALODON
            );
            Advancement.Builder builder = Advancement.Builder.advancement()
                    .display(
                            ModItems.GREAT_WHITE_SHARK,
                            Component.literal("Legendary Fisherman"),
                            Component.literal("Collect your first Legendary Fish"),
                            null, AdvancementType.TASK,
                            true, true, false
                    );

            for (Supplier<Item> fishSupplier : LEGENDARY_FISH_ITEMS) {
                Item fishItem = fishSupplier.get();
                ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(fishItem);

                builder.addCriterion("has_" + itemId.getPath(),
                        InventoryChangeTrigger.TriggerInstance.hasItems(fishItem));
            }

            builder.requirements(AdvancementRequirements.Strategy.OR);

            builder.parent(ResourceLocation.fromNamespaceAndPath("hadeandepths", "fishing/first_fish"));

            builder.save(consumer, "hadeandepths:collect_first_legendary_fish");
        }
    }
    void createNiteliteJarAdvancement(Consumer<AdvancementHolder> consumer)
    {
        {
            Advancement.Builder builder = Advancement.Builder.advancement()
                    .display(
                            ModBlocks.BLUE_NITELITE_JAR,
                            Component.literal("Jellyfish Museum"),
                            Component.literal("Craft a Nitelite in a jar"),
                            null, AdvancementType.TASK,
                            true, true, false
                    );
            builder.addCriterion("has_blue_nitelite",
                    InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BLUE_NITELITE_JAR));
            builder.addCriterion("has_green_nitelite",
                    InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GREEN_NITELITE_JAR));
            builder.addCriterion("has_pink_nitelite",
                    InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.PINK_NITELITE_JAR));
            builder.requirements(AdvancementRequirements.Strategy.OR);
            builder.parent(ResourceLocation.fromNamespaceAndPath("hadeandepths", "fishing/root"));

            builder.save(consumer, "hadeandepths:craft_nitelite_in_a_jar");
        }
    }

    void createButcheringHookAdvancement(Consumer<AdvancementHolder> consumer)
    {
        {
            Advancement.Builder builder = Advancement.Builder.advancement()
                    .display(
                            ModBlocks.BUTCHERING_HOOK,
                            Component.literal("Hooked"),
                            Component.literal("Craft a Butchering Hook"),
                            null, AdvancementType.TASK,
                            true, true, false
                    );
            builder.addCriterion("has_butchering_hook",
                    InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BUTCHERING_HOOK));

            builder.parent(ResourceLocation.fromNamespaceAndPath("hadeandepths", "fishing/root"));

            builder.save(consumer, "hadeandepths:craft_butchering_hook");
        }
    }

    void createDeepSeaResidueAdvancement(Consumer<AdvancementHolder> consumer)
    {
        {
            Advancement.Builder builder = Advancement.Builder.advancement()
                    .display(
                            ModItems.DEEP_SEA_RESIDUE,
                            Component.literal("Abyssal Remnants"),
                            Component.literal("Find a Deep Sea Residue"),
                            null, AdvancementType.TASK,
                            true, true, false
                    );
            builder.addCriterion("has_deep_sea_residue",
                    InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEEP_SEA_RESIDUE));

            builder.parent(ResourceLocation.fromNamespaceAndPath("hadeandepths", "fishing/root"));

            builder.save(consumer, "hadeandepths:find_deep_sea_residue");
        }
    }

    void createTidalBowAdvancement(Consumer<AdvancementHolder> consumer)
    {
        {
            Advancement.Builder builder = Advancement.Builder.advancement()
                    .display(
                            ModItems.SERPENT_BONE_BOW,
                            Component.literal("The Eb and Flow of the Tides"),
                            Component.literal("Craft a Tidal Bow"),
                            null, AdvancementType.GOAL,
                            true, true, false
                    );
            builder.addCriterion("has_tidal_bow",
                    InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SERPENT_BONE_BOW));

            builder.parent(ResourceLocation.fromNamespaceAndPath("hadeandepths", "find_deep_sea_residue"));

            builder.save(consumer, "hadeandepths:craft_tidal_bow");
        }
    }

    void createTidalMorningStarAdvancement(Consumer<AdvancementHolder> consumer)
    {
        {
            Advancement.Builder builder = Advancement.Builder.advancement()
                    .display(
                            ModItems.TIDAL_MORNING_STAR,
                            Component.literal("Sea Urchin on a Stick"),
                            Component.literal("Craft a Tidal Morning Star"),
                            null, AdvancementType.GOAL,
                            true, true, false
                    );
            builder.addCriterion("has_tidal_morning_star",
                    InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIDAL_MORNING_STAR));

            builder.parent(ResourceLocation.fromNamespaceAndPath("hadeandepths", "find_deep_sea_residue"));

            builder.save(consumer, "hadeandepths:craft_tidal_morning_star");
        }
    }
    void createTidalSledgeAdvancement(Consumer<AdvancementHolder> consumer)
    {
        {
            Advancement.Builder builder = Advancement.Builder.advancement()
                    .display(
                            ModItems.TIDAL_SLEDGE,
                            Component.literal("With All the Force of A Great Typhoon"),
                            Component.literal("Craft a Tidal Sledge"),
                            null, AdvancementType.GOAL,
                            true, true, false
                    );
            builder.addCriterion("has_tidal_sledge",
                    InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TIDAL_SLEDGE));

            builder.parent(ResourceLocation.fromNamespaceAndPath("hadeandepths", "find_deep_sea_residue"));

            builder.save(consumer, "hadeandepths:craft_tidal_sledge");
        }
    }

    void createCollectAllTidalArmorAdvancement(Consumer<AdvancementHolder> consumer)
    {
        {
            final List<Supplier<Item>> CAVERNOUS_FISH_ITEMS = List.of(
                    ModItems.TIDAL_HELMET, ModItems.TIDAL_CHESTPLATE, ModItems.TIDAL_LEGGINGS, ModItems.TIDAL_BOOTS
            );
            Advancement.Builder builder = Advancement.Builder.advancement()
                    .display(
                            ModItems.TIDAL_CHESTPLATE,
                            Component.literal("Cover Me in the Sea"),
                            Component.literal("Collect every Tidal Armor Piece"),
                            null, AdvancementType.CHALLENGE,
                            true, true, false
                    );

            for (Supplier<Item> fishSupplier : CAVERNOUS_FISH_ITEMS) {
                Item fishItem = fishSupplier.get();
                ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(fishItem);

                builder.addCriterion("has_" + itemId.getPath(),
                        InventoryChangeTrigger.TriggerInstance.hasItems(fishItem));
            }

            builder.parent(ResourceLocation.fromNamespaceAndPath("hadeandepths", "find_deep_sea_residue"));

            builder.save(consumer, "hadeandepths:collect_all_tidal_armor");
        }
    }
    void createToothArrowAdvancement(Consumer<AdvancementHolder> consumer)
    {
        {
            Advancement.Builder builder = Advancement.Builder.advancement()
                    .display(
                            ModBlocks.BUTCHERING_HOOK,
                            Component.literal("Sharper Arrows"),
                            Component.literal("Craft a Tooth Arrow that works above and below"),
                            null, AdvancementType.TASK,
                            true, true, false
                    );
            builder.addCriterion("has_tooth_arrow",
                    InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.TOOTH_ARROW));

            builder.parent(ResourceLocation.fromNamespaceAndPath("hadeandepths", "fishing/craft_butchering_hook"));

            builder.save(consumer, "hadeandepths:craft_tooth_arrow");
        }
    }

    void createFindDavyJonesLockerAdvancement(Consumer<AdvancementHolder> consumer)
    {
        {
            Advancement.Builder builder = Advancement.Builder.advancement()
                    .display(
                            ModBlocks.DAVY_JONES_LOCKER,
                            Component.literal("Dead Men Tell No Tales"),
                            Component.literal("Fish up Davy Jones' Locker"),
                            null, AdvancementType.GOAL,
                            true, true, false
                    );
            builder.addCriterion("has_davy_jones_locker",
                    InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.DAVY_JONES_LOCKER));

            builder.parent(ResourceLocation.fromNamespaceAndPath("hadeandepths", "fishing/root"));

            builder.save(consumer, "hadeandepths:find_davy_jones_locker");
        }
    }

    void createCollectAllCavernousFishAdvancement(Consumer<AdvancementHolder> consumer)
    {
        {
            final List<Supplier<Item>> CAVERNOUS_FISH_ITEMS = List.of(
                    ModItems.GOLD_FISH, ModItems.GLOW_FISH, ModItems.GHOST_FISH, ModItems.CAVE_CARP, ModItems.AMETHYST_GROUPER
            );
            Advancement.Builder builder = Advancement.Builder.advancement()
                    .display(
                            ModItems.GOLD_FISH,
                            Component.literal("Fishy Subterranean Wonders"),
                            Component.literal("Collect every Cavernous Fish"),
                            null, AdvancementType.GOAL,
                            true, true, false
                    );

            for (Supplier<Item> fishSupplier : CAVERNOUS_FISH_ITEMS) {
                Item fishItem = fishSupplier.get();
                ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(fishItem);

                builder.addCriterion("has_" + itemId.getPath(),
                        InventoryChangeTrigger.TriggerInstance.hasItems(fishItem));
            }

            builder.parent(ResourceLocation.fromNamespaceAndPath("hadeandepths", "fishing/first_fish"));

            builder.save(consumer, "hadeandepths:collect_all_cavernous_fish");
        }
    }

    void createCollectAllFabledFishAdvancement(Consumer<AdvancementHolder> consumer)
    {
        {
            final List<Supplier<Item>> FABLED_FISH_ITEMS = List.of(
                    ModItems.MINN_O_WISP, ModItems.FISH, ModItems.SEA_SERPENT, ModItems.FUR_BEARING_TROUT, ModItems.BASSIGATOR,
                    ModItems.RED_HERRING
            );
            Advancement.Builder builder = Advancement.Builder.advancement()
                    .display(
                            ModItems.MINN_O_WISP,
                            Component.literal("The Things of Legend"),
                            Component.literal("Collect every Fabled Fish"),
                            null, AdvancementType.GOAL,
                            true, true, false
                    );

            for (Supplier<Item> fishSupplier : FABLED_FISH_ITEMS) {
                Item fishItem = fishSupplier.get();
                ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(fishItem);

                builder.addCriterion("has_" + itemId.getPath(),
                        InventoryChangeTrigger.TriggerInstance.hasItems(fishItem));
            }

            builder.parent(ResourceLocation.fromNamespaceAndPath("hadeandepths", "fishing/first_fish"));

            builder.save(consumer, "hadeandepths:collect_all_fabled_fish");
        }
    }

    void createCollectAllAncientFishAdvancement(Consumer<AdvancementHolder> consumer)
    {
        {
            final List<Supplier<Item>> ANCIENT_FISH_ITEMS = List.of(
                    ModItems.TRILOBITE, ModItems.NAUTILUS, ModItems.HORSESHOE_CRAB, ModItems.DUNKLEOSTEUS, ModItems.COELACANTH, ModItems.MEGALODON
            );
            Advancement.Builder builder = Advancement.Builder.advancement()
                    .display(
                            ModItems.COELACANTH,
                            Component.literal("The Living Fossils"),
                            Component.literal("Collect every Ancient Fish"),
                            null, AdvancementType.GOAL,
                            true, true, false
                    );

            for (Supplier<Item> fishSupplier : ANCIENT_FISH_ITEMS) {
                Item fishItem = fishSupplier.get();
                ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(fishItem);

                builder.addCriterion("has_" + itemId.getPath(),
                        InventoryChangeTrigger.TriggerInstance.hasItems(fishItem));
            }

            builder.parent(ResourceLocation.fromNamespaceAndPath("hadeandepths", "fishing/first_fish"));

            builder.save(consumer, "hadeandepths:collect_all_ancient_fish");
        }
    }

    void createCollectAllInlandFishAdvancement(Consumer<AdvancementHolder> consumer)
    {
        {
            final List<Supplier<Item>> INLAND_FISH_ITEMS = List.of(
                    ModItems.ZOMBIE_FISH, ModItems.CARP, ModItems.GIANT_CATFISH, ModItems.STURGEON, ModItems.TIGER_FISH, ModItems.TROUT
            );
            Advancement.Builder builder = Advancement.Builder.advancement()
                    .display(
                            ModItems.CARP,
                            Component.literal("Ain't no River Wide Enough"),
                            Component.literal("Collect every Inland Fish"),
                            null, AdvancementType.GOAL,
                            true, true, false
                    );

            for (Supplier<Item> fishSupplier : INLAND_FISH_ITEMS) {
                Item fishItem = fishSupplier.get();
                ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(fishItem);

                builder.addCriterion("has_" + itemId.getPath(),
                        InventoryChangeTrigger.TriggerInstance.hasItems(fishItem));
            }

            builder.addCriterion("has_salmon",
                    InventoryChangeTrigger.TriggerInstance.hasItems(Items.SALMON));

            builder.parent(ResourceLocation.fromNamespaceAndPath("hadeandepths", "fishing/first_fish"));

            builder.save(consumer, "hadeandepths:collect_all_inland_fish");
        }
    }
    void createCollectAllAbyssalFishAdvancement(Consumer<AdvancementHolder> consumer)
    {
        {
            final List<Supplier<Item>> ABYSSAL_FISH_ITEMS = List.of(
                    ModItems.LAMPLIGHTER, ModItems.COLOSSAL_SQUID, ModItems.BLOBFISH, ModItems.LANTERN_FISH, ModItems.FRILLED_SHARK,
                    ModItems.SLACK_JAW, ModItems.PHANTOM_JELLYFISH
            );
            Advancement.Builder builder = Advancement.Builder.advancement()
                    .display(
                            ModItems.SLACK_JAW,
                            Component.literal("Cast Into the Abyss"),
                            Component.literal("Collect every Abyssal Fish"),
                            null, AdvancementType.GOAL,
                            true, true, false
                    );

            for (Supplier<Item> fishSupplier : ABYSSAL_FISH_ITEMS) {
                Item fishItem = fishSupplier.get();
                ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(fishItem);

                builder.addCriterion("has_" + itemId.getPath(),
                        InventoryChangeTrigger.TriggerInstance.hasItems(fishItem));
            }

            builder.parent(ResourceLocation.fromNamespaceAndPath("hadeandepths", "fishing/first_fish"));

            builder.save(consumer, "hadeandepths:collect_all_abyssal_fish");
        }
    }

    void createCollectAllOceanicFishAdvancement(Consumer<AdvancementHolder> consumer)
    {
        {
            final List<Supplier<Item>> OCEANIC_FISH_ITEMS = List.of(
                    ModItems.REAPER_FIN, ModItems.EEL, ModItems.PINK_NITELITE, ModItems.GREEN_NITELITE, ModItems.ELECTRIC_EEL,
                    ModItems.GREAT_WHITE_SHARK, ModItems.TUNA, ModItems.BARRACUDA
            );
            Advancement.Builder builder = Advancement.Builder.advancement()
                    .display(
                            ModItems.EEL,
                            Component.literal("The Great Blue Sea"),
                            Component.literal("Collect every Oceanic Fish"),
                            null, AdvancementType.GOAL,
                            true, true, false
                    );

            for (Supplier<Item> fishSupplier : OCEANIC_FISH_ITEMS) {
                Item fishItem = fishSupplier.get();
                ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(fishItem);

                builder.addCriterion("has_" + itemId.getPath(),
                        InventoryChangeTrigger.TriggerInstance.hasItems(fishItem));
            }

            builder.addCriterion("has_cod",
                    InventoryChangeTrigger.TriggerInstance.hasItems(Items.COD));
            builder.addCriterion("has_tropical_fish",
                    InventoryChangeTrigger.TriggerInstance.hasItems(Items.TROPICAL_FISH));
            builder.addCriterion("has_pufferfish",
                    InventoryChangeTrigger.TriggerInstance.hasItems(Items.PUFFERFISH));

            builder.parent(ResourceLocation.fromNamespaceAndPath("hadeandepths", "fishing/first_fish"));

            builder.save(consumer, "hadeandepths:collect_all_oceanic_fish");
        }
    }
    void createCollectAllFishAdvancement(Consumer<AdvancementHolder> consumer)
    {
        final List<Supplier<Item>> ALL_FISH_ITEMS = List.of(
                ModItems.REAPER_FIN, ModItems.EEL, ModItems.LAMPLIGHTER, ModItems.GOLD_FISH, ModItems.COELACANTH, ModItems.FISH, ModItems.CARP,
                ModItems.TRILOBITE, ModItems.GREEN_NITELITE, ModItems.BLUE_NITELITE, ModItems.PINK_NITELITE, ModItems.AMETHYST_GROUPER, ModItems.BARRACUDA,
                ModItems.BLOBFISH, ModItems.CAVE_CARP, ModItems.COLOSSAL_SQUID, ModItems.DUNKLEOSTEUS, ModItems.ELECTRIC_EEL, ModItems.LANTERN_FISH, ModItems.TUNA,
                ModItems.ZOMBIE_FISH, ModItems.GIANT_CATFISH, ModItems.GREAT_WHITE_SHARK, ModItems.GLOW_FISH, ModItems.GHOST_FISH, ModItems.HORSESHOE_CRAB,
                ModItems.NAUTILUS, ModItems.MINN_O_WISP, ModItems.SEA_SERPENT, ModItems.STURGEON, ModItems.TIGER_FISH, ModItems.FRILLED_SHARK, ModItems.ICY_KRILL,
                ModItems.TROUT, ModItems.FUR_BEARING_TROUT, ModItems.ICICLE_FISH, ModItems.FROZEN_FISH, ModItems.ORCA, ModItems.BASSIGATOR, ModItems.SLACK_JAW,
                ModItems.PHANTOM_JELLYFISH, ModItems.MEGALODON, ModItems.RED_HERRING
        );
        Advancement.Builder builder = Advancement.Builder.advancement()
                .display(
                        ModItems.FISH,
                        Component.literal("Master Baiter"),
                        Component.literal("Collect every Fish"),
                        null, AdvancementType.CHALLENGE,
                        true, true, false
                );

        for (Supplier<Item> fishSupplier : ALL_FISH_ITEMS) {
            Item fishItem = fishSupplier.get();
            ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(fishItem);

            builder.addCriterion("has_" + itemId.getPath(),
                    InventoryChangeTrigger.TriggerInstance.hasItems(fishItem));
        }

        builder.addCriterion("has_cod",
                InventoryChangeTrigger.TriggerInstance.hasItems(Items.COD));
        builder.addCriterion("has_tropical_fish",
                InventoryChangeTrigger.TriggerInstance.hasItems(Items.TROPICAL_FISH));
        builder.addCriterion("has_pufferfish",
                InventoryChangeTrigger.TriggerInstance.hasItems(Items.PUFFERFISH));
        InventoryChangeTrigger.TriggerInstance.hasItems(Items.SALMON);
        builder.addCriterion("has_salmon",
                InventoryChangeTrigger.TriggerInstance.hasItems(Items.SALMON));


        builder.parent(ResourceLocation.fromNamespaceAndPath("hadeandepths", "collect_first_legendary_fish"));

        builder.save(consumer, "hadeandepths:collect_all_fish");
    }
}