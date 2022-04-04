package net.chocomint.more_ores.util;

import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Vec3i;

public class Enums {

	private enum direction {
		top  (0, 1, 0 ),
		down (0, -1,0 ),
		east (1, 0, 0 ),
		west (-1,0, 0 ),
		north(0, 0, -1),
		south(0, 0, 1 );

		private final Vec3i relative_pos;

		direction(int x, int y, int z) {
			this.relative_pos = new Vec3i(x, y, z);
		}

		public Vec3i getRelativePos() {
			return relative_pos;
		}
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

	public enum CursorBehavior {
		onButton, none, clicked
	}
}
