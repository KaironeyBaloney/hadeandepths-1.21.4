package com.kaironeybaloney.hadeandepths.block.entity;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import static com.kaironeybaloney.hadeandepths.block.entity.ModBlockEntities.BLUE_NITELITE_JAR_BLOCK_ENTITY;

public class BlueNiteliteJarBlockEntity extends BlockEntity {
    public BlueNiteliteJarBlockEntity(BlockPos pos, BlockState state) {
        super(BLUE_NITELITE_JAR_BLOCK_ENTITY.get(), pos, state);
    }

    private int tickCounter = 0;

    public static void tick(Level level, BlockPos pos, BlockState state, BlueNiteliteJarBlockEntity be) {
        be.tickCounter++;
    }

    public int getTickCounter() {
        return tickCounter;
    }
}