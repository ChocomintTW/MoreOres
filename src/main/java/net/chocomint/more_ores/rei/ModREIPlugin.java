package net.chocomint.more_ores.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.chocomint.more_ores.block.ModBlocks;
import net.chocomint.more_ores.recipe.AlloyManufactoryRecipe;
import net.chocomint.more_ores.recipe.ModRecipes;

public class ModREIPlugin implements REIClientPlugin {

	@Override
	public void registerCategories(CategoryRegistry registry) {
		registry.add(
				new AlloyManufactoryREICategory()
		);

		registry.addWorkstations(ModREICategoryIdentifiers.ALLOY_MANUFACTORY, EntryStacks.of(ModBlocks.ALLOY_MANUFACTORY));

		registry.removePlusButton(ModREICategoryIdentifiers.ALLOY_MANUFACTORY);
	}

	@Override
	public void registerDisplays(DisplayRegistry registry) {
		registry.registerRecipeFiller(AlloyManufactoryRecipe.class,
				ModRecipes.ALLOY_MANUFACTORY_RECIPE_TYPE, AlloyManufactoryREIDisplay::new);
	}
}
