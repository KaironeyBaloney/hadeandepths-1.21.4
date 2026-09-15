package com.kaironeybaloney.hadeandepths.client;

import com.kaironeybaloney.hadeandepths.HadeanDepths;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ModelEvent;

import java.util.List;

import static com.kaironeybaloney.hadeandepths.HadeanDepths.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModModels {
    public static final ResourceLocation JELLY_RL =
            ResourceLocation.fromNamespaceAndPath(MODID, "block/blue_nitelite_jar_jellyfish");

    public static final List<String> LEGENDARY_FISH_PATHS = List.of(
            "great_white_shark", "manta_ray", "tuna", "colossal_squid", "dunkleosteus",
            "sea_serpent", "amethyst_grouper", "giant_catfish", "sturgeon", "frilled_shark",
            "orca", "phantom_jellyfish", "megalodon", "netheritic_contraption", "magma_wyrm");

    @SubscribeEvent
    public static void registerAdditionalModels(ModelEvent.RegisterAdditional event) {
        event.register(new ModelResourceLocation(JELLY_RL, ModelResourceLocation.STANDALONE_VARIANT));
        for (String path : LEGENDARY_FISH_PATHS) {
            event.register(new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(MODID, "item/gui/" + path), ModelResourceLocation.STANDALONE_VARIANT));
            event.register(new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(MODID, "item/" + path + "_full"), ModelResourceLocation.STANDALONE_VARIANT));
        }
    }
}
