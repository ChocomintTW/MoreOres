package net.chocomint.more_ores.recipe.dynamic;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class ModDynamicRecipes {

	public static Identifier toId(Item item) {
		String[] s = item.getTranslationKey().split("\\.");
		return new Identifier(s[1] + ":" + s[2]);
	}

	public static JsonObject createShapedRecipeJson(ArrayList<Character> keys, ArrayList<Identifier> items, ArrayList<String> type, ArrayList<String> pattern, Identifier output) {
		//Creating a new json object, where we will store our recipe.
		JsonObject json = new JsonObject();
		//The "type" of the recipe we are creating. In this case, a shaped recipe.
		json.addProperty("type", "minecraft:crafting_shaped");
		//This creates:
		//"type": "minecraft:crafting_shaped"

		//We create a new Json Element, and add our crafting pattern to it.
		JsonArray jsonArray = new JsonArray();
		jsonArray.add(pattern.get(0));
		jsonArray.add(pattern.get(1));
		jsonArray.add(pattern.get(2));
		//Then we add the pattern to our json object.
		json.add("pattern", jsonArray);
		//This creates:
		//"pattern": [
		//  "###",
		//  " | ",
		//  " | "
		//]

		//Next we need to define what the keys in the pattern are. For this we need different JsonObjects per key definition, and one main JsonObject that will contain all of the defined keys.
		JsonObject individualKey; //Individual key
		JsonObject keyList = new JsonObject(); //The main key object, containing all the keys

		for (int i = 0; i < keys.size(); ++i) {
			individualKey = new JsonObject();
			individualKey.addProperty(type.get(i), items.get(i).toString()); //This will create a key in the form "type": "input", where type is either "item" or "tag", and input is our input item.
			keyList.add(keys.get(i) + "", individualKey); //Then we add this key to the main key object.
			//This will add:
			//"#": { "tag": "c:copper_ingots" }
			//and after that
			//"|": { "item": "minecraft:sticks" }
			//and so on.
		}

		json.add("key", keyList);
		//And so we get:
		//"key": {
		//  "#": {
		//    "tag": "c:copper_ingots"
		//  },
		//  "|": {
		//    "item": "minecraft:stick"
		//  }
		//},

		//Finally, we define our result object
		JsonObject result = new JsonObject();
		result.addProperty("item", output.toString());
		result.addProperty("count", 1);
		json.add("result", result);
		//This creates:
		//"result": {
		//  "item": "modid:copper_pickaxe",
		//  "count": 1
		//}

		return json;
	}
}
