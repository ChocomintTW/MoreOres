package net.chocomint.more_ores.item;

import com.google.gson.JsonObject;
import net.chocomint.more_ores.item.ModItemGroup;
import net.chocomint.more_ores.item.ModItems;
import net.chocomint.more_ores.item.ModToolMaterials;
import net.chocomint.more_ores.item.custom.ModAxeItem;
import net.chocomint.more_ores.item.custom.ModHoeItem;
import net.chocomint.more_ores.item.custom.ModPickaxeItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;

import static net.chocomint.more_ores.recipe.dynamic.ToolRecipes.createToolJson;
import static net.chocomint.more_ores.item.material.*;

public class ModTools {

	public static final JsonObject[] TITANIUM_TOOL_RECIPE = createToolJson(ModItems.TITANIUM_INGOT, Items.STICK,
			getItemArray("titanium", ModToolMaterials.TITANIUM, TOOL_TITANIUM_DETAIL));
	public static final JsonObject[] SILVER_TOOL_RECIPE = createToolJson(ModItems.SILVER_INGOT, Items.STICK,
			getItemArray("silver", ModToolMaterials.SILVER, TOOL_SILVER_DETAIL));


	private static Item[] getItemArray(String name, ToolMaterial material, ToolDetail detail) {
		return new Item[]{
			ModItems.registerItem(name + "_sword", new SwordItem(material, detail.atkDamage[0], detail.atkSpeed[0],
					new FabricItemSettings().group(ModItemGroup.MORE_ORES))),
			ModItems.registerItem(name + "_shovel", new ShovelItem(material, detail.atkDamage[1], detail.atkSpeed[1],
					new FabricItemSettings().group(ModItemGroup.MORE_ORES))),
			ModItems.registerItem(name + "_pickaxe", new ModPickaxeItem(material, detail.atkDamage[2], detail.atkSpeed[2],
					new FabricItemSettings().group(ModItemGroup.MORE_ORES))),
			ModItems.registerItem(name + "_axe", new ModAxeItem(material, detail.atkDamage[3], detail.atkSpeed[3],
					new FabricItemSettings().group(ModItemGroup.MORE_ORES))),
			ModItems.registerItem(name + "_hoe", new ModHoeItem(material, detail.atkDamage[4], detail.atkSpeed[4],
					new FabricItemSettings().group(ModItemGroup.MORE_ORES)))
		};
	}

	public static void registerModTools() {
		System.out.println("Registering Silver Tools");
	}
}
