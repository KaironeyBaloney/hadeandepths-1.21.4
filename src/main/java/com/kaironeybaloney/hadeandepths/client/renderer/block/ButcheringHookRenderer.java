package com.kaironeybaloney.hadeandepths.client.renderer.block;

import com.kaironeybaloney.hadeandepths.block.ModBlocks;
import com.kaironeybaloney.hadeandepths.block.entity.ButcheringHookBlockEntity;
import com.kaironeybaloney.hadeandepths.block.entity.DavyJonesLockerBlockEntity;
import com.kaironeybaloney.hadeandepths.client.model.ButcheringHookModel;
import com.kaironeybaloney.hadeandepths.client.model.DavyJonesLockerModel;
import com.kaironeybaloney.hadeandepths.item.custom.FishAttachPoint;
import com.kaironeybaloney.hadeandepths.item.custom.FishItem;
import com.kaironeybaloney.hadeandepths.item.custom.FishRarity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.util.RenderUtil;

public class ButcheringHookRenderer extends GeoBlockRenderer<ButcheringHookBlockEntity> {
    public ButcheringHookRenderer(BlockEntityRendererProvider.Context context) {
        super(new ButcheringHookModel());
    }
    @Override
    public AABB getRenderBoundingBox(ButcheringHookBlockEntity blockEntity) {
        BlockPos pos = blockEntity.getBlockPos();
        return new AABB(
                pos.getX(), pos.getY() - 1.5, pos.getZ(),
                pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1
        );
    }



    @Override
    public void render(ButcheringHookBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        //super.render(blockEntity, partialTick, poseStack, bufferSource, 0xF000F0, packedOverlay);

        ItemStack stack = blockEntity.getItem();
        if (stack.isEmpty()) return;

        poseStack.pushPose();

        poseStack.translate(0.5, 0.5, 0.5);

        Direction clickedFace = blockEntity.getClickedFace();

        float yRot = switch (clickedFace) {
            case NORTH -> 0f;
            case SOUTH -> 180f;
            case WEST -> 90f;
            case EAST -> -90f;
            default -> 0f;
        };
        poseStack.mulPose(Axis.YP.rotationDegrees(yRot));

        FishRarity rarity = FishRarity.COMMON;
        Item fish = stack.getItem();
        if (fish instanceof FishItem fishItem) {
            rarity = fishItem.getRarity(stack);

            boolean isLegendary = rarity == FishRarity.LEGENDARY;

            float rot = switch (((FishItem) fish).getAttachPoint(stack)) {
                case FishAttachPoint.BOTTOM_LEFT -> 135f;
                case FishAttachPoint.BOTTOM_RIGHT -> -135f;
                case FishAttachPoint.TOP_LEFT -> 45;
                case FishAttachPoint.TOP_RIGHT -> -45;
                case FishAttachPoint.TOP_MIDDLE -> 0f;
                default -> 135f;
            };


            if (isLegendary) {
                poseStack.pushPose();
                poseStack.translate(0.0, -18/16.0, -2.5/16.0);
                poseStack.mulPose(Axis.ZP.rotationDegrees(rot));
                poseStack.scale(0.8f, 0.8f, 0.8f);

                Minecraft.getInstance().getItemRenderer().renderStatic(
                        stack,
                        ItemDisplayContext.FIXED,
                        packedLight,
                        packedOverlay,
                        poseStack,
                        bufferSource,
                        blockEntity.getLevel(),
                        0
                );

                poseStack.popPose();
            } else {
                poseStack.translate(0.0, -11.0/16.0, -2.5/16.0);
                poseStack.mulPose(Axis.ZP.rotationDegrees(rot));
                poseStack.scale(0.7f, 0.7f, 0.7f);


                Minecraft.getInstance().getItemRenderer().renderStatic(
                        stack,
                        ItemDisplayContext.FIXED,
                        packedLight,
                        packedOverlay,
                        poseStack,
                        bufferSource,
                        blockEntity.getLevel(),
                        0
                );
            }

            poseStack.popPose();
        }
        else
        {
            poseStack.translate(0.0, -11.0/16.0, -2.5/16.0);
            poseStack.mulPose(Axis.ZP.rotationDegrees(135f));
            poseStack.scale(0.7f, 0.7f, 0.7f);


            Minecraft.getInstance().getItemRenderer().renderStatic(
                    stack,
                    ItemDisplayContext.FIXED,
                    packedLight,
                    packedOverlay,
                    poseStack,
                    bufferSource,
                    blockEntity.getLevel(),
                    0
            );

            poseStack.popPose();
        }
    }
}