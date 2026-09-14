package com.kaironeybaloney.hadeandepths.item.custom;

import com.kaironeybaloney.hadeandepths.entity.FlameSeekerArrowEntity;
import com.kaironeybaloney.hadeandepths.entity.ToothArrowEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class FlameSeekerArrowItem extends ArrowItem implements ProjectileItem {

    public FlameSeekerArrowItem(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack ammo, LivingEntity shooter, @Nullable ItemStack weapon)
    {
        return new FlameSeekerArrowEntity(level, shooter, ammo.copyWithCount(1), weapon);
    }
    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction p_338469_) {
        FlameSeekerArrowEntity arrow = new FlameSeekerArrowEntity(level, pos.x(), pos.y(), pos.z(), stack.copyWithCount(1), (ItemStack)null);
        arrow.pickup = AbstractArrow.Pickup.ALLOWED;
        return arrow;
    }
}
