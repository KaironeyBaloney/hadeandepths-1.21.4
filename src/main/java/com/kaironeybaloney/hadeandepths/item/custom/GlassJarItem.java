package com.kaironeybaloney.hadeandepths.item.custom;

import com.kaironeybaloney.hadeandepths.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BottleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class GlassJarItem extends Item {

    public GlassJarItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level world, Player player, InteractionHand hand) {
        ItemStack stackInHand = player.getItemInHand(hand);

        BlockHitResult hitResult = getPlayerPOVHitResult(world, player, ClipContext.Fluid.SOURCE_ONLY);

        if (hitResult.getType() == HitResult.Type.MISS) {
            return InteractionResult.PASS;
        }

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos pos = hitResult.getBlockPos();
            if (!world.mayInteract(player, pos)) {
                return InteractionResult.PASS;
            }

            BlockState state = world.getBlockState(pos);

            if (state.is(Blocks.WATER_CAULDRON)) {
                int currentLevel = state.getValue(LayeredCauldronBlock.LEVEL);
                if (currentLevel > 0) {
                    if (!world.isClientSide) {
                        if (currentLevel > 1) {
                            world.setBlock(pos, state.setValue(LayeredCauldronBlock.LEVEL, currentLevel - 1), 3);
                        } else {
                            world.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 3);
                        }

                        world.playSound(
                                null,
                                player.getX(), player.getY(), player.getZ(),
                                SoundEvents.BOTTLE_FILL,
                                SoundSource.PLAYERS,
                                1.0F,
                                1.0F
                        );
                        world.gameEvent(player, GameEvent.FLUID_PICKUP, pos);
                    }

                    swapItem(player, stackInHand);
                    return InteractionResult.SUCCESS.heldItemTransformedTo(stackInHand);
                }
            }

            FluidState fluidState = world.getFluidState(pos);
            if (fluidState.is(FluidTags.WATER) && fluidState.isSource()) {
                if (!world.isClientSide) {
                    world.playSound(
                            null,
                            player.getX(), player.getY(), player.getZ(),
                            SoundEvents.BOTTLE_FILL,
                            SoundSource.PLAYERS,
                            1.0F,
                            1.0F
                    );
                    world.gameEvent(player, GameEvent.FLUID_PICKUP, pos);
                }

                swapItem(player, stackInHand);
                return InteractionResult.SUCCESS.heldItemTransformedTo(stackInHand);
            }
        }

        return InteractionResult.PASS;
    }

    private void swapItem(Player player, ItemStack original)
    {
        if (!player.getAbilities().instabuild) {
            original.shrink(1);
        }
            ItemStack result = new ItemStack(ModItems.WATER_JAR.get());
            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getInventory().add(result)) {
                player.drop(result, false);
            }
    }
}
