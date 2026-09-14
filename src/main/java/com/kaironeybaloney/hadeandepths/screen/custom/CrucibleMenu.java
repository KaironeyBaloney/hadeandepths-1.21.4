package com.kaironeybaloney.hadeandepths.screen.custom;

import com.kaironeybaloney.hadeandepths.block.entity.CrucibleBlockEntity;
import com.kaironeybaloney.hadeandepths.screen.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.crafting.RecipePropertySet;
import net.minecraft.world.item.crafting.RecipeType;

public class CrucibleMenu extends AbstractFurnaceMenu {

    public CrucibleMenu(int containerId, Inventory playerInventory, RegistryFriendlyByteBuf registryFriendlyByteBuf) {
        super(ModMenuTypes.CRUCIBLE_MENU.get(), RecipeType.SMELTING, RecipePropertySet.FURNACE_INPUT, RecipeBookType.FURNACE, containerId, playerInventory);
    }

    public CrucibleMenu(int containerId, Inventory playerInventory, Container furnaceContainer, ContainerData furnaceData) {
        super(ModMenuTypes.CRUCIBLE_MENU.get(), RecipeType.SMELTING, RecipePropertySet.FURNACE_INPUT, RecipeBookType.FURNACE, containerId, playerInventory, furnaceContainer, furnaceData);
    }
}
