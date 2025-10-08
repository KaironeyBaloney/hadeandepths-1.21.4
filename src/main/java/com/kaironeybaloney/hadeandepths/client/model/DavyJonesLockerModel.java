package com.kaironeybaloney.hadeandepths.client.model;

import com.kaironeybaloney.hadeandepths.block.entity.DavyJonesLockerBlockEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class DavyJonesLockerModel extends GeoModel<DavyJonesLockerBlockEntity> {
    private static final ResourceLocation MODEL = ResourceLocation.fromNamespaceAndPath("hadeandepths", "geo/davy_jones_locker.geo.json");
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("hadeandepths", "textures/block/davy_jones_locker.png");
    private static final ResourceLocation ANIM =ResourceLocation.fromNamespaceAndPath("hadeandepths", "animations/davy_jones_locker.animation.json");


    @Override
    public ResourceLocation getModelResource(DavyJonesLockerBlockEntity animatable, @Nullable GeoRenderer<DavyJonesLockerBlockEntity> renderer) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(DavyJonesLockerBlockEntity animatable, @Nullable GeoRenderer<DavyJonesLockerBlockEntity> renderer) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(DavyJonesLockerBlockEntity animatable) {
        return ANIM;
    }

    @Override
    public void setCustomAnimations(DavyJonesLockerBlockEntity entity, long instanceId, AnimationState<DavyJonesLockerBlockEntity> state) {
        super.setCustomAnimations(entity, instanceId, state);

        var lid = this.getAnimationProcessor().getBone("lid");
        if (lid != null) {
            float openness = entity.getOpenness(state.getPartialTick());
            lid.setRotY(openness * ((float) Math.PI / 2F));
        }
    }
}
