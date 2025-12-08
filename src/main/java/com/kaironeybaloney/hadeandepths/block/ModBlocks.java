package com.kaironeybaloney.hadeandepths.block;

import com.kaironeybaloney.hadeandepths.HadeanDepths;
import com.kaironeybaloney.hadeandepths.block.custom.*;
import com.kaironeybaloney.hadeandepths.item.ModItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(HadeanDepths.MODID);

    public static final DeferredBlock<Block> BLUE_NITELITE_JAR = registerBlock("blue_nitelite_jar",
            properties -> new BlueNiteliteJarBlock(properties), BlockBehaviour.Properties.of()
                    .strength(0.3f, 0.3f)
                    .sound(SoundType.GLASS)
                    .noOcclusion()
                    .dynamicShape()
                    .lightLevel(state -> 10));
    public static final DeferredBlock<Block> GREEN_NITELITE_JAR = registerBlock("green_nitelite_jar",
            properties -> new GreenNiteliteJarBlock(properties), BlockBehaviour.Properties.of()
                    .strength(0.3f, 0.3f)
                    .sound(SoundType.GLASS)
                    .noOcclusion()
                    .lightLevel(state -> 10));
    public static final DeferredBlock<Block> PINK_NITELITE_JAR = registerBlock("pink_nitelite_jar",
            properties -> new PinkNiteliteJarBlock(properties), BlockBehaviour.Properties.of()
                    .strength(0.3f, 0.3f)
                    .sound(SoundType.GLASS)
                    .noOcclusion()
                    .lightLevel(state -> 10));
    public static final DeferredBlock<Block> DAVY_JONES_LOCKER = registerBlock("davy_jones_locker",
            properties -> new DavyJonesLockerBlock(properties), BlockBehaviour.Properties.of()
                    .strength(2f, 2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)
                    .noOcclusion());
    public static final DeferredBlock<Block> WOODEN_CRATE = registerBlock("wooden_crate",
            properties -> new WoodenCrateBlock(properties), BlockBehaviour.Properties.of()
                    .strength(2f, 2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.WOOD)
                    .noOcclusion());
    public static final DeferredBlock<Block> BUTCHERING_HOOK = registerBlock("butchering_hook",
            properties -> new ButcheringHookBlock(properties), BlockBehaviour.Properties.of()
                    .strength(2f, 2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)
                    .noOcclusion());

    public static final DeferredBlock<Block> GLOWY_GOOP_BLOCK = registerBlock("glowy_goop_block",
            properties -> new Block(properties), BlockBehaviour.Properties.of()
                    .strength(0f, 0f)
                    .sound(SoundType.HONEY_BLOCK)
                    .noOcclusion()
                    .lightLevel(state -> 15));




    private static <B extends Block> DeferredBlock<B> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends B> blockFactory, BlockBehaviour.Properties blockProperties) {
        DeferredBlock<B> block = BLOCKS.registerBlock(name, blockFactory, blockProperties);
        registerBlockItem(name, block);
        return block;
    }

    private static <B extends Block> void registerBlockItem(String name, DeferredBlock<B> block) {
        ModItems.ITEMS.registerSimpleBlockItem(name, block);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
