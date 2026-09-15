package com.kaironeybaloney.hadeandepths.screen.custom;

import com.kaironeybaloney.hadeandepths.HadeanDepths;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CrucibleScreen extends AbstractFurnaceScreen<CrucibleMenu> {
    private static final ResourceLocation LIT_PROGRESS_SPRITE =
            ResourceLocation.fromNamespaceAndPath(HadeanDepths.MODID, "container/crucible/crucible_gui_flame");
    private static final ResourceLocation BURN_PROGRESS_SPRITE =
            ResourceLocation.fromNamespaceAndPath(HadeanDepths.MODID, "container/crucible/crucible_gui_arrow");
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(HadeanDepths.MODID, "textures/gui/crucible/crucible_gui.png");

    public CrucibleScreen(CrucibleMenu menu, Inventory playerInventory, Component title) {
        super(menu, new SmeltingRecipeBookComponent(), playerInventory, title, TEXTURE, LIT_PROGRESS_SPRITE, BURN_PROGRESS_SPRITE);
    }
}