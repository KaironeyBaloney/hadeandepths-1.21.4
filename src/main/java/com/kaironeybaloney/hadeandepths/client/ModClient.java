package com.kaironeybaloney.hadeandepths.client;

import com.kaironeybaloney.hadeandepths.HadeanDepths;
import com.kaironeybaloney.hadeandepths.block.ModBlocks;
import com.kaironeybaloney.hadeandepths.block.entity.BlueNiteliteJarBlockEntity;
import com.kaironeybaloney.hadeandepths.block.entity.ModBlockEntities;
import com.kaironeybaloney.hadeandepths.client.renderer.block.*;
import com.kaironeybaloney.hadeandepths.client.renderer.entity.HangingFishEntityRenderer;
import com.kaironeybaloney.hadeandepths.entity.ModEntities;
import com.kaironeybaloney.hadeandepths.screen.ModMenuTypes;
import com.kaironeybaloney.hadeandepths.screen.custom.DavyJonesLockerScreen;
import com.kaironeybaloney.hadeandepths.screen.custom.WoodenCrateScreen;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = HadeanDepths.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClient {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLUE_NITELITE_JAR.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.GREEN_NITELITE_JAR.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.PINK_NITELITE_JAR.get(), RenderType.translucent());
        });
    }
    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.BLUE_NITELITE_JAR_BLOCK_ENTITY.get(), BlueNiteliteJarRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.GREEN_NITELITE_JAR_BLOCK_ENTITY.get(), GreenNiteliteJarRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.PINK_NITELITE_JAR_BLOCK_ENTITY.get(), PinkNiteliteJarRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.DAVY_JONES_LOCKER_BLOCK_ENTITY.get(), DavyJonesLockerRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BUTCHERING_HOOK_BLOCK_ENTITY.get(), ButcheringHookRenderer::new);
    }

    @SubscribeEvent
    public static void registerER(EntityRenderersEvent.RegisterRenderers event) {
        EntityRenderers.register(ModEntities.HANGING_FISH_COMMON.get(), HangingFishEntityRenderer::new);
        EntityRenderers.register(ModEntities.HANGING_FISH_LEGENDARY.get(), HangingFishEntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.DAVY_JONES_LOCKER_MENU.get(), DavyJonesLockerScreen::new);
        event.register(ModMenuTypes.WOODEN_CRATE_MENU.get(), WoodenCrateScreen::new);
    }
}
