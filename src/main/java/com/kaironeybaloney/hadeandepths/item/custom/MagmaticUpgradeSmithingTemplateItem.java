package com.kaironeybaloney.hadeandepths.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;


public class MagmaticUpgradeSmithingTemplateItem extends Item {
    public MagmaticUpgradeSmithingTemplateItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.hadeandepths.magmatic_upgrade_smithing_template_tooltip"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
