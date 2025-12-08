package com.kaironeybaloney.hadeandepths.block.custom;

import com.kaironeybaloney.hadeandepths.block.entity.DavyJonesLockerBlockEntity;
import com.kaironeybaloney.hadeandepths.block.entity.WoodenCrateBlockEntity;
import com.kaironeybaloney.hadeandepths.sounds.ModSounds;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class WoodenCrateBlock extends Block implements EntityBlock {
    public WoodenCrateBlock(Properties props) {
        super(props);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new WoodenCrateBlockEntity(blockPos, blockState);
    }

    @Override
    public MenuProvider getMenuProvider(BlockState state, Level world, BlockPos pos) {
        BlockEntity be = world.getBlockEntity(pos);
        return be instanceof MenuProvider ? (MenuProvider)be : null;
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof WoodenCrateBlockEntity crateBE) {
                MenuProvider menuprovider = this.getMenuProvider(state, level, pos);
                if (menuprovider != null) {
                    ((ServerPlayer) player).openMenu(new SimpleMenuProvider(crateBE, Component.literal("")), pos);
                    level.playSound(null, pos, SoundEvents.BARREL_OPEN, SoundSource.BLOCKS, 1F,
                            level.random.nextFloat() * 0.1F + 0.75F);
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        Containers.dropContentsOnDestroy(state, newState, level, pos);
        super.onRemove(state, level, pos, newState, isMoving);
    }
}
