package com.kaironeybaloney.hadeandepths.item.armor;

import com.kaironeybaloney.hadeandepths.item.ModItems;
import com.kaironeybaloney.hadeandepths.tags.ModTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

import javax.tools.Tool;

public class ModToolMaterials {

    public static final ToolMaterial TIDAL_TOOL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            2000,
            9f,
            3.0f,
            15,
            ModTags.DEEP_SEA_AMALGAM_INGREDIENT);
}
