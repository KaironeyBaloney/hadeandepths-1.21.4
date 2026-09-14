package com.kaironeybaloney.hadeandepths.block.entity;

import com.kaironeybaloney.hadeandepths.client.renderer.block.CrucibleRenderer;
import com.kaironeybaloney.hadeandepths.screen.custom.CrucibleMenu;
import com.kaironeybaloney.hadeandepths.screen.custom.DavyJonesLockerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.world.level.block.AbstractFurnaceBlock.LIT;

public class CrucibleBlockEntity extends AbstractFurnaceBlockEntity {
    public CrucibleBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.CRUCIBLE_BLOCK_ENTITY.get(), pos, blockState, RecipeType.SMELTING);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("crucible");
    }

    public int cookingTimer() {
        return dataAccess.get(2);
    }

    public int cookingTotalTime() {
        return dataAccess.get(3);
    }

    public int litTimeRemaining() {
        return dataAccess.get(0);
    }

    public int litTotalTime() {
        return dataAccess.get(1);
    }

    public boolean isLit() {
        return dataAccess.get(0) > 0;
    }

    @Override
    protected AbstractFurnaceMenu createMenu(int i, Inventory inventory) {
        return new CrucibleMenu(i, inventory, this, this.dataAccess);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, CrucibleBlockEntity furnace) {
        if(!level.isClientSide())
        {
            AbstractFurnaceBlockEntity.serverTick((ServerLevel) level, pos, state, furnace);
            AbstractFurnaceBlockEntity.serverTick((ServerLevel) level, pos, state, furnace);
            AbstractFurnaceBlockEntity.serverTick((ServerLevel) level, pos, state, furnace);
        }
    }
}