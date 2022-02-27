package net.chocomint.more_ores.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.Objects;

public class AlloyManufactoryRecipe implements Recipe<SimpleInventory> {

	private final Identifier id;
	private final ItemStack output;
	private final DefaultedList<Ingredient> recipeItems;
	private final int lavaAmount;

	public AlloyManufactoryRecipe(Identifier id, ItemStack output, DefaultedList<Ingredient> recipeItems, int lavaAmount) {
		this.id = id;
		this.output = output;
		this.recipeItems = recipeItems;
		this.lavaAmount = lavaAmount;
	}

	@Override
	public boolean matches(SimpleInventory inventory, World world) {
		return recipeItems.get(0).test(inventory.getStack(0))
			&& recipeItems.get(1).test(inventory.getStack(1));
	}

	@Override
	public ItemStack craft(SimpleInventory inventory) {
		return output;
	}

	@Override
	public boolean fits(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getOutput() {
		return output.copy();
	}

	@Override
	public Identifier getId() {
		return id;
	}

	public int getLavaAmount() {
		return lavaAmount;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return Serializer.INSTANCE;
	}

	@Override
	public RecipeType<?> getType() {
		return Type.INSTANCE;
	}

	public static class Type implements RecipeType<AlloyManufactoryRecipe> {
		private Type() { }
		public static final Type INSTANCE = new Type();
		public static final String ID = "alloy_manufacture";
	}

	public static class Serializer implements RecipeSerializer<AlloyManufactoryRecipe> {
		public static final Serializer INSTANCE = new Serializer();
		public static final String ID = "alloy_manufacture";
		// this is the name given in the json file

		@Override
		public AlloyManufactoryRecipe read(Identifier id, JsonObject json) {
			ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "result"));
			int lava = JsonHelper.getInt(json, "lava");

			JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
			DefaultedList<Ingredient> inputs = DefaultedList.ofSize(ingredients.size(), Ingredient.EMPTY);

			for (int i = 0; i < inputs.size(); i++) {
				inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
			}

			return new AlloyManufactoryRecipe(id, output, inputs, lava);
		}

		@Override
		public AlloyManufactoryRecipe read(Identifier id, PacketByteBuf buf) {
			DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);
			int lava = buf.readInt();

			for (int i = 0; i < inputs.size(); i++) {
				inputs.set(i, Ingredient.fromPacket(buf));
			}

			ItemStack output = buf.readItemStack();
			return new AlloyManufactoryRecipe(id, output, inputs, lava);
		}

		@Override
		public void write(PacketByteBuf buf, AlloyManufactoryRecipe recipe) {
			buf.writeInt(recipe.getIngredients().size());
			for (Ingredient ing : recipe.getIngredients()) {
				ing.write(buf);
			}
			buf.writeItemStack(recipe.getOutput());
		}
	}
}
