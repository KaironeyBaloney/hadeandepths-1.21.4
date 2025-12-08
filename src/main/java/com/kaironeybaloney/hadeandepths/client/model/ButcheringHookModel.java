package com.kaironeybaloney.hadeandepths.client.model;

import com.kaironeybaloney.hadeandepths.block.entity.ButcheringHookBlockEntity;
import com.kaironeybaloney.hadeandepths.block.entity.DavyJonesLockerBlockEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class ButcheringHookModel extends GeoModel<ButcheringHookBlockEntity> {
    private static final ResourceLocation MODEL = ResourceLocation.fromNamespaceAndPath("hadeandepths", "geo/butchering_hook.geo.json");
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("hadeandepths", "textures/block/butchering_hook.png");
    private static final ResourceLocation ANIM = ResourceLocation.fromNamespaceAndPath("hadeandepths", "animations/butchering_hook.animation.json");


    @Override
    public ResourceLocation getModelResource(ButcheringHookBlockEntity animatable, @Nullable GeoRenderer<ButcheringHookBlockEntity> renderer) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(ButcheringHookBlockEntity animatable, @Nullable GeoRenderer<ButcheringHookBlockEntity> renderer) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(ButcheringHookBlockEntity animatable) {
        return ANIM;
    }

    @Override
    public RenderType getRenderType(ButcheringHookBlockEntity animatable, ResourceLocation texture) {
        return RenderType.entityCutoutNoCull(texture);
    }

}
