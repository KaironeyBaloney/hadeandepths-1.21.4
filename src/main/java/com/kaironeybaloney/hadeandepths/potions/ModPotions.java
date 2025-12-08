package com.kaironeybaloney.hadeandepths.potions;

import com.kaironeybaloney.hadeandepths.HadeanDepths;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModPotions {
    public static final DeferredRegister<net.minecraft.world.item.alchemy.Potion> POTIONS =
            DeferredRegister.create(Registries.POTION, HadeanDepths.MODID);

    public static final Holder<Potion> GLOWING_POTION = POTIONS.register("glowing_potion", registryName -> new Potion(
            "glowing",
            new MobEffectInstance(MobEffects.GLOWING, 3600, 0)
    ));

    public static final Holder<Potion> LONG_GLOWING_POTION = POTIONS.register("long_glowing_potion", registryName -> new Potion(
            "glowing",
            new MobEffectInstance(MobEffects.GLOWING, 9600, 0)
    ));

    public static final Holder<Potion> RESISTANCE_POTION = POTIONS.register("resistance_potion", registryName -> new Potion(
            "resistance",
            new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 3600, 0)
    ));

    public static final Holder<Potion> STRONG_RESISTANCE_POTION = POTIONS.register("strong_resistance_potion", registryName -> new Potion(
            "resistance",
            new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1800, 1)
    ));

    public static final Holder<Potion> LONG_RESISTANCE_POTION = POTIONS.register("long_resistance_potion", registryName -> new Potion(
            "resistance",
            new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 9600, 0)
    ));

    public static final Holder<Potion> DEEP_SEAS_EMBRACE = POTIONS.register("deep_seas_embrace", registryName -> new Potion(
            "deep_sea",
            new MobEffectInstance(MobEffects.NIGHT_VISION, 1800, 0),
            new MobEffectInstance(MobEffects.WATER_BREATHING, 1800, 0),
            new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 1800, 0)
    ));

    public static final Holder<Potion> LONG_DEEP_SEAS_EMBRACE = POTIONS.register("long_deep_seas_embrace", registryName -> new Potion(
            "deep_sea",
            new MobEffectInstance(MobEffects.NIGHT_VISION, 3600, 0),
            new MobEffectInstance(MobEffects.WATER_BREATHING, 3600, 0),
            new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 3600, 0)
    ));

    public static final Holder<Potion> DOLPHINS_GRACE = POTIONS.register("dolphins_grace", registryName -> new Potion(
            "dolphins_grace",
            new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 900, 0)
    ));

    public static final Holder<Potion> LONG_DOLPHINS_GRACE = POTIONS.register("long_dolphins_grace", registryName -> new Potion(
            "dolphins_grace",
            new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 1800, 0)
    ));
}