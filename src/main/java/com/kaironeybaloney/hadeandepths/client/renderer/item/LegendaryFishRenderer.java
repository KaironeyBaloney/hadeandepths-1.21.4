package com.kaironeybaloney.hadeandepths.client.renderer.item;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LegendaryFishRenderer extends BlockEntityWithoutLevelRenderer {
    public LegendaryFishRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet entityModels) {
        super(dispatcher, entityModels);
    }

    @Override
    public void renderByItem(
            ItemStack stack,
            ItemDisplayContext displayContext,
            PoseStack poseStack,
            MultiBufferSource bufferSource,
            int packedLight,
            int packedOverlay
    ) {
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(stack.getItem());
        boolean guiContext = displayContext == ItemDisplayContext.GUI;
        boolean carried = Minecraft.getInstance().screen instanceof AbstractContainerScreen<?> screen
                && stack == screen.getMenu().getCarried();
        String model = (guiContext && !carried) ? "item/gui/" + itemId.getPath() : "item/" + itemId.getPath() + "_full";

        BakedModel baked = Minecraft.getInstance().getModelManager().getModel(
                new ModelResourceLocation(
                        ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), model),
                        ModelResourceLocation.STANDALONE_VARIANT));

        if (guiContext) {
            Lighting.setupForFlatItems();
        }

        poseStack.translate(0.5F, 0.5F, 0.5F);

        Minecraft.getInstance().getItemRenderer()
                .render(stack, displayContext, false, poseStack, bufferSource, packedLight, packedOverlay, baked);
    }
}