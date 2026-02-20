package com.kaironeybaloney.hadeandepths.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.world.level.block.AbstractFurnaceBlock.LIT;

public class CrucibleBlockEntity extends AbstractFurnaceBlockEntity {


    public CrucibleBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState, RecipeType<? extends AbstractCookingRecipe> recipeType) {
        super(type, pos, blockState, recipeType);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("crucible");
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new FurnaceMenu(i, inventory);
    }
}