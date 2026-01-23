package com.kaironeybaloney.hadeandepths.client.renderer.entity;

import com.kaironeybaloney.hadeandepths.entity.FlameSeekerArrowEntity;
import com.kaironeybaloney.hadeandepths.entity.ToothArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.ResourceLocation;

public class FlameSeekerArrowRenderer extends ArrowRenderer<FlameSeekerArrowEntity, ArrowRenderState> {
    public FlameSeekerArrowRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public ArrowRenderState createRenderState() {
        return new ArrowRenderState();
    }

    @Override
    protected ResourceLocation getTextureLocation(ArrowRenderState arrowRenderState) {
        return ResourceLocation.fromNamespaceAndPath("hadeandepths", "textures/entity/projectiles/flame_seeker_arrow_projectile.png");
    }
}
