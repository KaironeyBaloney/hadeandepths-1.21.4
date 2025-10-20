package com.kaironeybaloney.hadeandepths.client.renderer.entity;

import com.kaironeybaloney.hadeandepths.entity.HangingFishEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.phys.Vec3;

public class HangingFishEntityRenderer extends EntityRenderer<HangingFishEntity, EntityRenderState> {
    public HangingFishEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public EntityRenderState createRenderState() {
        return new EntityRenderState();
    }

    public void render(HangingFishEntity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
    }

    @Override
    protected boolean shouldShowName(HangingFishEntity entity, double distanceToCameraSq) {
        return false;
    }

    @Override

    protected float getShadowRadius(EntityRenderState renderState) {
        return 0;
    }

    @Override

    protected float getShadowStrength(EntityRenderState renderState) {
        return 0;
    }

}
