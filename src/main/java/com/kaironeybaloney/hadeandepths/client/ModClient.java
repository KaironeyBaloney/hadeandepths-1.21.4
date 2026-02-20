package com.kaironeybaloney.hadeandepths.client;

import com.kaironeybaloney.hadeandepths.HadeanDepths;
import com.kaironeybaloney.hadeandepths.block.ModBlocks;
import com.kaironeybaloney.hadeandepths.block.entity.BlueNiteliteJarBlockEntity;
import com.kaironeybaloney.hadeandepths.block.entity.ModBlockEntities;
import com.kaironeybaloney.hadeandepths.client.properties.LoadedArrowsProperty;
import com.kaironeybaloney.hadeandepths.client.renderer.block.*;
import com.kaironeybaloney.hadeandepths.client.renderer.entity.*;
import com.kaironeybaloney.hadeandepths.data.ModDataComponents;
import com.kaironeybaloney.hadeandepths.data.custom.LoadedAmmoComponent;
import com.kaironeybaloney.hadeandepths.entity.ModEntities;
import com.kaironeybaloney.hadeandepths.item.ModItems;
import com.kaironeybaloney.hadeandepths.screen.ModMenuTypes;
import com.kaironeybaloney.hadeandepths.screen.custom.DavyJonesLockerScreen;
import com.kaironeybaloney.hadeandepths.screen.custom.WoodenCrateScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperties;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperty;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperties;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterRangeSelectItemModelPropertyEvent;
import net.neoforged.neoforge.common.brewing.BrewingRecipeRegistry;

@EventBusSubscriber(modid = HadeanDepths.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClient {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLUE_NITELITE_JAR.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.GREEN_NITELITE_JAR.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.PINK_NITELITE_JAR.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.GLOWY_GOOP_BLOCK.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.BUTCHERING_HOOK.get(), RenderType.cutout());


        });

    }
    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.BLUE_NITELITE_JAR_BLOCK_ENTITY.get(), BlueNiteliteJarRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.GREEN_NITELITE_JAR_BLOCK_ENTITY.get(), GreenNiteliteJarRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.PINK_NITELITE_JAR_BLOCK_ENTITY.get(), PinkNiteliteJarRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.DAVY_JONES_LOCKER_BLOCK_ENTITY.get(), DavyJonesLockerRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BUTCHERING_HOOK_BLOCK_ENTITY.get(), ButcheringHookRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.CRUCIBLE_BLOCK_ENTITY.get(), CrucibleRenderer::new);
    }

    @SubscribeEvent
    public static void registerER(EntityRenderersEvent.RegisterRenderers event) {
        EntityRenderers.register(ModEntities.HANGING_FISH_COMMON.get(), HangingFishEntityRenderer::new);
        EntityRenderers.register(ModEntities.HANGING_FISH_LEGENDARY.get(), HangingFishEntityRenderer::new);
        EntityRenderers.register(ModEntities.TOOTH_ARROW.get(), ToothArrowRenderer::new);
        EntityRenderers.register(ModEntities.FLAME_SEEKER_ARROW.get(), FlameSeekerArrowRenderer::new);
        EntityRenderers.register(ModEntities.WOODEN_FISHING_BOBBER.get(), WoodenFishingHookRenderer::new);
        EntityRenderers.register(ModEntities.IRON_FISHING_BOBBER.get(), IronFishingHookRenderer::new);
        EntityRenderers.register(ModEntities.DIAMOND_FISHING_BOBBER.get(), DiamondFishingHookRenderer::new);
        EntityRenderers.register(ModEntities.NETHERITE_FISHING_BOBBER.get(), NetheriteFishingHookRenderer::new);
        EntityRenderers.register(ModEntities.MAGMATIC_FISHING_BOBBER.get(), MagmaticFishingHookRenderer::new);
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.DAVY_JONES_LOCKER_MENU.get(), DavyJonesLockerScreen::new);
        event.register(ModMenuTypes.WOODEN_CRATE_MENU.get(), WoodenCrateScreen::new);
    }

    @SubscribeEvent
    public static void registerRangeProperties(RegisterRangeSelectItemModelPropertyEvent event) {
        event.register(
                ResourceLocation.fromNamespaceAndPath("hadeandepths", "loaded_arrows"),
                LoadedArrowsProperty.MAP_CODEC
        );
    }
}
