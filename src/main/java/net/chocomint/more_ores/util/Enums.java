package net.chocomint.more_ores.util;

import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Vec3i;

public class Enums {

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
