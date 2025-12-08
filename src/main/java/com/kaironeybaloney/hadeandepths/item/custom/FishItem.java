package com.kaironeybaloney.hadeandepths.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class FishItem extends Item {
    public FishItem(Properties properties, FishRarity itemRarity, FishType fishType) {
        super(properties);
        this.rarity = itemRarity;
        this.type = fishType;
        this.attachPoint = FishAttachPoint.BOTTOM_LEFT;

    }

    public FishItem(Properties properties, FishRarity itemRarity, FishType fishType, FishAttachPoint fishAttachPoint) {
        super(properties);
        this.rarity = itemRarity;
        this.type = fishType;
        this.attachPoint = fishAttachPoint;

    }

    private final FishRarity rarity;
    private final FishType type;
    private final FishAttachPoint attachPoint;

    public FishRarity getRarity(ItemStack stack) {
        return this.rarity;
    }
    public FishType getType(ItemStack stack) {
        return this.type;
    }
    public FishAttachPoint getAttachPoint(ItemStack stack) {
        return this.attachPoint;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag flags) {
        if (Screen.hasShiftDown()) {
            ResourceLocation id = BuiltInRegistries.ITEM.getKey(this);
            if (id != null) {
                Component rarityComp = Component.translatable("rarity." + rarity.getSerializedName());
                Component typeComp   = Component.translatable("type."   + type.getSerializedName());

                Component combined = Component.translatable(
                        "tooltip.hadeandepths.rarity_type",
                        rarityComp,
                        typeComp
                );

                tooltipComponents.add(combined);
                tooltipComponents.add(
                        Component.translatable("tooltip." + id.getNamespace() + "." + id.getPath())
                                .withStyle(ChatFormatting.GRAY)
                );
            }
        } else {
            tooltipComponents.add(Component.translatable("tooltip.hadeandepths.shift_up"));
        }

        super.appendHoverText(stack, context, tooltipComponents, flags);
    }
}
