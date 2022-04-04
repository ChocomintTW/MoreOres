package net.chocomint.more_ores.item;

import net.chocomint.more_ores.More_Ores;
import net.chocomint.more_ores.item.armors.SilverArmors;
import net.chocomint.more_ores.item.custom.ContainerItem;
import net.chocomint.more_ores.item.custom.CreditCardItem;
import net.chocomint.more_ores.item.custom.ModMusicDiscItem;
import net.chocomint.more_ores.item.custom.TeleportWandItem;
import net.chocomint.more_ores.sound.ModSounds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ModItems {

	// General Items

	public static final Item LAVA_TANK = registerItem("lava_tank",
			new ContainerItem(new FabricItemSettings().group(ModItemGroup.MORE_ORES), 20000));
	public static final Item CREDIT_CARD = registerItem("credit_card",
			new CreditCardItem(new FabricItemSettings().group(ModItemGroup.MORE_ORES).maxCount(1).fireproof()));
	public static final Item LEMON_MUSIC_DISC = registerItem("lemon_music_disc",
			new ModMusicDiscItem(7, ModSounds.LEMON,
					new FabricItemSettings().group(ModItemGroup.MORE_ORES).rarity(Rarity.RARE).maxCount(1)));
	public static final Item ADVANCED_COAL = registerItem("advanced_coal",
			new Item(new FabricItemSettings().group(ModItemGroup.MORE_ORES)));
	public static final Item TELEPORT_WAND = registerItem("teleport_wand",
			new TeleportWandItem(new FabricItemSettings().group(ModItemGroup.MORE_ORES)));

	/* Rseyjinecookxem
	   Tswastandji
	   Ldivesloupe
	   Afusternbavo */

	// Ore ingots
	public static final Item LEAD_INGOT = registerItem("lead_ingot",
			new Item(new FabricItemSettings().group(ModItemGroup.MORE_ORES)));
	public static final Item TITANIUM_INGOT = registerItem("titanium_ingot",
			new Item(new FabricItemSettings().group(ModItemGroup.MORE_ORES)));
	public static final Item SILVER_INGOT = registerItem("silver_ingot",
			new Item(new FabricItemSettings().group(ModItemGroup.MORE_ORES)));
	public static final Item CHARGED_QUARTZ = registerItem("charged_quartz",
			new Item(new FabricItemSettings().group(ModItemGroup.MORE_ORES)));

	// Alloys
	public static final Item STEEL_INGOT = registerItem("steel_ingot",
			new Item(new FabricItemSettings().group(ModItemGroup.MORE_ORES)));
	public static final Item REINFORCED_CHARGED_QUARTZ = registerItem("reinforced_charged_quartz",
			new Item(new FabricItemSettings().group(ModItemGroup.MORE_ORES)));
	public static final Item PIERRESITVIA_ALLOY = registerItem("pierresitvia_alloy",
			new Item(new FabricItemSettings().group(ModItemGroup.MORE_ORES).rarity(Rarity.RARE).fireproof()));
	public static final Item REINFORCED_BLAZE_ROD = registerItem("reinforced_blaze_rod",
			new Item(new FabricItemSettings().group(ModItemGroup.MORE_ORES).fireproof()));
	public static final Item GLOW_BLAZE_ALLOY = registerItem("glow_blaze_alloy",
			new Item(new FabricItemSettings().group(ModItemGroup.MORE_ORES).fireproof()));


	public static Item registerItem(String name, Item item) {
		return Registry.register(Registry.ITEM, new Identifier(More_Ores.MOD_ID, name), item);
	}

	public static void registerModItems() {
		System.out.println("Registering Mod Items for " + More_Ores.MOD_ID);

		// Tools
		ModTools.registerModTools();

		// Armors
		SilverArmors.registerSilverArmors();
	}
}
