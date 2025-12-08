package com.kaironeybaloney.hadeandepths.item.custom;

import com.kaironeybaloney.hadeandepths.entity.ModEntities;
import com.kaironeybaloney.hadeandepths.entity.ToothArrowEntity;
import com.kaironeybaloney.hadeandepths.item.ModItems;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class ToothArrowItem extends ArrowItem implements ProjectileItem {

    public ToothArrowItem(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack ammo, LivingEntity shooter, @Nullable ItemStack weapon)
    {
        return new ToothArrowEntity(level, shooter, ammo.copyWithCount(1), weapon);
    }
    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction p_338469_) {
        ToothArrowEntity arrow = new ToothArrowEntity(level, pos.x(), pos.y(), pos.z(), stack.copyWithCount(1), (ItemStack)null);
        arrow.pickup = AbstractArrow.Pickup.ALLOWED;
        return arrow;
    }
}
