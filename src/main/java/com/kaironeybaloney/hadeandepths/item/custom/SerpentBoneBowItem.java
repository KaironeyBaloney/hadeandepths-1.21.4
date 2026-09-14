package com.kaironeybaloney.hadeandepths.item.custom;

import com.kaironeybaloney.hadeandepths.data.ModDataComponents;
import com.kaironeybaloney.hadeandepths.data.custom.LoadedAmmoComponent;
import com.kaironeybaloney.hadeandepths.event.TickScheduler;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.event.EventHooks;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SerpentBoneBowItem extends BowItem {
    public SerpentBoneBowItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean releaseUsing(ItemStack stack, Level level, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof Player player)) return false;
        if (!(level instanceof ServerLevel serverLevel)) return false;

        LoadedAmmoComponent comp = stack.get(ModDataComponents.LOADED_AMMO);
        if (comp == null || comp.ammo().isEmpty()) return false;

        float charge = getPowerForTime(this.getUseDuration(stack, player) - remainingUseTicks);
        if (charge < 0.1F) return false;

        List<ItemStack> arrowsToFire = comp.ammo();

        stack.remove(ModDataComponents.LOADED_AMMO);

        if (level instanceof ServerLevel) {
            ServerLevel serverlevel = (ServerLevel)level;

            if (!arrowsToFire.isEmpty()) {
                shoot(serverlevel, player, player.getUsedItemHand(), stack, arrowsToFire, charge * 3.0F, 1.0F, charge == 1.0F, (LivingEntity)null);

            }
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        return true;
    }

    protected void shoot(ServerLevel level, LivingEntity shooter, InteractionHand hand, ItemStack weapon, List<ItemStack> projectileItems, float velocity, float inaccuracy, boolean isCrit, @Nullable LivingEntity target) {
        float f = EnchantmentHelper.processProjectileSpread(level, weapon, shooter, 0.0F);
        float f1 = projectileItems.size() == 1 ? 0.0F : 2.0F * f / (float)(projectileItems.size() - 1);
        float f2 = (float)((projectileItems.size() - 1) % 2) * f1 / 2.0F;
        float f3 = 1.0F;

        for (int i = 0; i < projectileItems.size(); ++i) {
            ItemStack itemstack = projectileItems.get(i);
            if (itemstack.isEmpty()) continue;


            float f4 = f2 + f3 * (float)((i + 1) / 2) * f1;
            int index = i;

            int delay = 11 * index;

            TickScheduler.schedule(level, delay, () -> {
                Projectile.spawnProjectile(
                        this.createProjectile(level, shooter, weapon, itemstack, isCrit),
                        level,
                        itemstack,
                        (p) -> this.shootProjectile(shooter, p, index, velocity, inaccuracy, f4, target)
                );
                level.playSound((Player)null, shooter.getX(), shooter.getY(), shooter.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f4 * 0.5F);
                level.playSound((Player)null, shooter.getX(), shooter.getY(), shooter.getZ(), SoundEvents.TRIDENT_RIPTIDE_1, SoundSource.PLAYERS, 0.25F * velocity, 1.5F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f4 * 0.5F);

                if(shooter instanceof Player) {
                    if(!((Player) shooter).isCreative())
                    {
                        weapon.hurtAndBreak(
                                this.getDurabilityUse(itemstack),
                                shooter,
                                LivingEntity.getSlotForHand(hand)
                        );

                    }
                        ((Player) shooter).getInventory().items.stream()
                                .filter(s -> !s.isEmpty() && ItemStack.isSameItemSameComponents(s, projectileItems.get(index)))
                                .findFirst()
                                .ifPresent(s -> s.shrink(1));
                }

            });
        }
    }

    public static float getPowerForTime(int charge) {
        float f = (float)charge / 20.0F;
        f = (f * f + f * 3.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        boolean flag = !player.getProjectile(stack).isEmpty();
        InteractionResult ret = EventHooks.onArrowNock(stack, level, player, hand, flag);
        if (ret != null) return ret;
        if (!player.hasInfiniteMaterials() && !flag) return InteractionResult.FAIL;

        List<ItemStack> ammo = new ArrayList<>();
        Set<Integer> used = new HashSet<>();

        if(!level.isClientSide()) {
            for (int slot = 0; slot < player.getInventory().getContainerSize(); ++slot) {
                ItemStack invStack = player.getInventory().getItem(slot);

                if (invStack.is(ItemTags.ARROWS)) {
                    ammo.add(invStack.copyWithCount(1));
                    if (invStack.getCount() < 2) used.add(slot);
                    level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.CROSSBOW_LOADING_START, SoundSource.PLAYERS, 1.5F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F));
                    System.out.println("Added arrow on use(): " + invStack);
                    stack.set(ModDataComponents.LOADED_AMMO, new LoadedAmmoComponent(ammo, used));
                    player.startUsingItem(hand);
                    return InteractionResult.CONSUME;
                }
            }
            if(player.isCreative())
            {
                ItemStack arrowStack = Items.ARROW.getDefaultInstance();
                ammo.add(arrowStack.copyWithCount(1));
                stack.set(ModDataComponents.LOADED_AMMO, new LoadedAmmoComponent(ammo, used));
                player.startUsingItem(hand);
                level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.CROSSBOW_LOADING_START, SoundSource.PLAYERS, 1.5F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F));
                return InteractionResult.CONSUME;
            }
        }

        return InteractionResult.FAIL;
    }


    @Override
    public void onUseTick(Level level, LivingEntity user, ItemStack stack, int count) {
        if (!(user instanceof Player player)) return;
        if (level.isClientSide()) return;

        LoadedAmmoComponent comp = stack.get(ModDataComponents.LOADED_AMMO);
        int useTime = this.getUseDuration(stack, user) - count;
        int ammoCount = comp.ammo().size();

        if (useTime >= 35 && ammoCount == 1) {

            List<ItemStack> ammo = new ArrayList<>(comp.ammo());
            Set<Integer> used = new HashSet<>(comp.usedSlots());
            boolean addedArrow = false;

            for (int slot = 0; slot < player.getInventory().getContainerSize(); ++slot) {
                if (used.contains(slot)) continue;

                ItemStack inv = player.getInventory().getItem(slot);
                if (inv.is(ItemTags.ARROWS)) {
                    ammo.add(inv.copyWithCount(1));
                    if (inv.getCount() < 3) used.add(slot);
                    addedArrow = true;
                    stack.set(ModDataComponents.LOADED_AMMO, new LoadedAmmoComponent(ammo, used));

                    level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.CROSSBOW_LOADING_END, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F));
                    System.out.println("Added arrow #2 from slot " + slot);
                    break;
                }
            }

            if(!addedArrow)
            {
                if(player.isCreative())
                {
                    ItemStack arrowStack = Items.ARROW.getDefaultInstance();
                    ammo.add(arrowStack.copyWithCount(1));
                    stack.set(ModDataComponents.LOADED_AMMO, new LoadedAmmoComponent(ammo, used));
                    level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.CROSSBOW_LOADING_END, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F));
                }
            }
        }

        if (useTime >= 50 && ammoCount == 2) {
            boolean addedArrow = false;
            List<ItemStack> ammo = new ArrayList<>(comp.ammo());
            Set<Integer> used = new HashSet<>(comp.usedSlots());

            for (int slot = 0; slot < player.getInventory().getContainerSize(); ++slot) {
                if (used.contains(slot)) continue;

                ItemStack inv = player.getInventory().getItem(slot);
                if (inv.is(ItemTags.ARROWS)) {
                    ammo.add(inv.copyWithCount(1));

                    stack.set(ModDataComponents.LOADED_AMMO, new LoadedAmmoComponent(ammo, used));
                    addedArrow = true;
                    level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.CROSSBOW_LOADING_END, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F));

                    System.out.println("Added arrow #3 from slot " + slot);
                    break;
                }
            }

            if(!addedArrow)
            {
                if(player.isCreative())
                {
                    ItemStack arrowStack = Items.ARROW.getDefaultInstance();
                    ammo.add(arrowStack.copyWithCount(1));
                    stack.set(ModDataComponents.LOADED_AMMO, new LoadedAmmoComponent(ammo, used));
                    level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.CROSSBOW_LOADING_END, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F));
                }
            }
        }
    }

    @Override
    public int getUseDuration(ItemStack p_40680_, LivingEntity p_345962_) {
        return 72000;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag flags) {
        tooltipComponents.add(Component.translatable("tooltip.hadeandepths.tidal_bow"));
        super.appendHoverText(stack, context, tooltipComponents, flags);
    }
}
