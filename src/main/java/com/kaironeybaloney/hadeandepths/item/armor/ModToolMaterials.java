package com.kaironeybaloney.hadeandepths.item.armor;

import com.kaironeybaloney.hadeandepths.tags.ModTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolMaterials {

    public static final Tier TIDAL_TOOL = new SimpleTier(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            2000,
            9f,
            3.0f,
            15,
            () -> Ingredient.of(ModTags.DEEP_SEA_AMALGAM_INGREDIENT));
}