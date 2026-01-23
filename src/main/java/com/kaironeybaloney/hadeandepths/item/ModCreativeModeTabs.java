package com.kaironeybaloney.hadeandepths.item;

import com.kaironeybaloney.hadeandepths.HadeanDepths;
import com.kaironeybaloney.hadeandepths.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HadeanDepths.MODID);

    public static final Supplier<CreativeModeTab> HADEAN_DEPTHS_TAB = CREATIVE_MODE_TAB.register("hadean_depths_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.REAPER_FIN.get()))
                    .title(Component.translatable("createivetab.hadeandepths.hadean_depths_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.REAPER_FIN);
                        output.accept(ModItems.MINN_O_WISP);
                        output.accept(ModItems.ZOMBIE_FISH);
                        output.accept(ModItems.GOLD_FISH);
                        output.accept(ModItems.EEL);
                        output.accept(ModItems.FISH);
                        output.accept(ModItems.PINK_NITELITE);
                        output.accept(ModItems.GREEN_NITELITE);
                        output.accept(ModItems.BLUE_NITELITE);
                        output.accept(ModItems.ELECTRIC_EEL);
                        output.accept(ModItems.CARP);
                        output.accept(ModItems.TRILOBITE);
                        output.accept(ModItems.NAUTILUS);
                        output.accept(ModItems.LAMPLIGHTER);
                        output.accept(ModItems.GLOW_FISH);
                        output.accept(ModItems.COELACANTH);
                        output.accept(ModItems.HORSESHOE_CRAB);
                        output.accept(ModItems.GHOST_FISH);
                        output.accept(ModItems.CAVE_CARP);
                        output.accept(ModItems.BLOBFISH);
                        output.accept(ModItems.LANTERN_FISH);
                        output.accept(ModItems.BARRACUDA);
                        output.accept(ModItems.TIGER_FISH);
                        output.accept(ModItems.ICY_KRILL);
                        output.accept(ModItems.TROUT);
                        output.accept(ModItems.FUR_BEARING_TROUT);
                        output.accept(ModItems.ICICLE_FISH);
                        output.accept(ModItems.FROZEN_FISH);
                        output.accept(ModItems.BASSIGATOR);
                        output.accept(ModItems.SLACK_JAW);
                        output.accept(ModItems.RED_HERRING);
                        output.accept(ModItems.METAMORFIN);
                        output.accept(ModItems.LAVA_JELLY);
                        output.accept(ModItems.TWISTED_EEL);
                        output.accept(ModItems.HELIOS);
                        output.accept(ModItems.MAGMA_GUT);
                        output.accept(ModItems.OBSIDIAN_SHARD_FIN);
                        output.accept(ModItems.ZOMBIE_HOG_SUCKER);
                        output.accept(ModItems.HOG_SUCKER);
                        output.accept(ModItems.BRUTISH_HOG_SUCKER);
                        output.accept(ModItems.CRIMSON_SHROOMLITE);
                        output.accept(ModItems.WARPED_SHROOMLITE);
                        output.accept(ModItems.INFERNO_EEL);
                        output.accept(ModItems.SOULFERNO_EEL);

                        output.accept(ModItems.GREAT_WHITE_SHARK);
                        output.accept(ModItems.TUNA);
                        output.accept(ModItems.COLOSSAL_SQUID);
                        output.accept(ModItems.DUNKLEOSTEUS);
                        output.accept(ModItems.SEA_SERPENT);
                        output.accept(ModItems.AMETHYST_GROUPER);
                        output.accept(ModItems.GIANT_CATFISH);
                        output.accept(ModItems.STURGEON);
                        output.accept(ModItems.FRILLED_SHARK);
                        output.accept(ModItems.ORCA);
                        output.accept(ModItems.PHANTOM_JELLYFISH);
                        output.accept(ModItems.MEGALODON);
                        output.accept(ModItems.NETHERITIC_CONTRAPTION);
                        output.accept(ModItems.MANTA_RAY);
                        output.accept(ModItems.MAGMA_WYRM);

                        output.accept(ModItems.TWIG_ROD);
                        output.accept(ModItems.IRON_FISHING_ROD);
                        output.accept(ModItems.REINFORCED_ROD);
                        output.accept(ModItems.NETHERITE_ROD);
                        output.accept(ModItems.MAGMATIC_ROD);

                        output.accept(ModItems.TIDAL_PICKAXE);
                        output.accept(ModItems.TIDAL_SHOVEL);
                        output.accept(ModItems.TIDAL_AXE);
                        output.accept(ModItems.TIDAL_HOE);
                        output.accept(ModItems.TIDAL_SWORD);
                        output.accept(ModItems.TIDAL_MORNING_STAR);
                        output.accept(ModItems.TIDAL_SLEDGE);
                        output.accept(ModItems.SERPENT_BONE_BOW);

                        output.accept(ModItems.TIDAL_HELMET);
                        output.accept(ModItems.TIDAL_CHESTPLATE);
                        output.accept(ModItems.TIDAL_LEGGINGS);
                        output.accept(ModItems.TIDAL_BOOTS);

                        output.accept(ModItems.FISH_BONE);
                        output.accept(ModItems.SHARP_TOOTH);
                        output.accept(ModItems.SHARK_TOOTH);
                        output.accept(ModItems.FORKED_SHARK_TOOTH);
                        output.accept(ModItems.SERPENT_FIN);
                        output.accept(ModItems.GLOWY_GOOP);
                        output.accept(ModItems.BLUBBER);
                        output.accept(ModItems.SHELL_FRAGMENT);
                        output.accept(ModItems.PRISMARINE_ROD);
                        output.accept(ModItems.HUGE_BONE);
                        output.accept(ModItems.GIANT_WHISKER);
                        output.accept(ModItems.LARGE_SCALES);
                        output.accept(ModItems.SQUID_EYE);
                        output.accept(ModItems.HUGE_AMETHYST_SHARD);
                        output.accept(ModItems.DUNKLEOSTEUS_PLATING);
                        output.accept(ModItems.PHANTOM_JELLY_SILK);
                        output.accept(ModItems.MANTA_RAY_WING);
                        output.accept(ModItems.LEGENDARY_HEART);
                        output.accept(ModItems.DEEP_SEA_RESIDUE);
                        output.accept(ModItems.DEEP_SEA_AMALGAM);
                        output.accept(ModItems.LIVING_FLAME);
                        output.accept(ModItems.LIVING_SOUL_FLAME);
                        output.accept(ModItems.SCORCHED_FISH_BONE);
                        output.accept(ModItems.RAW_FILLET);
                        output.accept(ModItems.COOKED_FILLET);
                        output.accept(ModItems.RAW_HUGE_FILLET);
                        output.accept(ModItems.COOKED_HUGE_FILLET);
                        output.accept(ModItems.RAW_TUNA_FILLET);
                        output.accept(ModItems.COOKED_TUNA_FILLET);
                        output.accept(ModItems.FISH_STEW);
                        output.accept(ModItems.FISH_SANDWICH);
                        output.accept(ModItems.TENTACLE);
                        output.accept(ModItems.COOKED_TENTACLE);
                        output.accept(ModItems.FIN);
                        output.accept(ModItems.BAKED_FIN);
                        output.accept(ModItems.RAW_SHELLFISH);
                        output.accept(ModItems.COOKED_SHELLFISH);
                        output.accept(ModItems.PUFFERFISH_LIVER);
                        output.accept(ModItems.EMBER_FILLET);
                        output.accept(ModItems.EMBER_FIN);

                        output.accept(ModItems.SAND_DOLLAR);
                        output.accept(ModItems.MAGMATIC_UPGRADE_SMITHING_TEMPLATE);
                        output.accept(ModItems.GLASS_JAR);
                        output.accept(ModItems.WATER_JAR);
                        output.accept(ModItems.TOOTH_ARROW);
                        output.accept(ModItems.FLAME_SEEKER_ARROW);

                        output.accept(ModBlocks.GLOWY_GOOP_BLOCK);
                        output.accept(ModBlocks.BLUE_NITELITE_JAR);
                        output.accept(ModBlocks.GREEN_NITELITE_JAR);
                        output.accept(ModBlocks.PINK_NITELITE_JAR);
                        output.accept(ModBlocks.WOODEN_CRATE);
                        output.accept(ModBlocks.DAVY_JONES_LOCKER);
                        output.accept(ModBlocks.BUTCHERING_HOOK);

                    }).build());

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
