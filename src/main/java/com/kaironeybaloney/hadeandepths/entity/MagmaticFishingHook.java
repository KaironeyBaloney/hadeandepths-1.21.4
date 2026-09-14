package com.kaironeybaloney.hadeandepths.entity;

import com.mojang.logging.LogUtils;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.entity.player.ItemFishedEvent;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MagmaticFishingHook extends FishingHook {
    private static final Logger LOGGER = LogUtils.getLogger();
    private final RandomSource syncronizedRandom;
    private boolean biting;
    private int outOfWaterTime;
    private static final int MAX_OUT_OF_WATER_TIME = 10;
    private static final EntityDataAccessor<Integer> DATA_HOOKED_ENTITY;
    private static final EntityDataAccessor<Boolean> DATA_BITING;
    private int life;
    private int nibble;
    private int timeUntilLured;
    private int timeUntilHooked;
    private float fishAngle;
    private boolean openWater;
    private boolean inLava;
    @Nullable
    private Entity hookedIn;
    private MagmaticFishingHook.FishHookState currentState;
    private final int luck;
    private final int lureSpeed;

    private MagmaticFishingHook(EntityType<? extends MagmaticFishingHook> entityType, Level level, int luck, int lureSpeed) {
        super(entityType, level);
        this.syncronizedRandom = RandomSource.create();
        this.openWater = true;
        this.inLava = false;
        this.currentState = MagmaticFishingHook.FishHookState.FLYING;
        this.luck = Math.max(0, luck);
        this.lureSpeed = Math.max(0, lureSpeed);
    }

    public MagmaticFishingHook(EntityType<? extends MagmaticFishingHook> p_150138_, Level p_150139_) {
        this(p_150138_, p_150139_, 0, 0);
    }

    public MagmaticFishingHook(Player owner, Level level, int luck, int lureSpeed) {
        this(ModEntities.MAGMATIC_FISHING_BOBBER.get(), level, luck, lureSpeed);
        this.setOwner(owner);
        float f = owner.getXRot();
        float f1 = owner.getYRot();
        float f2 = Mth.cos(-f1 * 0.017453292F - 3.1415927F);
        float f3 = Mth.sin(-f1 * 0.017453292F - 3.1415927F);
        float f4 = -Mth.cos(-f * 0.017453292F);
        float f5 = Mth.sin(-f * 0.017453292F);
        double d0 = owner.getX() - (double)f3 * 0.3;
        double d1 = owner.getEyeY();
        double d2 = owner.getZ() - (double)f2 * 0.3;
        this.moveTo(d0, d1, d2, f1, f);
        Vec3 vec3 = new Vec3((double)(-f3), (double)Mth.clamp(-(f5 / f4), -5.0F, 5.0F), (double)(-f2));
        double d3 = vec3.length();
        vec3 = vec3.multiply(0.6 / d3 + this.random.triangle(0.5, 0.0103365), 0.6 / d3 + this.random.triangle(0.5, 0.0103365), 0.6 / d3 + this.random.triangle(0.5, 0.0103365));
        this.setDeltaMovement(vec3);
        this.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * 180.0 / 3.1415927410125732));
        this.setXRot((float)(Mth.atan2(vec3.y, vec3.horizontalDistance()) * 180.0 / 3.1415927410125732));
        this.yRotO = this.getYRot();
        this.xRotO = this.getXRot();
    }

    protected void defineSynchedData(SynchedEntityData.Builder p_326397_) {
        super.defineSynchedData(p_326397_);
        p_326397_.define(DATA_HOOKED_ENTITY, 0);
        p_326397_.define(DATA_BITING, false);
    }

    protected boolean shouldBounceOnWorldBorder() {
        return true;
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        if (DATA_HOOKED_ENTITY.equals(key)) {
            int i = (Integer)this.getEntityData().get(DATA_HOOKED_ENTITY);
            this.hookedIn = i > 0 ? this.level().getEntity(i - 1) : null;
        }

        if (DATA_BITING.equals(key)) {
            this.biting = (Boolean)this.getEntityData().get(DATA_BITING);
            if (this.biting) {
                this.setDeltaMovement(this.getDeltaMovement().x, (double)(-0.4F * Mth.nextFloat(this.syncronizedRandom, 0.6F, 1.0F)), this.getDeltaMovement().z);
            }
        }

        super.onSyncedDataUpdated(key);
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double distance) {
        double d0 = 64.0;
        return distance < 4096.0;
    }

    @Override
    public void lerpTo(double p_37127_, double p_37128_, double p_37129_, float p_37130_, float p_37131_, int p_37132_) {
    }
    private boolean shouldStopFishing(Player player) {
        ItemStack itemstack = player.getMainHandItem();
        ItemStack itemstack1 = player.getOffhandItem();
        boolean flag = itemstack.canPerformAction(ItemAbilities.FISHING_ROD_CAST);
        boolean flag1 = itemstack1.canPerformAction(ItemAbilities.FISHING_ROD_CAST);
        if (!player.isRemoved() && player.isAlive() && (flag || flag1) && !(this.distanceToSqr(player) > 1024.0)) {
            return false;
        } else {
            this.discard();
            return true;
        }
    }

    @Override
    public void tick() {
        this.syncronizedRandom.setSeed(this.getUUID().getLeastSignificantBits() ^ this.level().getGameTime());
        Player player = this.getPlayerOwner();
        if (player == null) {
            this.discard();
        } else if (this.level().isClientSide || !this.shouldStopFishing(player)) {
            if (this.onGround()) {
                ++this.life;
                if (this.life >= 1200) {
                    this.discard();
                    return;
                }
            } else {
                this.life = 0;
            }

            float f = 0.0F;
            BlockPos blockpos = this.blockPosition();
            FluidState fluidstate = this.level().getFluidState(blockpos);
            this.inLava = fluidstate.is(FluidTags.LAVA);
            if (fluidstate.is(FluidTags.WATER) || fluidstate.is(FluidTags.LAVA)) {
                f = fluidstate.getHeight(this.level(), blockpos);
            }

            boolean flag = f > 0.0F;
            if (this.currentState == MagmaticFishingHook.FishHookState.FLYING) {
                if (this.hookedIn != null) {
                    this.setDeltaMovement(Vec3.ZERO);
                    this.currentState = MagmaticFishingHook.FishHookState.HOOKED_IN_ENTITY;
                    return;
                }

                if (flag) {
                    this.setDeltaMovement(this.getDeltaMovement().multiply(0.3, 0.2, 0.3));
                    this.currentState = MagmaticFishingHook.FishHookState.BOBBING;
                    return;
                }

                this.checkCollision();
            } else {
                if (this.currentState == MagmaticFishingHook.FishHookState.HOOKED_IN_ENTITY) {
                    if (this.hookedIn != null) {
                        if (!this.hookedIn.isRemoved() && this.hookedIn.level().dimension() == this.level().dimension()) {
                            this.setPos(this.hookedIn.getX(), this.hookedIn.getY(0.8), this.hookedIn.getZ());
                        } else {
                            this.setHookedEntity((Entity)null);
                            this.currentState = MagmaticFishingHook.FishHookState.FLYING;
                        }
                    }

                    return;
                }

                if (this.currentState == MagmaticFishingHook.FishHookState.BOBBING) {
                    Vec3 vec3 = this.getDeltaMovement();
                    double d0 = this.getY() + vec3.y - (double)blockpos.getY() - (double)f;
                    if (Math.abs(d0) < 0.01) {
                        d0 += Math.signum(d0) * 0.1;
                    }

                    this.setDeltaMovement(vec3.x * 0.9, vec3.y - d0 * (double)this.random.nextFloat() * 0.2, vec3.z * 0.9);

                    if (this.nibble <= 0 && this.timeUntilHooked <= 0) {
                        this.openWater = true;
                    } else {
                        this.openWater = this.openWater && this.outOfWaterTime < 10 && this.calculateOpenWater(blockpos);
                    }

                    if (flag) {
                        this.outOfWaterTime = Math.max(0, this.outOfWaterTime - 1);
                        if (this.biting) {
                            this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.1 * (double)this.syncronizedRandom.nextFloat() * (double)this.syncronizedRandom.nextFloat(), 0.0));
                        }

                        if (!this.level().isClientSide) {
                            if(inLava)
                            {
                                this.catchingFishLava(blockpos);
                            } else {
                                this.catchingFish(blockpos);
                            }
                        }
                    } else {
                        this.outOfWaterTime = Math.min(10, this.outOfWaterTime + 1);
                    }
                }
            }

            if (!fluidstate.is(FluidTags.WATER) && !fluidstate.is(FluidTags.LAVA)) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.03, 0.0));
            }

            this.move(MoverType.SELF, this.getDeltaMovement());
            this.applyEffectsFromBlocks();
            this.updateRotation();
            if (this.currentState == MagmaticFishingHook.FishHookState.FLYING && (this.onGround() || this.horizontalCollision)) {
                this.setDeltaMovement(Vec3.ZERO);
            }

            double d1 = 0.92;
            this.setDeltaMovement(this.getDeltaMovement().scale(0.92));
            this.reapplyPosition();
        }

    }

    private void checkCollision() {
        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hitresult.getType() == HitResult.Type.MISS || !EventHooks.onProjectileImpact(this, hitresult)) {
            this.onHit(hitresult);
        }

    }

    @Override
    protected boolean canHitEntity(Entity p_37135_) {
        return super.canHitEntity(p_37135_) || p_37135_.isAlive() && p_37135_ instanceof ItemEntity;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!this.level().isClientSide) {
            this.setHookedEntity(result.getEntity());
        }

    }

    @Override
    protected void onHitBlock(BlockHitResult p_37142_) {
        super.onHitBlock(p_37142_);
        this.setDeltaMovement(this.getDeltaMovement().normalize().scale(p_37142_.distanceTo(this)));
    }

    private void setHookedEntity(@Nullable Entity hookedEntity) {
        this.hookedIn = hookedEntity;
        this.getEntityData().set(DATA_HOOKED_ENTITY, hookedEntity == null ? 0 : hookedEntity.getId() + 1);
    }

    private void catchingFish(BlockPos pos) {
        ServerLevel serverlevel = (ServerLevel)this.level();
        int i = 1;
        BlockPos blockpos = pos.above();
        if (this.random.nextFloat() < 0.25F && this.level().isRainingAt(blockpos)) {
            ++i;
        }

        if (this.random.nextFloat() < 0.5F && !this.level().canSeeSky(blockpos)) {
            --i;
        }

        if (this.nibble > 0) {
            --this.nibble;
            if (this.nibble <= 0) {
                this.timeUntilLured = 0;
                this.timeUntilHooked = 0;
                this.getEntityData().set(DATA_BITING, false);
            }
        } else {
            float f5;
            float f6;
            float f7;
            double d4;
            double d5;
            double d6;
            BlockState blockstate1;
            if (this.timeUntilHooked > 0) {
                this.timeUntilHooked -= i;
                if (this.timeUntilHooked > 0) {
                    this.fishAngle += (float)this.random.triangle(0.0, 9.188);
                    f5 = this.fishAngle * 0.017453292F;
                    f6 = Mth.sin(f5);
                    f7 = Mth.cos(f5);
                    d4 = this.getX() + (double)(f6 * (float)this.timeUntilHooked * 0.1F);
                    d5 = (double)((float)Mth.floor(this.getY()) + 1.0F);
                    d6 = this.getZ() + (double)(f7 * (float)this.timeUntilHooked * 0.1F);
                    blockstate1 = serverlevel.getBlockState(BlockPos.containing(d4, d5 - 1.0, d6));
                    if (blockstate1.is(Blocks.WATER)) {
                        if (this.random.nextFloat() < 0.15F) {
                            serverlevel.sendParticles(ParticleTypes.BUBBLE, d4, d5 - 0.10000000149011612, d6, 1, (double)f6, 0.1, (double)f7, 0.0);
                        }

                        float f3 = f6 * 0.04F;
                        float f4 = f7 * 0.04F;
                        serverlevel.sendParticles(ParticleTypes.FISHING, d4, d5, d6, 0, (double)f4, 0.01, (double)(-f3), 1.0);
                        serverlevel.sendParticles(ParticleTypes.FISHING, d4, d5, d6, 0, (double)(-f4), 0.01, (double)f3, 1.0);
                    }
                } else {
                    this.playSound(SoundEvents.FISHING_BOBBER_SPLASH, 0.25F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
                    double d3 = this.getY() + 0.5;
                    serverlevel.sendParticles(ParticleTypes.BUBBLE, this.getX(), d3, this.getZ(), (int)(1.0F + this.getBbWidth() * 20.0F), (double)this.getBbWidth(), 0.0, (double)this.getBbWidth(), 0.20000000298023224);
                    serverlevel.sendParticles(ParticleTypes.FISHING, this.getX(), d3, this.getZ(), (int)(1.0F + this.getBbWidth() * 20.0F), (double)this.getBbWidth(), 0.0, (double)this.getBbWidth(), 0.20000000298023224);
                    this.nibble = Mth.nextInt(this.random, 20, 40);
                    this.getEntityData().set(DATA_BITING, true);
                }
            } else if (this.timeUntilLured > 0) {
                this.timeUntilLured -= i;
                f5 = 0.15F;
                if (this.timeUntilLured < 20) {
                    f5 += (float)(20 - this.timeUntilLured) * 0.05F;
                } else if (this.timeUntilLured < 40) {
                    f5 += (float)(40 - this.timeUntilLured) * 0.02F;
                } else if (this.timeUntilLured < 60) {
                    f5 += (float)(60 - this.timeUntilLured) * 0.01F;
                }

                if (this.random.nextFloat() < f5) {
                    f6 = Mth.nextFloat(this.random, 0.0F, 360.0F) * 0.017453292F;
                    f7 = Mth.nextFloat(this.random, 25.0F, 60.0F);
                    d4 = this.getX() + (double)(Mth.sin(f6) * f7) * 0.1;
                    d5 = (double)((float)Mth.floor(this.getY()) + 1.0F);
                    d6 = this.getZ() + (double)(Mth.cos(f6) * f7) * 0.1;
                    blockstate1 = serverlevel.getBlockState(BlockPos.containing(d4, d5 - 1.0, d6));
                    if (blockstate1.is(Blocks.WATER)) {
                        serverlevel.sendParticles(ParticleTypes.SPLASH, d4, d5, d6, 2 + this.random.nextInt(2), 0.10000000149011612, 0.0, 0.10000000149011612, 0.0);
                    }
                }

                if (this.timeUntilLured <= 0) {
                    this.fishAngle = Mth.nextFloat(this.random, 0.0F, 360.0F);
                    this.timeUntilHooked = Mth.nextInt(this.random, 20, 80);
                }
            } else {
                this.timeUntilLured = Mth.nextInt(this.random, 100, 600);
                this.timeUntilLured -= this.lureSpeed;
            }
        }

    }

    private void catchingFishLava(BlockPos pos) {
        ServerLevel serverlevel = (ServerLevel)this.level();
        int i = 1;
        BlockPos blockpos = pos.above();
        if (this.random.nextFloat() < 0.25F && this.level().isRainingAt(blockpos)) {
            ++i;
        }


        if (this.random.nextFloat() < 0.5F && !this.level().canSeeSky(blockpos)) {
            --i;
        }

        if (this.nibble > 0) {
            --this.nibble;
            if (this.nibble <= 0) {
                this.timeUntilLured = 0;
                this.timeUntilHooked = 0;
                this.getEntityData().set(DATA_BITING, false);
            }
        } else {
            float f5;
            float f6;
            float f7;
            double d4;
            double d5;
            double d6;
            BlockState blockstate1;
            if (this.timeUntilHooked > 0) {
                this.timeUntilHooked -= i;
                if (this.timeUntilHooked > 0) {
                    this.fishAngle += (float)this.random.triangle(0.0, 9.188);
                    f5 = this.fishAngle * 0.017453292F;
                    f6 = Mth.sin(f5);
                    f7 = Mth.cos(f5);
                    d4 = this.getX() + (double)(f6 * (float)this.timeUntilHooked * 0.1F);
                    d5 = (double)((float)Mth.floor(this.getY()) + 1.0F);
                    d6 = this.getZ() + (double)(f7 * (float)this.timeUntilHooked * 0.1F);
                    blockstate1 = serverlevel.getBlockState(BlockPos.containing(d4, d5 - 1.0, d6));
                    if (blockstate1.is(Blocks.LAVA)) {
                        if (this.random.nextFloat() < 0.15F) {
                            serverlevel.sendParticles(ParticleTypes.LAVA, d4, d5 - 0.10000000149011612, d6, 1, (double)f6, 0.1, (double)f7, 0.0);
                        }

                        float f3 = f6 * 0.04F;
                        float f4 = f7 * 0.04F;
                        serverlevel.sendParticles(ParticleTypes.SMALL_FLAME, d4, d5, d6, 0, (double)f4, 0.01, (double)(-f3), 1.0);
                        serverlevel.sendParticles(ParticleTypes.SMALL_FLAME, d4, d5, d6, 0, (double)(-f4), 0.015, (double)f3, 0.025);
                    }
                } else {
                    this.playSound(SoundEvents.LAVA_POP, 0.25F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
                    double d3 = this.getY() + 0.5;
                    serverlevel.sendParticles(ParticleTypes.LAVA, this.getX(), d3, this.getZ(), (int)(1.0F + this.getBbWidth() * 20.0F), (double)this.getBbWidth(), 0.0, (double)this.getBbWidth(), 0.20000000298023224);
                    serverlevel.sendParticles(ParticleTypes.SMALL_FLAME, this.getX(), d3, this.getZ(), (int)(1.0F + this.getBbWidth() * 20.0F), (double)this.getBbWidth(), 0.0, (double)this.getBbWidth(), 0.20000000298023224);
                    this.nibble = Mth.nextInt(this.random, 20, 40);
                    this.getEntityData().set(DATA_BITING, true);
                }
            } else if (this.timeUntilLured > 0) {
                this.timeUntilLured -= i;
                f5 = 0.15F;
                if (this.timeUntilLured < 20) {
                    f5 += (float)(20 - this.timeUntilLured) * 0.05F;
                } else if (this.timeUntilLured < 40) {
                    f5 += (float)(40 - this.timeUntilLured) * 0.02F;
                } else if (this.timeUntilLured < 60) {
                    f5 += (float)(60 - this.timeUntilLured) * 0.01F;
                }

                if (this.random.nextFloat() < f5) {
                    f6 = Mth.nextFloat(this.random, 0.0F, 360.0F) * 0.017453292F;
                    f7 = Mth.nextFloat(this.random, 25.0F, 60.0F);
                    d4 = this.getX() + (double)(Mth.sin(f6) * f7) * 0.1;
                    d5 = (double)((float)Mth.floor(this.getY()) + 1.0F);
                    d6 = this.getZ() + (double)(Mth.cos(f6) * f7) * 0.1;
                    blockstate1 = serverlevel.getBlockState(BlockPos.containing(d4, d5 - 1.0, d6));
                    if (blockstate1.is(Blocks.LAVA)) {
                        serverlevel.sendParticles(ParticleTypes.ASH, d4, d5, d6, 2 + this.random.nextInt(2), 0.10000000149011612, 0.0, 0.10000000149011612, 0.0);
                    }
                }

                if (this.timeUntilLured <= 0) {
                    this.fishAngle = Mth.nextFloat(this.random, 0.0F, 360.0F);
                    this.timeUntilHooked = Mth.nextInt(this.random, 20, 80);
                }
            } else {
                this.timeUntilLured = Mth.nextInt(this.random, 100, 600);
                this.timeUntilLured -= this.lureSpeed;
            }
        }

    }
    private boolean calculateOpenWater(BlockPos pos) {
        MagmaticFishingHook.OpenWaterType MagmaticFishingHook$openwatertype = MagmaticFishingHook.OpenWaterType.INVALID;

        for(int i = -1; i <= 2; ++i) {
            MagmaticFishingHook.OpenWaterType MagmaticFishingHook$openwatertype1 = this.getOpenWaterTypeForArea(pos.offset(-2, i, -2), pos.offset(2, i, 2));
            switch (MagmaticFishingHook$openwatertype1.ordinal()) {
                case 0:
                    if (MagmaticFishingHook$openwatertype == MagmaticFishingHook.OpenWaterType.INVALID) {
                        return false;
                    }
                    break;
                case 1:
                    if (MagmaticFishingHook$openwatertype == MagmaticFishingHook.OpenWaterType.ABOVE_WATER) {
                        return false;
                    }
                    break;
                case 2:
                    return false;
            }

            MagmaticFishingHook$openwatertype = MagmaticFishingHook$openwatertype1;
        }

        return true;
    }

    private MagmaticFishingHook.OpenWaterType getOpenWaterTypeForArea(BlockPos firstPos, BlockPos secondPos) {
        return (MagmaticFishingHook.OpenWaterType)BlockPos.betweenClosedStream(firstPos, secondPos).map(this::getOpenWaterTypeForBlock).reduce((p_37139_, p_37140_) -> {
            return p_37139_ == p_37140_ ? p_37139_ : MagmaticFishingHook.OpenWaterType.INVALID;
        }).orElse(MagmaticFishingHook.OpenWaterType.INVALID);
    }

    private MagmaticFishingHook.OpenWaterType getOpenWaterTypeForBlock(BlockPos pos) {
        BlockState blockstate = this.level().getBlockState(pos);
        if (!blockstate.isAir() && !blockstate.is(Blocks.LILY_PAD)) {
            FluidState fluidstate = blockstate.getFluidState();
            return fluidstate.is(FluidTags.WATER) && fluidstate.isSource() && blockstate.getCollisionShape(this.level(), pos).isEmpty() ? MagmaticFishingHook.OpenWaterType.INSIDE_WATER : MagmaticFishingHook.OpenWaterType.INVALID;
        } else {
            return MagmaticFishingHook.OpenWaterType.ABOVE_WATER;
        }
    }

    @Override
    public boolean isOpenWaterFishing() {
        return this.openWater;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
    }

    @Override
    public int retrieve(ItemStack stack) {
        Player player = this.getPlayerOwner();
        if (!this.level().isClientSide && player != null && !this.shouldStopFishing(player)) {
            int i = 0;
            ItemFishedEvent event = null;
            if (this.hookedIn != null) {
                this.pullEntity(this.hookedIn);
                CriteriaTriggers.FISHING_ROD_HOOKED.trigger((ServerPlayer)player, stack, this, Collections.emptyList());
                this.level().broadcastEntityEvent(this, (byte)31);
                i = this.hookedIn instanceof ItemEntity ? 3 : 5;
            } else if (this.nibble > 0) {

                ResourceLocation lootTableID;

                if(this.inLava) {
                    System.out.println("Lava Loot Table");
                    lootTableID = ResourceLocation.fromNamespaceAndPath("hadeandepths", "gameplay/fishing_lava_master");
                } else {
                    System.out.println("Water Loot Table");
                    lootTableID = ResourceLocation.fromNamespaceAndPath("hadeandepths", "gameplay/fishing_master");
                }

                LootParams lootparams = (new LootParams.Builder((ServerLevel)this.level())).withParameter(LootContextParams.ORIGIN, this.position()).withParameter(LootContextParams.TOOL, stack).withParameter(LootContextParams.THIS_ENTITY, this).withParameter(LootContextParams.ATTACKING_ENTITY, this.getOwner()).withLuck((float)this.luck + player.getLuck()).create(LootContextParamSets.FISHING);
                ResourceKey<LootTable> lootTableKey = ResourceKey.create(Registries.LOOT_TABLE, lootTableID);
                LootTable lootTable = this.level().getServer().reloadableRegistries().getLootTable(lootTableKey);

                List<ItemStack> list = lootTable.getRandomItems(lootparams);

                event = new ItemFishedEvent(list, this.onGround() ? 2 : 1, this);
                NeoForge.EVENT_BUS.post(event);
                if (event.isCanceled()) {
                    this.discard();
                    return event.getRodDamage();
                }

                CriteriaTriggers.FISHING_ROD_HOOKED.trigger((ServerPlayer)player, stack, this, list);
                Iterator var8 = list.iterator();

                while(var8.hasNext()) {
                    ItemStack itemstack = (ItemStack)var8.next();
                    ItemEntity itementity = new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), itemstack);
                    double d0 = player.getX() - this.getX();
                    double d1 = player.getY() - this.getY();
                    double d2 = player.getZ() - this.getZ();
                    double d3 = 0.1;
                    itementity.setDeltaMovement(d0 * 0.1, d1 * 0.1 + Math.sqrt(Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * 0.08, d2 * 0.1);
                    this.level().addFreshEntity(itementity);
                    player.level().addFreshEntity(new ExperienceOrb(player.level(), player.getX(), player.getY() + 0.5, player.getZ() + 0.5, this.random.nextInt(6) + 1));
                    if (itemstack.is(ItemTags.FISHES)) {
                        player.awardStat(Stats.FISH_CAUGHT, 1);
                    }
                }

                i = 1;
            }

            if (this.onGround()) {
                i = 2;
            }

            this.discard();
            return event != null ? event.getRodDamage() : i;
        } else {
            return 0;
        }
    }

    @Override
    public void handleEntityEvent(byte p_37123_) {
        if (p_37123_ == 31 && this.level().isClientSide && this.hookedIn instanceof Player && ((Player)this.hookedIn).isLocalPlayer()) {
            this.pullEntity(this.hookedIn);
        }

        super.handleEntityEvent(p_37123_);
    }

    @Override
    protected void pullEntity(Entity p_entity) {
        Entity entity = this.getOwner();
        if (entity != null) {
            Vec3 vec3 = (new Vec3(entity.getX() - this.getX(), entity.getY() - this.getY(), entity.getZ() - this.getZ())).scale(0.1);
            p_entity.setDeltaMovement(p_entity.getDeltaMovement().add(vec3));
        }

    }

    @Override
    protected MovementEmission getMovementEmission() {
        return MovementEmission.NONE;
    }

    @Override
    public void remove(RemovalReason p_150146_) {
        this.updateOwnerInfo((MagmaticFishingHook)null);
        super.remove(p_150146_);
    }

    @Override
    public void onClientRemoval() {
        this.updateOwnerInfo((MagmaticFishingHook)null);
    }

    @Override
    public void setOwner(@Nullable Entity entity) {
        super.setOwner(entity);
        this.updateOwnerInfo(this);
    }

    private void updateOwnerInfo(@Nullable MagmaticFishingHook MagmaticFishingHook) {
        Player player = this.getPlayerOwner();
        if (player != null) {
            player.fishing = MagmaticFishingHook;
        }

    }

    @Nullable
    @Override
    public Player getPlayerOwner() {
        Entity entity = this.getOwner();
        return entity instanceof Player ? (Player)entity : null;
    }

    @Nullable
    @Override
    public Entity getHookedIn() {
        return this.hookedIn;
    }

    @Override
    public boolean canUsePortal(boolean p_352895_) {
        return false;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity p_352092_) {
        Entity entity = this.getOwner();
        return new ClientboundAddEntityPacket(this, p_352092_, entity == null ? this.getId() : entity.getId());
    }

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket p_150150_) {
        super.recreateFromPacket(p_150150_);
        if (this.getPlayerOwner() == null) {
            int i = p_150150_.getData();
            LOGGER.error("Failed to recreate fishing hook on client. {} (id: {}) is not a valid owner.", this.level().getEntity(i), i);
            this.discard();
        }

    }

    static {
        DATA_HOOKED_ENTITY = SynchedEntityData.defineId(MagmaticFishingHook.class, EntityDataSerializers.INT);
        DATA_BITING = SynchedEntityData.defineId(MagmaticFishingHook.class, EntityDataSerializers.BOOLEAN);
    }

    static enum FishHookState {
        FLYING,
        HOOKED_IN_ENTITY,
        BOBBING;

        private FishHookState() {
        }
    }

    static enum OpenWaterType {
        ABOVE_WATER,
        INSIDE_WATER,
        INVALID;

        private OpenWaterType() {
        }
    }
}