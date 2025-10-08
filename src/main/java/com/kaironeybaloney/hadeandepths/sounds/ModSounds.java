package com.kaironeybaloney.hadeandepths.sounds;

import com.kaironeybaloney.hadeandepths.HadeanDepths;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, HadeanDepths.MODID);

    public static final Holder<SoundEvent> DAVY_JONES_LOCKER_OPEN = SOUND_EVENTS.register(
            "davy_jones_locker_open",
            SoundEvent::createVariableRangeEvent
    );

    public static final Holder<SoundEvent> DAVY_JONES_LOCKER_CLOSE = SOUND_EVENTS.register(
            "davy_jones_locker_close",
            SoundEvent::createVariableRangeEvent
    );
}
