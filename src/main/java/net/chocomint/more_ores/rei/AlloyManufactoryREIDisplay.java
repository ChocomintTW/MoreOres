package net.chocomint.more_ores.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import net.chocomint.more_ores.recipe.AlloyManufactoryRecipe;

public class AlloyManufactoryREIDisplay extends ModRecipeDisplay<AlloyManufactoryRecipe> {
	public AlloyManufactoryREIDisplay(AlloyManufactoryRecipe recipe) {
		super(recipe);
	}

	public int getLava() {
		return recipe.getLavaAmount();
	}

	@Override
	public CategoryIdentifier<?> getCategoryIdentifier() {
		return ModREICategoryIdentifiers.ALLOY_MANUFACTORY;
	}
}
