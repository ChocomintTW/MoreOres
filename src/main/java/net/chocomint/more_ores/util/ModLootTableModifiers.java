package net.chocomint.more_ores.util;

import net.chocomint.more_ores.item.ModItems;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

public class ModLootTableModifiers {

	private static final Identifier ENDERMAN_ID = new Identifier("minecraft", "entities/enderman");

	public static void modifyLootTables() {
		LootTableLoadingCallback.EVENT.register(((resourceManager, manager, id, supplier, setter) -> {
			if (ENDERMAN_ID.equals(id)) {
				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
						.with(ItemEntry.builder(ModItems.ENDERMAN_SCRAP))
						.rolls(ConstantLootNumberProvider.create(1))
						.conditionally(RandomChanceWithLootingLootCondition.builder(0.1f, 0.05f))
						.conditionally(KilledByPlayerLootCondition.builder());
				supplier.withPool(poolBuilder.build());
			}
		}));
	}
}
