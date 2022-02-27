package net.chocomint.more_ores.item;

import net.chocomint.more_ores.More_Ores;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
	public static final ItemGroup MORE_ORES = FabricItemGroupBuilder.build(new Identifier(More_Ores.MOD_ID, "more_ores"),
			() -> new ItemStack(ModItems.LEAD_INGOT));
}
