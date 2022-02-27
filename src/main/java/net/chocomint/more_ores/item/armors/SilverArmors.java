package net.chocomint.more_ores.item.armors;

import net.chocomint.more_ores.item.ModArmorMaterials;
import net.chocomint.more_ores.item.ModItemGroup;
import net.chocomint.more_ores.item.ModItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public class SilverArmors {

	public static void registerSilverArmors() {
		System.out.println("Registering Silver Tools");
	}

	public static final Item SILVER_HELMET = ModItems.registerItem("silver_helmet",
			new ArmorItem(ModArmorMaterials.SILVER, EquipmentSlot.HEAD,
					new FabricItemSettings().group(ModItemGroup.MORE_ORES)));

	public static final Item SILVER_CHESTPLATE = ModItems.registerItem("silver_chestplate",
			new ArmorItem(ModArmorMaterials.SILVER, EquipmentSlot.CHEST,
					new FabricItemSettings().group(ModItemGroup.MORE_ORES)));

	public static final Item SILVER_LEGGINGS = ModItems.registerItem("silver_leggings",
			new ArmorItem(ModArmorMaterials.SILVER, EquipmentSlot.LEGS,
					new FabricItemSettings().group(ModItemGroup.MORE_ORES)));

	public static final Item SILVER_BOOTS = ModItems.registerItem("silver_boots",
			new ArmorItem(ModArmorMaterials.SILVER, EquipmentSlot.FEET,
					new FabricItemSettings().group(ModItemGroup.MORE_ORES)));
}
