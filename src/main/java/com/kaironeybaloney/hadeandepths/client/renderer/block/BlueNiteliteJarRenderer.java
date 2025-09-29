package com.kaironeybaloney.hadeandepths.client.renderer.block;

import com.kaironeybaloney.hadeandepths.HadeanDepths;
import com.kaironeybaloney.hadeandepths.block.entity.BlueNiteliteJarBlockEntity;
import com.kaironeybaloney.hadeandepths.item.ModItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.joml.Vector3f;

import java.util.List;


public class BlueNiteliteJarRenderer<T extends BlockEntity> implements BlockEntityRenderer<T> {
    private final BlockRenderDispatcher blockRenderer;

    public BlueNiteliteJarRenderer(BlockEntityRendererProvider.Context ctx) {
        this.blockRenderer = ctx.getBlockRenderDispatcher();

    }

    @Override
    public void render(T be, float pt, PoseStack ps, MultiBufferSource buffers,
                       int light, int overlay) {

        Level level = be.getLevel();
        BlockPos pos = be.getBlockPos();

        if (level != null && level.isClientSide()) {
            if (Minecraft.getInstance().isPaused()) return;
            int divisor = 8;
            RandomSource rand = level.getRandom();


            for (int i = 0; i < 1; i++) {
                if (rand.nextInt(divisor) != 0) {
                    break;
                }
                double x = pos.getX() + Math.clamp(rand.nextDouble(), 4.0 / 16, 12.0 / 16);
                double y = pos.getY() + Math.clamp(rand.nextDouble(), 1.0 / 16, 10.0 / 16);
                double z = pos.getZ() + Math.clamp(rand.nextDouble(), 4.0 / 16, 12.0 / 16);

                DustParticleOptions dust = new DustParticleOptions(5099007, 0.5f);

                level.addParticle(dust, x, y, z, 0, 10, 0);
            }
        }
        ps.pushPose();
        float time = (be.getLevel() != null ? be.getLevel().getGameTime() : 0) + pt;
        float bob  = Mth.sin(time * 0.05f) * 0.05f;
        float weave = Mth.sin(time * 0.05f) * 0.025f;
        float weave2 = Mth.sin(time * 0.075f) * 0.025f;
        ps.translate(0.5 + weave, bob + 0.5, 0.5 + weave2);
        ps.scale(1f, 1f, 1f);
        ps.mulPose(Axis.YP.rotationDegrees((time) % 360));

        VertexConsumer vc = buffers.getBuffer(Sheets.cutoutBlockSheet());


        ItemStack stack = new ItemStack(ModItems.BLUE_NITELITE_JELLYFISH.get());
        Minecraft.getInstance().getItemRenderer().renderStatic(
                stack,
                ItemDisplayContext.GROUND,
                light,
                overlay,
                ps,
                buffers,
                be.getLevel(),
                be.getBlockPos().getY()
        );

        ps.popPose();
    }
}