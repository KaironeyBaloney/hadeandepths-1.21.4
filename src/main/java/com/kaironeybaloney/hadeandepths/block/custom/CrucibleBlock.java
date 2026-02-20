package com.kaironeybaloney.hadeandepths.block.custom;

import com.kaironeybaloney.hadeandepths.block.entity.CrucibleBlockEntity;
import com.kaironeybaloney.hadeandepths.block.entity.ModBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CrucibleBlock extends AbstractFurnaceBlock {
    public CrucibleBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends AbstractFurnaceBlock> codec() {
        return null;
    }

    @Override
    protected void openContainer(Level level, BlockPos blockPos, Player player) {

    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
       return new CrucibleBlockEntity(ModBlockEntities.CRUCIBLE_BLOCK_ENTITY.get(), blockPos, blockState, RecipeType.SMELTING);
    }

}
