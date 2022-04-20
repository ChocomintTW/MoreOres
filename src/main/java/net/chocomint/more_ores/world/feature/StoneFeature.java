package net.chocomint.more_ores.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class StoneFeature extends Feature<DefaultFeatureConfig> {
	public StoneFeature(Codec<DefaultFeatureConfig> configCodec) {
		super(configCodec);
	}

	@Override
	public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
		StructureWorldAccess world = context.getWorld();
		Random random = context.getRandom();
		BlockPos pos = context.getOrigin();

		float f = (float) random.nextInt(3) + 4.0F;

		// i: height; f: circle size
		for (int i = 0; f > 1.5F; i++) {
			for(int j = MathHelper.floor(-f); j <= MathHelper.ceil(f); ++j) {
				for(int k = MathHelper.floor(-f); k <= MathHelper.ceil(f); ++k) {

					Block random_block = getBlockFromOrder(random.nextInt(0, 4));

					if ((float)(j * j + k * k) <= Math.pow(f + 1.0F, 2) - random.nextInt(2))
						this.setBlockState(world, pos.add(j, i, k), random_block.getDefaultState());
				}
			}
			f -= (float) random.nextInt(2) + 0.5F;
		}

		return false;
	}

	public Block getBlockFromOrder(int order) {
		return switch (order) {
			case 0 -> Blocks.COBBLESTONE;
			case 1 -> Blocks.STONE_BRICKS;
			case 2 -> Blocks.MOSSY_STONE_BRICKS;
			case 3 -> Blocks.CRACKED_STONE_BRICKS;
			case 4 -> Blocks.STONE;
			default -> Blocks.AIR;
		};
	}
}
