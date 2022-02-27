package net.chocomint.more_ores.item.tools;

import net.chocomint.more_ores.item.ModItemGroup;
import net.chocomint.more_ores.item.ModItems;
import net.chocomint.more_ores.item.ModToolMaterials;
import net.chocomint.more_ores.item.custom.ModAxeItem;
import net.chocomint.more_ores.item.custom.ModHoeItem;
import net.chocomint.more_ores.item.custom.ModPickaxeItem;
import net.chocomint.more_ores.item.material;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;

public class TitaniumTools {

	public static void registerTitaniumTools() { System.out.println("Registering Titanium Tools"); }

	public static final Item TITANIUM_SWORD = ModItems.registerItem("titanium_sword",
			new SwordItem(ModToolMaterials.TITANIUM, material.TOOL_TITANIUM_DETAIL.atkDamage[0], material.TOOL_TITANIUM_DETAIL.atkSpeed[0],
					new FabricItemSettings().group(ModItemGroup.MORE_ORES)));

	public static final Item TITANIUM_SHOVEL = ModItems.registerItem("titanium_shovel",
			new ShovelItem(ModToolMaterials.TITANIUM, material.TOOL_TITANIUM_DETAIL.atkDamage[1], material.TOOL_TITANIUM_DETAIL.atkSpeed[1],
					new FabricItemSettings().group(ModItemGroup.MORE_ORES)));

	public static final Item TITANIUM_PICKAXE = ModItems.registerItem("titanium_pickaxe",
			new ModPickaxeItem(ModToolMaterials.TITANIUM, material.TOOL_TITANIUM_DETAIL.atkDamage[2], material.TOOL_TITANIUM_DETAIL.atkSpeed[2],
					new FabricItemSettings().group(ModItemGroup.MORE_ORES)));

	public static final Item TITANIUM_AXE = ModItems.registerItem("titanium_axe",
			new ModAxeItem(ModToolMaterials.TITANIUM, material.TOOL_TITANIUM_DETAIL.atkDamage[3], material.TOOL_TITANIUM_DETAIL.atkSpeed[3],
					new FabricItemSettings().group(ModItemGroup.MORE_ORES)));

	public static final Item TITANIUM_Hoe = ModItems.registerItem("titanium_hoe",
			new ModHoeItem(ModToolMaterials.TITANIUM, material.TOOL_TITANIUM_DETAIL.atkDamage[4], material.TOOL_TITANIUM_DETAIL.atkSpeed[4],
					new FabricItemSettings().group(ModItemGroup.MORE_ORES)));
}
