package net.chocomint.more_ores.item;

import net.chocomint.more_ores.More_Ores;
import net.chocomint.more_ores.fluid.ModFluids;
import net.chocomint.more_ores.item.armors.SilverArmors;
import net.chocomint.more_ores.item.custom.*;
import net.chocomint.more_ores.sound.ModSounds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
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

	// Items
	public static final Item ADVANCED_COAL = registerItem("advanced_coal",
			new Item(new FabricItemSettings().group(ModItemGroup.MORE_ORES)));
	public static final Item COMPRESSED_SUGAR = registerItem("compressed_sugar",
			new Item(new FabricItemSettings().group(ModItemGroup.MORE_ORES)));
	public static final Item PAOLYTA_B = registerItem("paolyta_b",
			new PaolytaBItem(new FabricItemSettings().maxCount(1).group(ModItemGroup.MORE_ORES)));
	public static final Item RADIOACTIVE_WATER_BUCKET = registerItem("radioactive_water_bucket",
			new BucketItem(ModFluids.RADIOACTIVE_WATER_STILL, new FabricItemSettings().maxCount(1).group(ModItemGroup.MORE_ORES)));

	// Tools
	public static final Item REMOTE_CONTROL = registerItem("remote_control",
			new RemoteControlItem(new FabricItemSettings().group(ModItemGroup.MORE_ORES)));
//	public static final Item GRAVITY_WAND = registerItem("gravity_wand",
//			new GravityWandItem(new FabricItemSettings().group(ModItemGroup.MORE_ORES)));

	public static final Item ENDERMAN_SCRAP = registerItem("enderman_scrap",
			new Item(new FabricItemSettings().maxCount(16).group(ModItemGroup.MORE_ORES)));
	public static final Item ENDERMAN_CORE = registerItem("enderman_core",
			new Item(new FabricItemSettings().maxCount(16).group(ModItemGroup.MORE_ORES)));
	public static final Item TELEPORT_CORE = registerItem("teleport_core",
			new Item(new FabricItemSettings().maxCount(16).rarity(Rarity.UNCOMMON).fireproof().group(ModItemGroup.MORE_ORES)));
	public static final Item TELEPORT_WAND = registerItem("teleport_wand",
			new TeleportWandItem(new FabricItemSettings().rarity(Rarity.RARE).fireproof().group(ModItemGroup.MORE_ORES)));

	/* Rseyjinecookxem
	   Tswastandji
	   Ldivesloupe
	   Afusternbavo */

	// Ore
	public static final Item LEAD_INGOT = registerItem("lead_ingot",
			new Item(new FabricItemSettings().group(ModItemGroup.MORE_ORES)));
	public static final Item TITANIUM_INGOT = registerItem("titanium_ingot",
			new Item(new FabricItemSettings().group(ModItemGroup.MORE_ORES)));
	public static final Item SILVER_INGOT = registerItem("silver_ingot",
			new Item(new FabricItemSettings().group(ModItemGroup.MORE_ORES)));

	public static final Item CHARGED_QUARTZ = registerItem("charged_quartz",
			new Item(new FabricItemSettings().group(ModItemGroup.MORE_ORES)));

	public static final Item RAW_VIBRANIUM_NUGGET = registerItem("raw_vibranium_nugget",
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
