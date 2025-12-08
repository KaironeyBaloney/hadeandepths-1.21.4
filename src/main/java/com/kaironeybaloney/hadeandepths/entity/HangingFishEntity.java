package com.kaironeybaloney.hadeandepths.entity;

import com.kaironeybaloney.hadeandepths.block.entity.ButcheringHookBlockEntity;
import com.kaironeybaloney.hadeandepths.item.custom.FishItem;
import com.kaironeybaloney.hadeandepths.item.custom.FishRarity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Interaction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

import java.util.List;

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
        TagKey<Item> BUTCHERING_TAG = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("hadeandepths", "butchering_item"));

        if (!level.isClientSide) {
            System.out.println("INTERACTED WITH FISH");

            ButcheringHookBlockEntity hook = getHookBlockEntity();
            if (hook == null) return InteractionResult.PASS;

            ItemStack held = player.getItemInHand(hand);

            if (held.is(BUTCHERING_TAG) && !hook.isEmpty())
            {
                level.playSound(null, hookPos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1F,
                        level.random.nextFloat() * 0.1F + 0.9F);
                dropFishLoot(level, hookPos, hook.getItem());
                ItemStack removed = hook.removeItem();
                hook.setChanged();
                player.getMainHandItem().hurtAndBreak(1, player,
                        hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
                return InteractionResult.SUCCESS;
            }
            else if (held.isEmpty()) {
                level.playSound(null, hookPos, SoundEvents.SALMON_FLOP, SoundSource.BLOCKS, 1F,
                        level.random.nextFloat() * 0.1F + 0.9F);
                ItemStack removed = hook.removeItem();
                if (!removed.isEmpty()) {
                    if (!player.getInventory().add(removed.copy()))
                        player.drop(removed.copy(), false);
                }
                this.remove(RemovalReason.DISCARDED);
            } else if (held.is(FISH_TAG)) {
                level.playSound(null, hookPos, SoundEvents.SALMON_FLOP, SoundSource.BLOCKS, 1F,
                        level.random.nextFloat() * 0.1F + 0.9F);
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

    private void dropFishLoot(Level level, BlockPos pos, ItemStack fish) {
        if (fish.isEmpty() || !(level instanceof ServerLevel serverLevel)) return;
        FishRarity rarity = FishRarity.COMMON;
        if (fish.getItem() instanceof FishItem fishItem) {
            rarity = fishItem.getRarity(fish);
        }
        boolean isLegendary = rarity == FishRarity.LEGENDARY;

        ResourceLocation fishID = BuiltInRegistries.ITEM.getKey(fish.getItem());

        ResourceLocation lootTableID = ResourceLocation.fromNamespaceAndPath("hadeandepths", "butchering/" + fishID.getPath());
        ResourceKey<LootTable> lootTableKey = ResourceKey.create(Registries.LOOT_TABLE, lootTableID);
        LootTable lootTable = serverLevel.getServer().reloadableRegistries().getLootTable(lootTableKey);

        LootParams params = new LootParams.Builder(serverLevel)
                .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                .withParameter(LootContextParams.BLOCK_STATE, level.getBlockState(pos))
                .withParameter(LootContextParams.TOOL, ItemStack.EMPTY)
                .create(LootContextParamSets.BLOCK);

        List<ItemStack> drops = lootTable.getRandomItems(params);
        for (ItemStack stack : drops) {
            Containers.dropItemStack(level, pos.getX() + 0.5, isLegendary ? pos.getY() - 1.0 : pos.getY() - 0.5, pos.getZ() + 0.5, stack);
        }
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
