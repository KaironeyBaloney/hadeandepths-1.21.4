package com.kaironeybaloney.hadeandepths.client;

import com.kaironeybaloney.hadeandepths.HadeanDepths;
import com.kaironeybaloney.hadeandepths.block.ModBlocks;
import com.kaironeybaloney.hadeandepths.block.entity.BlueNiteliteJarBlockEntity;
import com.kaironeybaloney.hadeandepths.block.entity.ModBlockEntities;
import com.kaironeybaloney.hadeandepths.client.renderer.block.*;
import com.kaironeybaloney.hadeandepths.client.renderer.entity.*;
import com.kaironeybaloney.hadeandepths.client.renderer.item.LegendaryFishRenderer;
import com.kaironeybaloney.hadeandepths.data.ModDataComponents;
import com.kaironeybaloney.hadeandepths.data.custom.LoadedAmmoComponent;
import com.kaironeybaloney.hadeandepths.entity.ModEntities;
import com.kaironeybaloney.hadeandepths.item.ModItems;
import com.kaironeybaloney.hadeandepths.screen.ModMenuTypes;
import com.kaironeybaloney.hadeandepths.screen.custom.CrucibleScreen;
import com.kaironeybaloney.hadeandepths.screen.custom.DavyJonesLockerScreen;
import com.kaironeybaloney.hadeandepths.screen.custom.WoodenCrateScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
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
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.common.brewing.BrewingRecipeRegistry;

@EventBusSubscriber(modid = HadeanDepths.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClient {
    private static final IClientItemExtensions LEGENDARY_FISH_EXTENSIONS = new IClientItemExtensions() {
        private LegendaryFishRenderer renderer;

        @Override
        public BlockEntityWithoutLevelRenderer getCustomRenderer() {
            if (renderer == null) {
                renderer = new LegendaryFishRenderer(
                        Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                        Minecraft.getInstance().getEntityModels());
            }
            return renderer;
        }
    };

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLUE_NITELITE_JAR.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.GREEN_NITELITE_JAR.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.PINK_NITELITE_JAR.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.GLOWY_GOOP_BLOCK.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.BUTCHERING_HOOK.get(), RenderType.cutout());

            ItemProperties.register(
                    ModItems.SERPENT_BONE_BOW.get(),
                    ResourceLocation.fromNamespaceAndPath("hadeandepths", "loaded_arrows"),
                    (stack, level, entity, seed) -> {
                        var comp = stack.get(ModDataComponents.LOADED_AMMO);
                        return comp == null ? 0f : (float) comp.ammo().size();
                    });

            ItemProperties.register(
                    ModItems.SERPENT_BONE_BOW.get(),
                    ResourceLocation.fromNamespaceAndPath("hadeandepths", "using_item"),
                    (stack, level, entity, seed) ->
                            entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);

            ItemProperties.register(
                    ModItems.SERPENT_BONE_BOW.get(),
                    ResourceLocation.fromNamespaceAndPath("hadeandepths", "use_duration"),
                    (stack, level, entity, seed) -> {
                        if (entity == null) {
                            return 0.0F;
                        }
                        return (float) (stack.getUseDuration(entity) - entity.getUseItemRemainingTicks());
                    });
        });
}

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(LEGENDARY_FISH_EXTENSIONS,
                ModItems.GREAT_WHITE_SHARK.get(), ModItems.MANTA_RAY.get(), ModItems.TUNA.get(),
                ModItems.COLOSSAL_SQUID.get(), ModItems.DUNKLEOSTEUS.get(), ModItems.SEA_SERPENT.get(),
                ModItems.AMETHYST_GROUPER.get(), ModItems.GIANT_CATFISH.get(), ModItems.STURGEON.get(),
                ModItems.FRILLED_SHARK.get(), ModItems.ORCA.get(), ModItems.PHANTOM_JELLYFISH.get(),
                ModItems.MEGALODON.get(), ModItems.NETHERITIC_CONTRAPTION.get(), ModItems.MAGMA_WYRM.get());
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
        event.register(ModMenuTypes.CRUCIBLE_MENU.get(), CrucibleScreen::new);
    }
}
