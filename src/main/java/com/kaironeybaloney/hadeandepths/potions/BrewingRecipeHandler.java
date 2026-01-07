package com.kaironeybaloney.hadeandepths.potions;


import com.kaironeybaloney.hadeandepths.item.ModItems;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

@EventBusSubscriber(modid = "hadeandepths", bus = EventBusSubscriber.Bus.GAME)
public class BrewingRecipeHandler {

    @SubscribeEvent
    public static void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();
        builder.addMix(
                Potions.AWKWARD,
                ModItems.GLOWY_GOOP.get(),
                ModPotions.GLOWING_POTION
        );
        builder.addMix(
                ModPotions.GLOWING_POTION,
                net.minecraft.world.item.Items.REDSTONE,
                ModPotions.LONG_GLOWING_POTION
        );

        builder.addMix(
                Potions.AWKWARD,
                ModItems.SHELL_FRAGMENT.get(),
                ModPotions.RESISTANCE_POTION
        );
        builder.addMix(
                ModPotions.RESISTANCE_POTION,
                net.minecraft.world.item.Items.REDSTONE,
                ModPotions.LONG_RESISTANCE_POTION
        );
        builder.addMix(
                ModPotions.RESISTANCE_POTION,
                net.minecraft.world.item.Items.GLOWSTONE,
                ModPotions.STRONG_RESISTANCE_POTION
        );

        builder.addMix(
                Potions.AWKWARD,
                ModItems.SQUID_EYE.get(),
                ModPotions.DEEP_SEAS_EMBRACE
        );

        builder.addMix(
                ModPotions.DEEP_SEAS_EMBRACE,
                ModItems.TENTACLE.get(),
                ModPotions.LONG_DEEP_SEAS_EMBRACE
        );
        builder.addMix(
                ModPotions.DEEP_SEAS_EMBRACE,
                ModItems.FIN.get(),
                ModPotions.LONG_DEEP_SEAS_EMBRACE
        );

        builder.addMix(
                Potions.AWKWARD,
                ModItems.FIN.get(),
                ModPotions.DOLPHINS_GRACE
        );

        builder.addMix(
                ModPotions.DOLPHINS_GRACE,
                Items.REDSTONE,
                ModPotions.LONG_DOLPHINS_GRACE
        );

        builder.addMix(
                Potions.AWKWARD,
                ModItems.PUFFERFISH_LIVER.get(),
                ModPotions.PUFFERFISH_VENOM
        );
    }
}