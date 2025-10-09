package com.kaironeybaloney.hadeandepths.block.entity;


import com.kaironeybaloney.hadeandepths.screen.custom.DavyJonesLockerMenu;
import com.kaironeybaloney.hadeandepths.screen.custom.WoodenCrateMenu;
import com.kaironeybaloney.hadeandepths.sounds.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import static com.kaironeybaloney.hadeandepths.block.entity.ModBlockEntities.WOODEN_CRATE_BLOCK_ENTITY;

public class WoodenCrateBlockEntity extends RandomizableContainerBlockEntity implements MenuProvider {
    private NonNullList<ItemStack> items;


    public WoodenCrateBlockEntity(BlockPos pos, BlockState state) {
        super(WOODEN_CRATE_BLOCK_ENTITY.get(), pos, state);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
    }
    @Override
    protected Component getDefaultName() {
        return Component.translatable("wooden_crate");
    }

    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(tag)) {
            ContainerHelper.loadAllItems(tag, this.items, provider);
        }

    }

    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);
        if (!this.trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, this.items, provider);
        }

    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory inv) {
        return new WoodenCrateMenu(id, inv, this);
    }

    @Override
    public int getContainerSize() {
        return 36;
    }
}