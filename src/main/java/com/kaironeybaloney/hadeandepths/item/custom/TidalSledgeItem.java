package com.kaironeybaloney.hadeandepths.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class TidalSledgeItem extends SwordItem {
    public TidalSledgeItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties) {
        super(material, attackDamage, attackSpeed, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean result = super.hurtEnemy(stack, target, attacker);

        if (!attacker.level().isClientSide()) {
            float strength = 1F;

            double x = -Math.sin(attacker.getYRot() * (Math.PI / 180.0F)) * strength;
            double z =  Math.cos(attacker.getYRot() * (Math.PI / 180.0F)) * strength;

            target.push(x, 0.1F, z);
            target.hurtMarked = true;
        }

        if(attacker instanceof Player player)
        {
            float charge = ((Player) attacker).getAttackStrengthScale(0.5f);
            if(charge > 0.5 && !target.level().isClientSide())
            {
                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 1, true, false));
            }
        }


        return result;
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity victim, LivingEntity attacker) {

        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag flags) {
        tooltipComponents.add(Component.translatable("tooltip.hadeandepths.tidal_sledge"));
        super.appendHoverText(stack, context, tooltipComponents, flags);
    }
}
