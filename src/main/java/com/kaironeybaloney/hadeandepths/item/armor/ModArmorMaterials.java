package com.kaironeybaloney.hadeandepths.item.armor;

import com.kaironeybaloney.hadeandepths.tags.ModTags;
import net.minecraft.Util;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.*;

import java.util.EnumMap;

public class ModArmorMaterials {
    public static ResourceKey<EquipmentAsset> TIDAL = EquipmentAssets.createId("tidal");

    public static final ArmorMaterial TIDAL_ARMOR_MATERIAL = new ArmorMaterial(
            1200,
            Util.make(new EnumMap<ArmorType, Integer>(ArmorType.class), attribute -> {
                attribute.put(ArmorType.BOOTS, 5);
                attribute.put(ArmorType.LEGGINGS, 7);
                attribute.put(ArmorType.CHESTPLATE, 9);
                attribute.put(ArmorType.HELMET, 5);
                attribute.put(ArmorType.BODY, 11);
            }),
            16,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            2f,
            0.1f,
            ModTags.DEEP_SEA_AMALGAM_INGREDIENT,
            ModArmorMaterials.TIDAL
    );
}
