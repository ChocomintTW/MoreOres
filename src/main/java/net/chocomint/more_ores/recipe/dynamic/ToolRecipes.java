package net.chocomint.more_ores.recipe.dynamic;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;

import static net.chocomint.more_ores.recipe.dynamic.ModDynamicRecipes.*;

public class ToolRecipes {

	public static JsonObject[] createToolJson(Item ingot, Item stick, Item[] toolList) {
		return new JsonObject[] {
				createSwordJson(ingot, stick, toolList[0]),
				createShovelJson(ingot, stick, toolList[1]),
				createPickaxeJson(ingot, stick, toolList[2]),
				createAxeJson(ingot, stick, toolList[3]),
				createHoeJson(ingot, stick, toolList[4])
		};
	}

	public static JsonObject createSwordJson(Item ingot, Item stick, Item sword) {
		return createShapedRecipeJson(
				Lists.newArrayList('#', '|'),
				Lists.newArrayList(toId(ingot), toId(stick)),
				Lists.newArrayList("item", "item"),
				Lists.newArrayList(
						"#",
						"#",
						"|"
				), toId(sword)
		);
	}
	public static JsonObject createShovelJson(Item ingot, Item stick, Item shovel) {
		return createShapedRecipeJson(
				Lists.newArrayList('#', '|'),
				Lists.newArrayList(toId(ingot), toId(stick)),
				Lists.newArrayList("item", "item"),
				Lists.newArrayList(
						"#",
						"|",
						"|"
				), toId(shovel)
		);
	}
	public static JsonObject createPickaxeJson(Item ingot, Item stick, Item pickaxe) {
		return createShapedRecipeJson(
				Lists.newArrayList('#', '|'),
				Lists.newArrayList(toId(ingot), toId(stick)),
				Lists.newArrayList("item", "item"),
				Lists.newArrayList(
						"###",
						" | ",
						" | "
				), toId(pickaxe)
		);
	}
	public static JsonObject createAxeJson(Item ingot, Item stick, Item axe) {
		return createShapedRecipeJson(
				Lists.newArrayList('#', '|'),
				Lists.newArrayList(toId(ingot), toId(stick)),
				Lists.newArrayList("item", "item"),
				Lists.newArrayList(
						"##",
						"#|",
						" |"
				), toId(axe)
		);
	}
	public static JsonObject createHoeJson(Item ingot, Item stick, Item hoe) {
		return createShapedRecipeJson(
				Lists.newArrayList('#', '|'),
				Lists.newArrayList(toId(ingot), toId(stick)),
				Lists.newArrayList("item", "item"),
				Lists.newArrayList(
						"##",
						" |",
						" |"
				), toId(hoe)
		);
	}
}
