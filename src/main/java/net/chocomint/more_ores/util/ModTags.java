package net.chocomint.more_ores.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModTags {

	public static class Blocks {
		public static final TagKey<Block> CONNECTABLE = createTag("connectable");

		private static TagKey<Block> createTag(String name) {
			return TagKey.of(Registry.BLOCK_KEY, new Identifier("c", name));
		}
	}

	public static class Items {

		private static TagKey<Item> createTag(String name) {
			return TagKey.of(Registry.ITEM_KEY, new Identifier("c", name));
		}
	}

}
