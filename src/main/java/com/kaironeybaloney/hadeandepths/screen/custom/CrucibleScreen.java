package com.kaironeybaloney.hadeandepths.screen.custom;

import com.kaironeybaloney.hadeandepths.HadeanDepths;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.SearchRecipeBookCategory;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeBookCategories;

import java.util.List;
public class CrucibleScreen extends AbstractFurnaceScreen<CrucibleMenu> {
    private static final ResourceLocation LIT_PROGRESS_SPRITE =
            ResourceLocation.fromNamespaceAndPath(HadeanDepths.MODID, "container/crucible/crucible_gui_flame");
    private static final ResourceLocation BURN_PROGRESS_SPRITE =
            ResourceLocation.fromNamespaceAndPath(HadeanDepths.MODID, "container/crucible/crucible_gui_arrow");
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(HadeanDepths.MODID, "textures/gui/crucible/crucible_gui.png");
    private static final Component FILTER_NAME = Component.translatable("gui.recipebook.toggleRecipes.smeltable");
    private static final List<RecipeBookComponent.TabInfo> TABS;

    public CrucibleScreen(CrucibleMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, FILTER_NAME, TEXTURE, LIT_PROGRESS_SPRITE, BURN_PROGRESS_SPRITE, TABS);
    }

    static {
        TABS = List.of(new RecipeBookComponent.TabInfo(SearchRecipeBookCategory.FURNACE), new RecipeBookComponent.TabInfo(Items.PORKCHOP, RecipeBookCategories.FURNACE_FOOD), new RecipeBookComponent.TabInfo(Items.STONE, RecipeBookCategories.FURNACE_BLOCKS), new RecipeBookComponent.TabInfo(Items.LAVA_BUCKET, Items.EMERALD, RecipeBookCategories.FURNACE_MISC));
    }
}
