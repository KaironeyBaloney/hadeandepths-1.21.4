package com.kaironeybaloney.hadeandepths.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static final TagKey<Item> DEEP_SEA_AMALGAM_INGREDIENT =
            TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("medicalitems", "tidal_repair_items"));
}
