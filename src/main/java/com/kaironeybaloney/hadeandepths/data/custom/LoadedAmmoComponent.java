package com.kaironeybaloney.hadeandepths.data.custom;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record LoadedAmmoComponent(List<ItemStack> ammo, Set<Integer> usedSlots) implements DataComponentType {
    public static final Codec<LoadedAmmoComponent> CODEC =
            ItemStack.CODEC.listOf()
                    .xmap(LoadedAmmoComponent::new, LoadedAmmoComponent::ammo);

    public static final StreamCodec<RegistryFriendlyByteBuf, LoadedAmmoComponent> STREAM_CODEC =
            ItemStack.STREAM_CODEC.apply(ByteBufCodecs.list())
                    .map(LoadedAmmoComponent::new, LoadedAmmoComponent::ammo);
    public LoadedAmmoComponent(List<ItemStack> ammo) {
        this(ammo, new HashSet<>());
    }

    public boolean isSlotUsed(int slot) {
        return usedSlots.contains(slot);
    }

    public LoadedAmmoComponent markSlotUsed(int slot) {
        Set<Integer> newSet = new HashSet<>(usedSlots);
        newSet.add(slot);
        return new LoadedAmmoComponent(new ArrayList<>(ammo), newSet);
    }
    @Nullable
    @Override
    public Codec codec() {
        return CODEC;
    }

    @Override
    public StreamCodec streamCodec() {
        return STREAM_CODEC;
    }
}
