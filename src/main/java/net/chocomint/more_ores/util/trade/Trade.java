package net.chocomint.more_ores.util.trade;

import net.chocomint.more_ores.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

public record Trade(VillagerProfession profession, int level, TradeOffer tradeOffer) {

	public static final Trade[] ModTrades = {
			new Trade(VillagerProfession.TOOLSMITH, 1, new TradeOffer(
					new ItemStack(Items.EMERALD, 50),
					new ItemStack(ModItems.LEMON_MUSIC_DISC, 1),
					5, 5, 0.08f))
	};
}
