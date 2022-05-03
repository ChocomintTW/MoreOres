package net.chocomint.more_ores.util.trade;

import net.chocomint.more_ores.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

public record Trade(VillagerProfession profession, int level, TradeOffer tradeOffer) {

	public static final Trade[] ModTrades = {
//			new Trade(VillagerProfession.WEAPONSMITH, 3, new TradeOffer(
//					new ItemStack(Items.EMERALD, 40),
//					new ItemStack(Items.ENDER_PEARL, 12),
//					new ItemStack(ModItems.TELEPORT_WAND),
//					1, 5, 0.3f
//			))
	};
}
