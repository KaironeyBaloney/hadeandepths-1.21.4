package com.kaironeybaloney.hadeandepths.client.renderer.entity;

import com.kaironeybaloney.hadeandepths.entity.HangingFishEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class HangingFishEntityRenderer extends EntityRenderer<HangingFishEntity> {
    public HangingFishEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(HangingFishEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
    }

    @Override
    public ResourceLocation getTextureLocation(HangingFishEntity entity) {
        return ResourceLocation.fromNamespaceAndPath("hadeandepths", "textures/entity/hanging_fish.png");
    }

    @Override
    protected boolean shouldShowName(HangingFishEntity entity) {
        return false;
    }

    @Override
    protected float getShadowRadius(HangingFishEntity entity) {
        return 0;
    }
}