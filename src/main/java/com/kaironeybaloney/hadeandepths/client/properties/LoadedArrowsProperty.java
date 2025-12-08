package com.kaironeybaloney.hadeandepths.client.properties;

import com.kaironeybaloney.hadeandepths.data.ModDataComponents;
import com.mojang.datafixers.types.Type;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public record LoadedArrowsProperty() implements RangeSelectItemModelProperty {
    public static final MapCodec<LoadedArrowsProperty> MAP_CODEC = MapCodec.unit(new LoadedArrowsProperty());

    @Override
    public float get(ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity, int i) {
        var comp = itemStack.get(ModDataComponents.LOADED_AMMO);
        if (comp == null) return 0f;
        return (float) comp.ammo().size();
    }

    @Override
    public MapCodec<LoadedArrowsProperty> type() {
        return MAP_CODEC;
    }
}
