package com.kaironeybaloney.hadeandepths.item.armor;

import com.kaironeybaloney.hadeandepths.tags.ModTags;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;

public class ModArmorMaterials {
    public static final Holder<ArmorMaterial> TIDAL_ARMOR_MATERIAL = Holder.direct(new ArmorMaterial(
            Util.make(new EnumMap<ArmorItem.Type, Integer>(ArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.BOOTS, 5);
                attribute.put(ArmorItem.Type.LEGGINGS, 7);
                attribute.put(ArmorItem.Type.CHESTPLATE, 9);
                attribute.put(ArmorItem.Type.HELMET, 5);
                attribute.put(ArmorItem.Type.BODY, 11);
            }),
            16,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.of(ModTags.DEEP_SEA_AMALGAM_INGREDIENT),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("hadeandepths", "tidal"))),
            2f,
            0.1f
    ));
}