package com.kaironeybaloney.hadeandepths.client.renderer.block;

import com.kaironeybaloney.hadeandepths.block.entity.DavyJonesLockerBlockEntity;
import com.kaironeybaloney.hadeandepths.client.model.DavyJonesLockerModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class DavyJonesLockerRenderer extends GeoBlockRenderer<DavyJonesLockerBlockEntity> {
    public DavyJonesLockerRenderer(BlockEntityRendererProvider.Context context) {
        super(new DavyJonesLockerModel());
    }
}
