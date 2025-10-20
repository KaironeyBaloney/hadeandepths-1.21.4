package com.kaironeybaloney.hadeandepths.entity;

import com.kaironeybaloney.hadeandepths.block.entity.ButcheringHookBlockEntity;
import com.kaironeybaloney.hadeandepths.item.custom.FishItem;
import com.kaironeybaloney.hadeandepths.item.custom.FishRarity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class HangingFishEntity extends Entity {
    private BlockPos hookPos;
    private ItemStack fishStack = ItemStack.EMPTY;
    private Level level;

    public HangingFishEntity(EntityType<? extends HangingFishEntity> type, Level level2) {
        super(type, level2);
        this.noPhysics = true;
        level = level2;
    }

    public void setHook(BlockPos pos) {
        this.hookPos = pos;
    }

    public BlockPos getHookPos() {
        return hookPos;
    }

    public Level getLevel() {
        return level;
    }

    public ButcheringHookBlockEntity getHookBlockEntity() {
        if (hookPos == null)
        {
            return null;
        }
        BlockEntity be = level.getBlockEntity(hookPos);
        if (be instanceof ButcheringHookBlockEntity hook)
        {
            return hook;
        }
        return null;
    }

    public void setFishStack(ItemStack stack) {
        this.fishStack = stack;
    }

    public ItemStack getFishStack() {
        return fishStack;
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {

        TagKey<Item> FISH_TAG = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("hadeandepths", "fish"));

        if (!level.isClientSide) {
            System.out.println("INTERACTED WITH FISH");

            ButcheringHookBlockEntity hook = getHookBlockEntity();
            if (hook == null) return InteractionResult.PASS;

            ItemStack held = player.getItemInHand(hand);

            if (held.isEmpty()) {
                ItemStack removed = hook.removeItem();
                if (!removed.isEmpty()) {
                    if (!player.getInventory().add(removed.copy()))
                        player.drop(removed.copy(), false);
                }
                this.remove(RemovalReason.DISCARDED);
            } else if (held.is(FISH_TAG)) {
                if (hook.isEmpty()) {
                    ItemStack toInsert = held.copy();
                    toInsert.setCount(1);
                    hook.setItem(toInsert);
                    if (!player.isCreative()) {
                        held.shrink(1);
                    }
                    hook.setChanged();
                    return InteractionResult.SUCCESS;
                } else {
                    ItemStack removed = hook.removeItem();
                    if (!removed.isEmpty()) {
                        if (!player.getInventory().add(removed.copy())) {
                            player.drop(removed.copy(), false);
                        }
                        hook.setChanged();
                    }
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.FAIL;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public boolean hurtServer(ServerLevel serverLevel, DamageSource damageSource, float v) {
        return false;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {

    }
}
