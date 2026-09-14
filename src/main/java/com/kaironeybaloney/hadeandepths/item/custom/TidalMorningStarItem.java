package com.kaironeybaloney.hadeandepths.item.custom;

import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class TidalMorningStarItem extends SwordItem {
    private final int pierce;
    public TidalMorningStarItem(ToolMaterial material, float attackDamage, float attackSpeed, int piercePercentage, Properties properties) {
        super(material, attackDamage, attackSpeed, properties);
        pierce = piercePercentage;
    }

    public int getArmorPiercePercentage(ItemStack stack)
    {
        return this.pierce;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag flags) {
            tooltipComponents.add(Component.translatable("tooltip.hadeandepths.tidal_morning_star"));
        super.appendHoverText(stack, context, tooltipComponents, flags);
    }
}
