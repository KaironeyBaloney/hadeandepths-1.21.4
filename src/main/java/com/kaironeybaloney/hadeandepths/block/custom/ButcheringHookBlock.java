package com.kaironeybaloney.hadeandepths.block.custom;

import com.kaironeybaloney.hadeandepths.block.entity.ButcheringHookBlockEntity;
import com.kaironeybaloney.hadeandepths.block.entity.WoodenCrateBlockEntity;
import com.kaironeybaloney.hadeandepths.entity.HangingFishEntity;
import com.kaironeybaloney.hadeandepths.item.ModItems;
import com.kaironeybaloney.hadeandepths.item.custom.FishItem;
import com.kaironeybaloney.hadeandepths.item.custom.FishRarity;
import com.kaironeybaloney.hadeandepths.item.custom.FishType;
import com.kaironeybaloney.hadeandepths.sounds.ModSounds;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.chat.report.ReportEnvironment;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Interaction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public class ButcheringHookBlock extends Block implements EntityBlock, SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public ButcheringHookBlock(Properties props) {
        super(props);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        boolean water = level.getFluidState(pos).getType() == Fluids.WATER;
        return this.defaultBlockState().setValue(WATERLOGGED, water);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {

        if (direction == Direction.UP && !state.canSurvive(world, pos)) {
            if (world instanceof Level level) {
                level.destroyBlock(pos, true);
            }
            return Blocks.AIR.defaultBlockState();
        }

        return super.updateShape(state, world, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return Block.canSupportCenter(world, pos.above(), Direction.DOWN);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    public VoxelShape makeShape(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.46875, 0.3125, 0.46875, 0.53125, 0.4375, 0.53125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5, 0.125, 0.21875, 0.5, 0.3125, 0.78125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.40625, 0.6875, 0.5, 0.59375, 1, 0.5), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5, 0.6875, 0.40625, 0.5, 1, 0.59375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 0.4375, 0.375, 0.625, 0.6875, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4062499999999999, 0.6875, 0.40625, 0.5937499999999999, 1, 0.5937500000000001), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.21875, 0.125, 0.5, 0.78125, 0.3125, 0.5), BooleanOp.OR);

        return shape;
    }

    public final VoxelShape SHAPE = this.makeShape();

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }



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

        if(!level.isClientSide) {
            Direction face = hit.getDirection();

            if (face == Direction.UP || face == Direction.DOWN) {
                Vec3 playerPos = player.position();
                Vec3 blockCenter = Vec3.atCenterOf(pos);

                double dx = playerPos.x - blockCenter.x;
                double dz = playerPos.z - blockCenter.z;

                if (Math.abs(dx) > Math.abs(dz)) {
                    face = dx > 0 ? Direction.EAST : Direction.WEST;
                } else {
                    face = dz > 0 ? Direction.SOUTH : Direction.NORTH;
                }
            }

            hookBE.setClickedFace(face);
        }

        ItemStack held = player.getItemInHand(hand);

        TagKey<Item> FISH_TAG = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("hadeandepths", "fish"));
        TagKey<Item> BUTCHERING_TAG = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("hadeandepths", "butchering_item"));

        if (held.is(BUTCHERING_TAG) && !hookBE.isEmpty())
        {
            ItemStack fishStack = hookBE.getItem();
            level.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1F,
                    level.random.nextFloat() * 0.1F + 0.9F);
            dropFishLoot(level, pos, hookBE.getItem());
            if(fishStack.getItem() instanceof FishItem fishItem)
            {
                FishRarity rarity = fishItem.getRarity(fishStack);
                FishType type = fishItem.getType(fishStack);
                double randomValue = level.random.nextDouble();
                switch (rarity)
                {
                    case FishRarity.COMMON:
                    {
                        if(type != FishType.MAGMATIC && type !=  FishType.NETHEROUS) {
                            if (randomValue <= 0.02) {
                                Containers.dropItemStack(level, pos.getX() + 0.5, pos.getY() - 0.5, pos.getZ() + 0.5, ModItems.DEEP_SEA_RESIDUE.toStack(1));
                            }
                        }
                        break;
                    }
                    case FishRarity.UNCOMMON:
                    {
                        if(type != FishType.MAGMATIC && type !=  FishType.NETHEROUS) {
                            if (randomValue <= 0.06) {
                                Containers.dropItemStack(level, pos.getX() + 0.5, pos.getY() - 0.5, pos.getZ() + 0.5, ModItems.DEEP_SEA_RESIDUE.toStack(1));
                            }
                        }
                        break;
                    }
                    case FishRarity.RARE:
                    {
                        if(type != FishType.MAGMATIC && type !=  FishType.NETHEROUS) {
                            if (randomValue <= 0.18) {
                                Containers.dropItemStack(level, pos.getX() + 0.5, pos.getY() - 0.5, pos.getZ() + 0.5, ModItems.DEEP_SEA_RESIDUE.toStack(1));
                            }
                        }

                        break;
                    }
                    case FishRarity.LEGENDARY:
                    {
                        if(type != FishType.MAGMATIC && type !=  FishType.NETHEROUS) {
                            if (randomValue <= 0.50) {
                                Containers.dropItemStack(level, pos.getX() + 0.5, pos.getY() - 1, pos.getZ() + 0.5, ModItems.DEEP_SEA_RESIDUE.toStack(1));
                            }
                        }
                        Containers.dropItemStack(level, pos.getX() + 0.5,pos.getY() - 1, pos.getZ() + 0.5, ModItems.LEGENDARY_HEART.toStack(1));
                        break;
                    }
                }
            }
            ItemStack removed = hookBE.removeItem();
            hookBE.setChanged();
            player.getMainHandItem().hurtAndBreak(1, player,
                    hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
            return InteractionResult.SUCCESS;
        }
        else if (held.isEmpty()) {
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
        else if (held.is(FISH_TAG)) {
            if (hookBE.isEmpty()) {
                level.playSound(null, pos, SoundEvents.SALMON_FLOP, SoundSource.BLOCKS, 1F,
                        level.random.nextFloat() * 0.1F + 0.9F);
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
                level.playSound(null, pos, SoundEvents.SALMON_FLOP, SoundSource.BLOCKS, 1F,
                        level.random.nextFloat() * 0.1F + 0.9F);
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
            Containers.dropItemStack(level, pos.getX() + 0.5, isLegendary ? pos.getY() - 1 : pos.getY() - 0.5, pos.getZ() + 0.5, stack);
        }
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
}