package net.chocomint.more_ores.item;

import net.chocomint.more_ores.More_Ores;
import net.chocomint.more_ores.item.armors.SilverArmors;
import net.chocomint.more_ores.item.tools.SilverTools;
import net.chocomint.more_ores.item.tools.TitaniumTools;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

	// General Items

	public static final Item ADVANCED_COAL = registerItem("advanced_coal",
			new Item(new FabricItemSettings().group(ModItemGroup.MORE_ORES)));

	// Alloys
	public static final Item STEEL_INGOT = registerItem("steel_ingot",
			new Item(new FabricItemSettings().group(ModItemGroup.MORE_ORES)));
	public static final Item PIERRESITVIA_ALLOY = registerItem("pierresitvia_alloy",
			new Item(new FabricItemSettings().group(ModItemGroup.MORE_ORES)));
	public static final Item REINFORCED_CHARGED_QUARTZ = registerItem("reinforced_charged_quartz",
			new Item(new FabricItemSettings().group(ModItemGroup.MORE_ORES)));

	// Ore ingots
	public static final Item LEAD_INGOT = registerItem("lead_ingot",
			new Item(new FabricItemSettings().group(ModItemGroup.MORE_ORES)));
	public static final Item TITANIUM_INGOT = registerItem("titanium_ingot",
			new Item(new FabricItemSettings().group(ModItemGroup.MORE_ORES)));
	public static final Item SILVER_INGOT = registerItem("silver_ingot",
			new Item(new FabricItemSettings().group(ModItemGroup.MORE_ORES)));
	public static final Item VOID_GEM = registerItem("void_gem",
			new Item(new FabricItemSettings().group(ModItemGroup.MORE_ORES)));

	public static final Item CHARGED_QUARTZ = registerItem("charged_quartz",
			new Item(new FabricItemSettings().group(ModItemGroup.MORE_ORES)));


	public static Item registerItem(String name, Item item) {
		return Registry.register(Registry.ITEM, new Identifier(More_Ores.MOD_ID, name), item);
	}

	public static void registerModItems() {
		System.out.println("Registering Mod Items for " + More_Ores.MOD_ID);

		// Tools
		//TitaniumTools.registerTitaniumTools();
		SilverTools.registerSilverTools();

		// Armors
		SilverArmors.registerSilverArmors();
	}

}
