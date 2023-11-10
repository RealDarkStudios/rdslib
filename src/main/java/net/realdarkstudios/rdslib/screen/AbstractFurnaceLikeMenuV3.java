package net.realdarkstudios.rdslib.screen;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;

public abstract class AbstractFurnaceLikeMenuV3 extends AbstractFurnaceMenu {
    public AbstractFurnaceLikeMenuV3(MenuType<?> pMenuType, RecipeType<? extends AbstractCookingRecipe> pRecipeType, RecipeBookType pRecipeBookType, int pContainerId, Inventory pPlayerInventory) {
        super(pMenuType, pRecipeType, pRecipeBookType, pContainerId, pPlayerInventory);
    }

    public AbstractFurnaceLikeMenuV3(MenuType<?> pMenuType, RecipeType<? extends AbstractCookingRecipe> pRecipeType, RecipeBookType pRecipeBookType, int pContainerId, Inventory pPlayerInventory, Container pContainer, ContainerData pContainerData) {
        super(pMenuType, pRecipeType, pRecipeBookType, pContainerId, pPlayerInventory, pContainer, pContainerData);
    }
}
