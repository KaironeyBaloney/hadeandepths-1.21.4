package com.kaironeybaloney.hadeandepths.screen;

import com.kaironeybaloney.hadeandepths.HadeanDepths;
import com.kaironeybaloney.hadeandepths.screen.custom.DavyJonesLockerMenu;
import com.kaironeybaloney.hadeandepths.screen.custom.WoodenCrateMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;


public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, HadeanDepths.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<DavyJonesLockerMenu>> DAVY_JONES_LOCKER_MENU =
            registerMenuType("davy_jones_locker_menu", DavyJonesLockerMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<WoodenCrateMenu>> WOODEN_CRATE_MENU =
            registerMenuType("wooden_crate_menu", WoodenCrateMenu::new);

    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name,
                                                                                                              IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
