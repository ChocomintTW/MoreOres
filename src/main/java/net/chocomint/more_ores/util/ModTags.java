package net.chocomint.more_ores.util;

import net.chocomint.more_ores.More_Ores;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class ModTags {

	public static class Blocks {
		public static final Tag<Block> CONNECTABLE = createTag("connectable");

		private static Tag<Block> createTag(String name) {
			return TagFactory.BLOCK.create(new Identifier("c", name));
		}
	}

	public static class Items {
//		public static final Tag<Item> MOD_GLASS = createTag("mod_glass");

		private static Tag<Item> createTag(String name) {
			return TagFactory.ITEM.create(new Identifier("c", name));
		}
	}

}
