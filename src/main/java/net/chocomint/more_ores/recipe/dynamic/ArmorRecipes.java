package net.chocomint.more_ores.recipe.dynamic;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;

import static net.chocomint.more_ores.recipe.dynamic.ModDynamicRecipes.createShapedRecipeJson;
import static net.chocomint.more_ores.recipe.dynamic.ModDynamicRecipes.toId;

public class ArmorRecipes {

	public static JsonObject[] createArmorJson(Item ingot, Item[] ArmorList) {
		return new JsonObject[] {
				createHelmetJson(ingot, ArmorList[0]),
				createChestplateJson(ingot, ArmorList[1]),
				createLeggingsJson(ingot, ArmorList[2]),
				createBootsJson(ingot, ArmorList[3])
		};
	}

	public static JsonObject createHelmetJson(Item ingot, Item sword) {
		return createShapedRecipeJson(
				Lists.newArrayList('#'),
				Lists.newArrayList(toId(ingot)),
				Lists.newArrayList("item"),
				Lists.newArrayList(
						"###",
						"# #"
				), toId(sword)
		);
	}
	public static JsonObject createChestplateJson(Item ingot, Item shovel) {
		return createShapedRecipeJson(
				Lists.newArrayList('#'),
				Lists.newArrayList(toId(ingot)),
				Lists.newArrayList("item"),
				Lists.newArrayList(
						"# #",
						"###",
						"###"
				), toId(shovel)
		);
	}
	public static JsonObject createLeggingsJson(Item ingot, Item pickaxe) {
		return createShapedRecipeJson(
				Lists.newArrayList('#'),
				Lists.newArrayList(toId(ingot)),
				Lists.newArrayList("item"),
				Lists.newArrayList(
						"###",
						"# #",
						"# #"
				), toId(pickaxe)
		);
	}
	public static JsonObject createBootsJson(Item ingot, Item axe) {
		return createShapedRecipeJson(
				Lists.newArrayList('#'),
				Lists.newArrayList(toId(ingot)),
				Lists.newArrayList("item"),
				Lists.newArrayList(
						"# #",
						"# #"
				), toId(axe)
		);
	}
}
