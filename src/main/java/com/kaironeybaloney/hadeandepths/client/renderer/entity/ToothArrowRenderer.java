package com.kaironeybaloney.hadeandepths.client.renderer.entity;

import com.kaironeybaloney.hadeandepths.entity.ToothArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ToothArrowRenderer extends ArrowRenderer<ToothArrowEntity> {
    public ToothArrowRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public ResourceLocation getTextureLocation(ToothArrowEntity entity) {
        return ResourceLocation.fromNamespaceAndPath("hadeandepths", "textures/entity/projectiles/tooth_arrow_projectile.png");
    }
}