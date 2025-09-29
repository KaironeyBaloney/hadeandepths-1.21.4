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

                        output.accept(ModItems.GREAT_WHITE_SHARK);
                        output.accept(ModItems.TUNA);
                        output.accept(ModItems.COLOSSAL_SQUID);
                        output.accept(ModItems.DUNKLEOSTEUS);
                        output.accept(ModItems.SEA_SERPENT);
                        output.accept(ModItems.AMETHYST_GROUPER);
                        output.accept(ModItems.GIANT_CATFISH);
                        output.accept(ModItems.STURGEON);

                        output.accept(ModItems.TWIG_ROD);
                        output.accept(ModItems.REINFORCED_ROD);
                        output.accept(ModItems.NETHERITE_ROD);
                        output.accept(ModItems.MAGMATIC_ROD);

                        output.accept(ModItems.FISH_BONE);
                        output.accept(ModItems.SHARP_TOOTH);
                        output.accept(ModItems.GLOWY_GOOP);
                        output.accept(ModItems.SHELL_FRAGMENT);
                        output.accept(ModItems.LARGE_SCALES);
                        output.accept(ModItems.SQUID_EYE);
                        output.accept(ModItems.SAND_DOLLAR);
                        output.accept(ModItems.MAGMATIC_UPGRADE_SMITHING_TEMPLATE);
                        output.accept(ModItems.GLASS_JAR);
                        output.accept(ModItems.WATER_JAR);

                        output.accept(ModBlocks.BLUE_NITELITE_JAR);
                        output.accept(ModBlocks.GREEN_NITELITE_JAR);
                        output.accept(ModBlocks.PINK_NITELITE_JAR);
                        output.accept(ModBlocks.WOODEN_CRATE);
                        output.accept(ModBlocks.DAVY_JONES_LOCKER);

                    }).build());

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
