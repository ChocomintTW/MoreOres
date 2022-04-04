package net.chocomint.more_ores.block.multiBlock;

import net.minecraft.util.StringIdentifiable;

public enum ATMPart implements StringIdentifiable {
	BASE("base"),
	TOP("top");

	private final String name;

	ATMPart(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	@Override
	public String asString() {
		return this.name;
	}
}
