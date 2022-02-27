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

public class LeadTools {

	public static void registerSilverTools() {
		System.out.println("Registering Silver Tools");
	}

	public static final Item LEAD_SWORD = ModItems.registerItem("lead_sword",
			new SwordItem(ModToolMaterials.LEAD, material.TOOL_LEAD_DETAIL.atkDamage[0], material.TOOL_LEAD_DETAIL.atkSpeed[0],
					new FabricItemSettings().group(ModItemGroup.MORE_ORES)));

	public static final Item LEAD_SHOVEL = ModItems.registerItem("lead_shovel",
			new ShovelItem(ModToolMaterials.LEAD, material.TOOL_LEAD_DETAIL.atkDamage[1], material.TOOL_LEAD_DETAIL.atkSpeed[1],
					new FabricItemSettings().group(ModItemGroup.MORE_ORES)));

	public static final Item LEAD_PICKAXE = ModItems.registerItem("lead_pickaxe",
			new ModPickaxeItem(ModToolMaterials.LEAD, material.TOOL_LEAD_DETAIL.atkDamage[2], material.TOOL_LEAD_DETAIL.atkSpeed[2],
					new FabricItemSettings().group(ModItemGroup.MORE_ORES)));

	public static final Item LEAD_AXE = ModItems.registerItem("lead_axe",
			new ModAxeItem(ModToolMaterials.LEAD, material.TOOL_LEAD_DETAIL.atkDamage[3], material.TOOL_LEAD_DETAIL.atkSpeed[3],
					new FabricItemSettings().group(ModItemGroup.MORE_ORES)));

	public static final Item LEAD_Hoe = ModItems.registerItem("lead_hoe",
			new ModHoeItem(ModToolMaterials.LEAD, material.TOOL_LEAD_DETAIL.atkDamage[4], material.TOOL_LEAD_DETAIL.atkSpeed[4],
					new FabricItemSettings().group(ModItemGroup.MORE_ORES)));
}
