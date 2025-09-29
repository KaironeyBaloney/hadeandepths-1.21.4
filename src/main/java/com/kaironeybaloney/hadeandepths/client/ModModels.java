package com.kaironeybaloney.hadeandepths.client;

import com.kaironeybaloney.hadeandepths.HadeanDepths;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ModelEvent;

import static com.kaironeybaloney.hadeandepths.HadeanDepths.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModModels {
    public static final ResourceLocation JELLY_RL =
            ResourceLocation.fromNamespaceAndPath(MODID, "block/blue_nitelite_jar_jellyfish");

    @SubscribeEvent
    public static void registerAdditionalModels(ModelEvent.RegisterAdditional event) {
        event.register(JELLY_RL);
    }
}
