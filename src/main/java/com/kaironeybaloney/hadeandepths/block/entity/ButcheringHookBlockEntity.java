package com.kaironeybaloney.hadeandepths.block.entity;


import com.kaironeybaloney.hadeandepths.entity.HangingFishEntity;
import com.kaironeybaloney.hadeandepths.entity.ModEntities;
import com.kaironeybaloney.hadeandepths.item.custom.FishItem;
import com.kaironeybaloney.hadeandepths.item.custom.FishRarity;
import com.kaironeybaloney.hadeandepths.screen.custom.WoodenCrateMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;

import static com.kaironeybaloney.hadeandepths.block.entity.ModBlockEntities.BUTCHERING_HOOK_BLOCK_ENTITY;
import static com.kaironeybaloney.hadeandepths.block.entity.ModBlockEntities.WOODEN_CRATE_BLOCK_ENTITY;

public class ButcheringHookBlockEntity extends BlockEntity implements GeoAnimatable {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public final ItemStackHandler inventory = new ItemStackHandler(1) {
        @Override
        protected int getStackLimit(int slot, ItemStack stack) {
            return 1;
        }



        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            ButcheringHookBlockEntity.this.setChanged();
            if (ButcheringHookBlockEntity.this.level != null && !ButcheringHookBlockEntity.this.level.isClientSide) {
                ButcheringHookBlockEntity.this.level.sendBlockUpdated(
                        ButcheringHookBlockEntity.this.worldPosition,
                        ButcheringHookBlockEntity.this.getBlockState(),
                        ButcheringHookBlockEntity.this.getBlockState(),
                        3
                );
            }
        }
    };

    public boolean isEmpty() {
        return this.inventory.getStackInSlot(0).isEmpty();
    }

    public ItemStack getItem() {
        return this.inventory.getStackInSlot(0);
    }

    public void setItem(ItemStack stack) {
        this.inventory.setStackInSlot(0, stack);
        this.setChanged();

        if (this.level != null && !this.level.isClientSide) {
            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);

            if (!stack.isEmpty()) {
                spawnHangingFish(this.level, this.worldPosition);
            } else {
                removeHangingFish(this.level, this.worldPosition);
            }
        }
    }

    public ItemStack removeItem() {
        ItemStack current = this.inventory.getStackInSlot(0);
        if (current.isEmpty()) return ItemStack.EMPTY;

        ItemStack ret = current.copy();
        this.inventory.setStackInSlot(0, ItemStack.EMPTY);
        this.setChanged();

        if (this.level != null && !this.level.isClientSide) {
            removeHangingFish(this.level, this.worldPosition);

            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
        }

        return ret;
    }

    public ButcheringHookBlockEntity(BlockPos pos, BlockState state) {
        super(BUTCHERING_HOOK_BLOCK_ENTITY.get(), pos, state);
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventory.getSlots());
        for(int i = 0; i < inventory.getSlots(); i++) {
            inv.setItem(i, inventory.getStackInSlot(i));
        }

        assert this.level != null;
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        if (tag.contains("Inventory")) {
            inventory.deserializeNBT(provider, tag.getCompound("Inventory"));
        }
    }

    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);
        tag.put("Inventory", inventory.serializeNBT(provider));
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider provider) {
        if (tag.contains("Inventory")) {
            inventory.deserializeNBT(provider, tag.getCompound("Inventory"));
        }
    }

    public void spawnHangingFish(Level level, BlockPos pos) {
        if (level.isClientSide()) {
            return;
        }
        ItemStack stack = inventory.getStackInSlot(0);
        if (stack.isEmpty()) {
            return;
        }


        boolean exists = !level.getEntitiesOfClass(HangingFishEntity.class, new AABB(pos)).isEmpty();
        if (exists) {
            return;
        }

        FishRarity rarity = FishRarity.COMMON;
        if (stack.getItem() instanceof FishItem fishItem) {
            rarity = fishItem.getRarity(stack);
        }

        boolean isLegendary = rarity == FishRarity.LEGENDARY;

        if(isLegendary) {
            HangingFishEntity entity = new HangingFishEntity(ModEntities.HANGING_FISH_LEGENDARY.get(), level);
            entity.setHook(pos);
            entity.setFishStack(getItem());

            entity.setPos(pos.getX() + 0.5, pos.getY() - 1.25, pos.getZ() + 0.5);

            level.addFreshEntity(entity);
        }
        else
        {
            HangingFishEntity entity = new HangingFishEntity(ModEntities.HANGING_FISH_COMMON.get(), level);
            entity.setHook(pos);
            entity.setFishStack(getItem());

            entity.setPos(pos.getX() + 0.5, pos.getY() - 0.5, pos.getZ() + 0.5);

            level.addFreshEntity(entity);
        }
    }

    public void removeHangingFish(Level level, BlockPos pos) {
        if (level.isClientSide()) return;

        level.getEntitiesOfClass(HangingFishEntity.class, new AABB(pos))
                .forEach(Entity::discard);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object object) {
        return 0;
    }
}