package net.chocomint.more_ores.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CreditCardItem extends Item {
	public CreditCardItem(Settings settings) {
		super(settings);
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		CardRank card = CardRank.values()[getCardRankOrder(stack)];
		String owner = stack.getOrCreateNbt().getString("ownerId");
		if(!owner.equals("")) {
			tooltip.add(new LiteralText(stack.getOrCreateNbt().getString("ownerId") + " [")
					.append(card.getText().append(new LiteralText("]").formatted(Formatting.WHITE))));
		}
		else {
			tooltip.add(new TranslatableText("tooltip.credit_card.unregistered").formatted(Formatting.ITALIC, Formatting.RED));
		}
	}

	public static int getCardRankOrder(ItemStack stack) {
		return stack.getOrCreateNbt().getInt("rank");
	}

	public static int getCoin(ItemStack stack) {
		return stack.getOrCreateNbt().getInt("coin");
	}

	public enum CardRank {
		STANDARD("standard", Formatting.GREEN),
		GOLD("gold", Formatting.GOLD),
		PLATINUM("platinum", Formatting.YELLOW),
		DIAMOND("diamond", Formatting.AQUA);

		private final String translateKey;
		private final Formatting[] formats;

		CardRank(String key, Formatting... format) {
			this.translateKey = key;
			this.formats = format;
		}

		public String getTranslateKey() {
			return translateKey;
		}

		public Formatting[] getFormats() {
			return formats;
		}

		public MutableText getText() {
			return new TranslatableText("credit_card.rank." + this.translateKey).formatted(this.formats);
		}
	}
}
