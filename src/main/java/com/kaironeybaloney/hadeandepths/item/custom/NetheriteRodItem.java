package com.kaironeybaloney.hadeandepths.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.event.EventHooks;

import java.util.List;

public class NetheriteRodItem extends FishingRodItem {
    public NetheriteRodItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (player.fishing != null) {
            if (!level.isClientSide) {
                int i = player.fishing.retrieve(itemstack);
                ItemStack original = itemstack.copy();
                itemstack.hurtAndBreak(i, player, LivingEntity.getSlotForHand(hand));
                if (itemstack.isEmpty()) {
                    EventHooks.onPlayerDestroyItem(player, original, hand);
                }
            }

            level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE, SoundSource.NEUTRAL, 1.0F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            player.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
        } else {
            level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            if (level instanceof ServerLevel) {
                ServerLevel serverlevel = (ServerLevel)level;
                int j = (int)((EnchantmentHelper.getFishingTimeReduction(serverlevel, itemstack, player) * 20.0F) * 1.5F);
                int k = (int) ((EnchantmentHelper.getFishingLuckBonus(serverlevel, itemstack, player)) * 1.5F);
                Projectile.spawnProjectile(new FishingHook(player, level, k, j), serverlevel, itemstack);
            }

            player.awardStat(Stats.ITEM_USED.get(this));
            player.gameEvent(GameEvent.ITEM_INTERACT_START);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()) {
            ResourceLocation id = BuiltInRegistries.ITEM.getKey(this);
            if (id != null) {
                tooltipComponents.add(Component.translatable("tooltip." + id.getNamespace() + "." + id.getPath() + "_lure")
                        .withStyle(ChatFormatting.GRAY));
                tooltipComponents.add(Component.translatable("tooltip." + id.getNamespace() + "." + id.getPath() + "_luck")
                        .withStyle(ChatFormatting.GRAY));
            }
        } else {
            tooltipComponents.add(Component.translatable("tooltip.hadeandepths.shift_up"));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
