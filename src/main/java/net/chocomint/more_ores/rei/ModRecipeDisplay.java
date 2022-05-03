package net.chocomint.more_ores.rei;

import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.recipe.Recipe;
import net.minecraft.util.Identifier;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class ModRecipeDisplay<T extends Recipe<SimpleInventory>> implements Display {
	protected final T recipe;
	protected List<EntryIngredient> inputs;
	protected EntryIngredient outputs;

	public ModRecipeDisplay(T recipe) {
		this.recipe = recipe;
		this.inputs = EntryIngredients.ofIngredients(recipe.getIngredients());
		this.outputs = EntryIngredients.of(recipe.getOutput());
	}

	@Override
	public @Nonnull
	List<EntryIngredient> getInputEntries() {
		return this.inputs;
	}

	@Override
	public @Nonnull List<EntryIngredient> getOutputEntries() {
		return Collections.singletonList(this.outputs);
	}

	@Override
	public @Nonnull
	Optional<Identifier> getDisplayLocation() {
		return Optional.ofNullable(this.recipe).map(T::getId);
	}
}
