package com.kaironeybaloney.hadeandepths.data;

import com.kaironeybaloney.hadeandepths.HadeanDepths;
import com.kaironeybaloney.hadeandepths.data.custom.LoadedAmmoComponent;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModDataComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS_TYPES =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, HadeanDepths.MODID);

    public static final Supplier<DataComponentType<LoadedAmmoComponent>> LOADED_AMMO =
            DATA_COMPONENTS_TYPES.registerComponentType(
                    "loaded_ammo",
                    builder -> builder
                            .persistent(LoadedAmmoComponent.CODEC)
                            .networkSynchronized(LoadedAmmoComponent.STREAM_CODEC)
            );

    public static void register(IEventBus eventBus) {
        DATA_COMPONENTS_TYPES.register(eventBus);
    }
}
