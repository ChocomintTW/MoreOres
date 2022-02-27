package net.chocomint.more_ores.item.tools;

import net.chocomint.more_ores.item.ModItemGroup;
import net.chocomint.more_ores.item.ModItems;
import net.chocomint.more_ores.item.ModToolMaterials;
import net.chocomint.more_ores.item.custom.ModAxeItem;
import net.chocomint.more_ores.item.custom.ModHoeItem;
import net.chocomint.more_ores.item.custom.ModPickaxeItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;

public class SilverTools {

	public static void registerSilverTools() {
		System.out.println("Registering Silver Tools");
	}

	public static final Item SILVER_SWORD = ModItems.registerItem("silver_sword",
			new SwordItem(ModToolMaterials.SILVER,8, 1,
					new FabricItemSettings().group(ModItemGroup.MORE_ORES)));

	public static final Item SILVER_SHOVEL = ModItems.registerItem("silver_shovel",
			new ShovelItem(ModToolMaterials.SILVER,8, 1,
					new FabricItemSettings().group(ModItemGroup.MORE_ORES)));

	public static final Item SILVER_PICKAXE = ModItems.registerItem("silver_pickaxe",
			new ModPickaxeItem(ModToolMaterials.SILVER,8, 1,
					new FabricItemSettings().group(ModItemGroup.MORE_ORES)));

	public static final Item SILVER_AXE = ModItems.registerItem("silver_axe",
			new ModAxeItem(ModToolMaterials.SILVER,8, 1,
					new FabricItemSettings().group(ModItemGroup.MORE_ORES)));

	public static final Item SILVER_Hoe = ModItems.registerItem("silver_hoe",
			new ModHoeItem(ModToolMaterials.SILVER,8, 1,
					new FabricItemSettings().group(ModItemGroup.MORE_ORES)));
}
