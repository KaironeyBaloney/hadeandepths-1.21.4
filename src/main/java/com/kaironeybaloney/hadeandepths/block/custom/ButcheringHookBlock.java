package com.kaironeybaloney.hadeandepths.block.custom;

import com.kaironeybaloney.hadeandepths.block.entity.ButcheringHookBlockEntity;
import com.kaironeybaloney.hadeandepths.block.entity.WoodenCrateBlockEntity;
import com.kaironeybaloney.hadeandepths.entity.HangingFishEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Interaction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class ButcheringHookBlock extends HorizontalDirectionalBlock implements EntityBlock {

    public ButcheringHookBlock(Properties props) {
        super(props);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return null;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction opp = context.getHorizontalDirection().getOpposite();
        return this.defaultBlockState().setValue(FACING, opp);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    public static final VoxelShape SHAPE = Stream.of(
           Block.box(7.5, 5, 7.5, 8.5, 7, 8.5), Block.box(8, 1, 5.5, 8, 5, 9.5),
            Block.box(5.5, 9, 5, 10.5, 14, 6),
            Block.box(7.5, 11, 4.5, 8.5, 13, 5.5),
            Block.box(6, 7, 6, 10, 16, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ButcheringHookBlockEntity(blockPos, blockState);
    }

    @Override
    public InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

        var be = level.getBlockEntity(pos);
        if (!(be instanceof ButcheringHookBlockEntity hookBE)) {
            return InteractionResult.PASS;
        }

        ItemStack held = player.getItemInHand(hand);

        TagKey<Item> FISH_TAG = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("hadeandepths", "fish"));

        if (held.isEmpty()) {
            ItemStack removed = hookBE.removeItem();
            if (!removed.isEmpty()) {
                if (!player.getInventory().add(removed.copy())) {
                    player.drop(removed.copy(), false);
                }
                level.sendBlockUpdated(pos, state, state, 3);
                hookBE.setChanged();
            }
            return InteractionResult.SUCCESS;
        }

        if (held.is(FISH_TAG)) {
            if (hookBE.isEmpty()) {
                ItemStack toInsert = held.copy();
                toInsert.setCount(1);
                hookBE.setItem(toInsert);
                if (!player.isCreative()) {
                    held.shrink(1);
                }
                level.sendBlockUpdated(pos, state, state, 3);
                hookBE.setChanged();
                return InteractionResult.SUCCESS;
            } else {
                ItemStack removed = hookBE.removeItem();
                if (!removed.isEmpty()) {
                    if (!player.getInventory().add(removed.copy())) {
                        player.drop(removed.copy(), false);
                    }
                    level.sendBlockUpdated(pos, state, state, 3);
                    hookBE.setChanged();
                }
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.FAIL;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {

            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof ButcheringHookBlockEntity hookBE) {
                hookBE.drops();
            }

            level.getEntitiesOfClass(HangingFishEntity.class, new AABB(pos))
                    .forEach(Entity::discard);

            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }
}