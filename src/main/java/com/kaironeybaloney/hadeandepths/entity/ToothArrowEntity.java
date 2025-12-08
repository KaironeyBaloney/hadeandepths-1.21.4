package com.kaironeybaloney.hadeandepths.entity;

import com.kaironeybaloney.hadeandepths.item.ModItems;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class ToothArrowEntity extends AbstractArrow {
    public ToothArrowEntity(EntityType<? extends ToothArrowEntity> type, Level level) {
        super(type, level);
        this.setBaseDamage(4.0D);
    }

    public ToothArrowEntity(Level level, double x, double y, double z, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(ModEntities.TOOTH_ARROW.get(), x, y, z, level, pickupItemStack, firedFromWeapon);
        this.setBaseDamage(4.0D);
    }

    public ToothArrowEntity(Level level, LivingEntity owner, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(ModEntities.TOOTH_ARROW.get(), owner, level, pickupItemStack, firedFromWeapon);
        this.setBaseDamage(4.0D);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.TOOTH_ARROW.get());
    }

    @Override
    protected float getWaterInertia() {
        return 0.99F;
    }
}
