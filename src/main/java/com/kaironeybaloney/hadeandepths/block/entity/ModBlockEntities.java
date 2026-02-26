package com.kaironeybaloney.hadeandepths.block.entity;

import com.kaironeybaloney.hadeandepths.HadeanDepths;
import com.kaironeybaloney.hadeandepths.block.ModBlocks;
import com.kaironeybaloney.hadeandepths.block.custom.ButcheringHookBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.Set;
import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, HadeanDepths.MODID);

    public static final Supplier<BlockEntityType<BlueNiteliteJarBlockEntity>> BLUE_NITELITE_JAR_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("blue_nitelite_jar_block_entity", () -> new BlockEntityType<>(
                    BlueNiteliteJarBlockEntity::new, ModBlocks.BLUE_NITELITE_JAR.get()));

    public static final Supplier<BlockEntityType<PinkNiteliteJarBlockEntity>> PINK_NITELITE_JAR_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("pink_nitelite_jar_block_entity", () -> new BlockEntityType<>(
                    PinkNiteliteJarBlockEntity::new, ModBlocks.PINK_NITELITE_JAR.get()));

    public static final Supplier<BlockEntityType<GreenNiteliteJarBlockEntity>> GREEN_NITELITE_JAR_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("green_nitelite_jar_block_entity", () -> new BlockEntityType<>(
                    GreenNiteliteJarBlockEntity::new, ModBlocks.GREEN_NITELITE_JAR.get()));

    public static final Supplier<BlockEntityType<DavyJonesLockerBlockEntity>> DAVY_JONES_LOCKER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("davy_jones_locker_block_entity", () -> new BlockEntityType<>(
                    DavyJonesLockerBlockEntity::new, ModBlocks.DAVY_JONES_LOCKER.get()));

    public static final Supplier<BlockEntityType<WoodenCrateBlockEntity>> WOODEN_CRATE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("wooden_crate_block_entity", () -> new BlockEntityType<>(
                    WoodenCrateBlockEntity::new, ModBlocks.WOODEN_CRATE.get()));

    public static final Supplier<BlockEntityType<ButcheringHookBlockEntity>> BUTCHERING_HOOK_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("butchering_hook_block_entity", () -> new BlockEntityType<>(
                    ButcheringHookBlockEntity::new, ModBlocks.BUTCHERING_HOOK.get()));

    public static final Supplier<BlockEntityType<CrucibleBlockEntity>> CRUCIBLE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("crucible_block_entity", () -> new BlockEntityType<>(
                    CrucibleBlockEntity::new, ModBlocks.CRUCIBLE.get()));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
