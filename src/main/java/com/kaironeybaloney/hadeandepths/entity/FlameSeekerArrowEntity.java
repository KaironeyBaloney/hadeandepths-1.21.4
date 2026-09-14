package com.kaironeybaloney.hadeandepths.entity;

import com.google.common.collect.Lists;
import com.kaironeybaloney.hadeandepths.event.TickScheduler;
import com.kaironeybaloney.hadeandepths.item.ModItems;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

import javax.annotation.Nullable;
import java.util.List;

public class FlameSeekerArrowEntity extends AbstractArrow {
    public FlameSeekerArrowEntity(EntityType<? extends FlameSeekerArrowEntity> type, Level level) {
        super(type, level);
        this.setBaseDamage(4.0D);
    }

    public FlameSeekerArrowEntity(Level level, double x, double y, double z, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(ModEntities.FLAME_SEEKER_ARROW.get(), x, y, z, level, pickupItemStack, firedFromWeapon);
        this.setBaseDamage(4.0D);
    }

    public FlameSeekerArrowEntity(Level level, LivingEntity owner, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(ModEntities.FLAME_SEEKER_ARROW.get(), owner, level, pickupItemStack, firedFromWeapon);
        this.setBaseDamage(4.0D);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.FLAME_SEEKER_ARROW.get());
    }

    @Override
    protected void doPostHurtEffects(LivingEntity target) {
        Level level = level();
        if(!level.isClientSide()) {
            if (!target.isOnFire()) {
                target.igniteForSeconds(3);
            } else {
                TickScheduler.schedule((ServerLevel) level, 10, () -> {
                    DamageSource damagesource = this.damageSources().arrow(this, (Entity) target);
                    target.hurt(damagesource, 4);
                    target.setRemainingFireTicks(target.getRemainingFireTicks() + 60);
                    level.playSound((Player)null, target.getX(), target.getY(), target.getZ(), SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
                    ((ServerLevel) level).sendParticles(ParticleTypes.FLAME, target.getX(), target.getY(), target.getZ(), 20, 0.25, 0.25, 0.25, 0.125);
                });
            }
        }
    }
}
