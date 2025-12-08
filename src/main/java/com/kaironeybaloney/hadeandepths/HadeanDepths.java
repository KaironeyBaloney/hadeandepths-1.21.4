package com.kaironeybaloney.hadeandepths;

import com.kaironeybaloney.hadeandepths.data.ModDataComponents;
import com.kaironeybaloney.hadeandepths.datagen.advancements.ModAdvancementGenerator;
import com.kaironeybaloney.hadeandepths.datagen.advancements.ModAdvancementProvider;
import com.kaironeybaloney.hadeandepths.block.ModBlocks;
import com.kaironeybaloney.hadeandepths.block.entity.ModBlockEntities;
import com.kaironeybaloney.hadeandepths.entity.ModEntities;
import com.kaironeybaloney.hadeandepths.event.TickScheduler;
import com.kaironeybaloney.hadeandepths.item.ModCreativeModeTabs;
import com.kaironeybaloney.hadeandepths.item.ModItems;
import com.kaironeybaloney.hadeandepths.potions.ModPotions;
import com.kaironeybaloney.hadeandepths.screen.ModMenuTypes;
import com.kaironeybaloney.hadeandepths.sounds.ModSounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.List;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(HadeanDepths.MODID)
public class HadeanDepths {
    public static final String MODID = "hadeandepths";
    private static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public HadeanDepths(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::OnGatherData);
        NeoForge.EVENT_BUS.addListener(this::onLevelTick);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModPotions.POTIONS.register(modEventBus);
        ModDataComponents.DATA_COMPONENTS_TYPES.register(modEventBus);

        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);

        ModMenuTypes.register(modEventBus);


        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public void OnGatherData(GatherDataEvent.Client event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var lookupProvider = event.getLookupProvider();

        generator.addProvider(
                true,
                new ModAdvancementProvider(output, lookupProvider, List.of(new ModAdvancementGenerator()))
        );
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {

        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    public void onLevelTick(LevelTickEvent.Post event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            TickScheduler.onLevelTick(serverLevel);
        }
    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}