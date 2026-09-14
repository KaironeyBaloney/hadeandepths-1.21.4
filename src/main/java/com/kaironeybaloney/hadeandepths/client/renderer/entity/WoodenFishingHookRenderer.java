package com.kaironeybaloney.hadeandepths.client.renderer.entity;

import com.kaironeybaloney.hadeandepths.entity.WoodenFishingHook;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.client.renderer.entity.state.FishingHookRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.ItemAbilities;

public class WoodenFishingHookRenderer extends EntityRenderer<WoodenFishingHook, FishingHookRenderState> {
    private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath("hadeandepths", "textures/entity/wooden_fishing_hook.png");
    private static final RenderType RENDER_TYPE;
    private static final double VIEW_BOBBING_SCALE = 960.0;

    public WoodenFishingHookRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    public boolean shouldRender(WoodenFishingHook p_363069_, Frustum p_362635_, double p_361840_, double p_361502_, double p_360380_) {
        return super.shouldRender(p_363069_, p_362635_, p_361840_, p_361502_, p_360380_) && p_363069_.getPlayerOwner() != null;
    }

    public void render(FishingHookRenderState p_362456_, PoseStack p_114699_, MultiBufferSource p_114700_, int p_114701_) {
        p_114699_.pushPose();
        p_114699_.pushPose();
        p_114699_.scale(0.5F, 0.5F, 0.5F);
        p_114699_.mulPose(this.entityRenderDispatcher.cameraOrientation());
        PoseStack.Pose posestack$pose = p_114699_.last();
        VertexConsumer vertexconsumer = p_114700_.getBuffer(RENDER_TYPE);
        vertex(vertexconsumer, posestack$pose, p_114701_, 0.0F, 0, 0, 1);
        vertex(vertexconsumer, posestack$pose, p_114701_, 1.0F, 0, 1, 1);
        vertex(vertexconsumer, posestack$pose, p_114701_, 1.0F, 1, 1, 0);
        vertex(vertexconsumer, posestack$pose, p_114701_, 0.0F, 1, 0, 0);
        p_114699_.popPose();
        float f = (float) p_362456_.lineOriginOffset.x;
        float f1 = (float) p_362456_.lineOriginOffset.y;
        float f2 = (float) p_362456_.lineOriginOffset.z;
        VertexConsumer vertexconsumer1 = p_114700_.getBuffer(RenderType.lineStrip());
        PoseStack.Pose posestack$pose1 = p_114699_.last();

        for (int j = 0; j <= 16; ++j) {
            stringVertex(f, f1, f2, vertexconsumer1, posestack$pose1, fraction(j, 16), fraction(j + 1, 16));
        }

        p_114699_.popPose();
        super.render(p_362456_, p_114699_, p_114700_, p_114701_);
    }

    public static HumanoidArm getHoldingArm(Player player) {
        return player.getMainHandItem().canPerformAction(ItemAbilities.FISHING_ROD_CAST) ? player.getMainArm() : player.getMainArm().getOpposite();
    }

    private Vec3 getPlayerHandPos(Player player, float handAngle, float partialTick) {
        int i = getHoldingArm(player) == HumanoidArm.RIGHT ? 1 : -1;
        if (this.entityRenderDispatcher.options.getCameraType().isFirstPerson() && player == Minecraft.getInstance().player) {
            double d4 = 960.0 / (double) (Integer) this.entityRenderDispatcher.options.fov().get();
            Vec3 vec3 = this.entityRenderDispatcher.camera.getNearPlane().getPointOnPlane((float) i * 0.525F, -0.1F).scale(d4).yRot(handAngle * 0.5F).xRot(-handAngle * 0.7F);
            return player.getEyePosition(partialTick).add(vec3);
        } else {
            float f = Mth.lerp(partialTick, player.yBodyRotO, player.yBodyRot) * 0.017453292F;
            double d0 = (double) Mth.sin(f);
            double d1 = (double) Mth.cos(f);
            float f1 = player.getScale();
            double d2 = (double) i * 0.35 * (double) f1;
            double d3 = 0.8 * (double) f1;
            float f2 = player.isCrouching() ? -0.1875F : 0.0F;
            return player.getEyePosition(partialTick).add(-d1 * d2 - d0 * d3, (double) f2 - 0.45 * (double) f1, -d0 * d2 + d1 * d3);
        }
    }

    private static float fraction(int numerator, int denominator) {
        return (float) numerator / (float) denominator;
    }

    private static void vertex(VertexConsumer consumer, PoseStack.Pose pose, int packedLight, float x, int y, int u, int v) {
        consumer.addVertex(pose, x - 0.5F, (float) y - 0.5F, 0.0F).setColor(-1).setUv((float) u, (float) v).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(pose, 0.0F, 1.0F, 0.0F);
    }

    private static void stringVertex(float x, float y, float z, VertexConsumer consumer, PoseStack.Pose pose, float stringFraction, float nextStringFraction) {
        float f = x * stringFraction;
        float f1 = y * (stringFraction * stringFraction + stringFraction) * 0.5F + 0.25F;
        float f2 = z * stringFraction;
        float f3 = x * nextStringFraction - f;
        float f4 = y * (nextStringFraction * nextStringFraction + nextStringFraction) * 0.5F + 0.25F - f1;
        float f5 = z * nextStringFraction - f2;
        float f6 = Mth.sqrt(f3 * f3 + f4 * f4 + f5 * f5);
        f3 /= f6;
        f4 /= f6;
        f5 /= f6;
        consumer.addVertex(pose, f, f1, f2).setColor(-16777216).setNormal(pose, f3, f4, f5);
    }

    public FishingHookRenderState createRenderState() {
        return new FishingHookRenderState();
    }

    public void extractRenderState(WoodenFishingHook p_361584_, FishingHookRenderState p_364824_, float p_360891_) {
        super.extractRenderState(p_361584_, p_364824_, p_360891_);
        Player player = p_361584_.getPlayerOwner();
        if (player == null) {
            p_364824_.lineOriginOffset = Vec3.ZERO;
        } else {
            float f = player.getAttackAnim(p_360891_);
            float f1 = Mth.sin(Mth.sqrt(f) * 3.1415927F);
            Vec3 vec3 = this.getPlayerHandPos(player, f1, p_360891_);
            Vec3 vec31 = p_361584_.getPosition(p_360891_).add(0.0, 0.25, 0.0);
            p_364824_.lineOriginOffset = vec3.subtract(vec31);
        }

    }

    protected boolean affectedByCulling(WoodenFishingHook p_365042_) {
        return false;
    }

    static {
        RENDER_TYPE = RenderType.entityCutout(TEXTURE_LOCATION);
    }
}
