package com.kaironeybaloney.hadeandepths.block.entity;


import com.kaironeybaloney.hadeandepths.block.custom.DavyJonesLockerBlock;
import com.kaironeybaloney.hadeandepths.screen.custom.DavyJonesLockerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.awt.*;

import static com.kaironeybaloney.hadeandepths.block.entity.ModBlockEntities.DAVY_JONES_LOCKER_BLOCK_ENTITY;

public class DavyJonesLockerBlockEntity extends RandomizableContainerBlockEntity implements MenuProvider, GeoBlockEntity {
    private NonNullList<ItemStack> items;
    private final AnimatableInstanceCache animatableCache = GeckoLibUtil.createInstanceCache(this);

    private static final RawAnimation OPEN_CLOSE_ANIM = RawAnimation.begin().thenPlayAndHold("animation.davy_jones_locker.open_close");

    private float animationProgress = 0f;
    public boolean isOpening() {
        return openersCounter.getOpenerCount() > 0;
    }

    private final ContainerOpenersCounter openersCounter;

    public DavyJonesLockerBlockEntity(BlockPos pos, BlockState state) {
        super(DAVY_JONES_LOCKER_BLOCK_ENTITY.get(), pos, state);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        this.openersCounter  = new ContainerOpenersCounter() {
            @Override
            protected void onOpen(Level level, BlockPos blockPos, BlockState blockState) {

            }

            @Override
            protected void onClose(Level level, BlockPos blockPos, BlockState blockState) {

            }

            @Override
            protected void openerCountChanged(Level level, BlockPos blockPos, BlockState blockState, int oldCount, int newCount) {
            }

            @Override
            protected boolean isOwnContainer(Player player) {
                if (!(player.containerMenu instanceof DavyJonesLockerMenu)) {
                    return false;
                } else {
                    Container container = ((DavyJonesLockerMenu)player.containerMenu).getContainer();
                    return container == DavyJonesLockerBlockEntity.this;
                }
            }
        };
    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, DavyJonesLockerBlockEntity be) {
        if (!level.isClientSide) {
            be.openersCounter.recheckOpeners(level, pos, state);
        }
        System.out.println("IsOpening = " + be.isOpening());
    }

    public static int getOpenCount(BlockGetter level, BlockPos pos) {
        BlockState blockstate = level.getBlockState(pos);
        if (blockstate.hasBlockEntity()) {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof DavyJonesLockerBlockEntity) {
                return ((DavyJonesLockerBlockEntity)blockentity).openersCounter.getOpenerCount();
            }
        }

        return 0;
    }

    public void startOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    public void stopOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("Davy_Jones_Locker");
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
        return new DavyJonesLockerMenu(id, inv, this);
    }

    @Override
    public int getContainerSize() {
        return 36;
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(
                new AnimationController<>(this, "main_controller", 0, this::predicate)
                        .triggerableAnim("open_close", OPEN_CLOSE_ANIM)
        );
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.animatableCache;
    }

    public <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        AnimationController<E> controller = event.getController();
        controller.transitionLength(5);

        if (isOpening()) {
            controller.setAnimation(
                    RawAnimation.begin()
                            .then("animation.davy_jones_locker.open_close", Animation.LoopType.PLAY_ONCE)
            );
            controller.setAnimationSpeed(1.0f);
        } else {
            controller.setAnimation(
                    RawAnimation.begin()
                            .then("animation.davy_jones_locker.open_close", Animation.LoopType.PLAY_ONCE)
            );
            controller.setAnimationSpeed(-1.0f);
        }

        return PlayState.CONTINUE;
    }
}