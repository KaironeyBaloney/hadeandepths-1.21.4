package com.kaironeybaloney.hadeandepths.client.renderer.entity;

import com.kaironeybaloney.hadeandepths.entity.FlameSeekerArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class FlameSeekerArrowRenderer extends ArrowRenderer<FlameSeekerArrowEntity> {
    public FlameSeekerArrowRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public ResourceLocation getTextureLocation(FlameSeekerArrowEntity entity) {
        return ResourceLocation.fromNamespaceAndPath("hadeandepths", "textures/entity/projectiles/flame_seeker_arrow_projectile.png");
    }
}