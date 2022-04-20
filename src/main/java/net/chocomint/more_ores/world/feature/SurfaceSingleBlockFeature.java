package net.chocomint.more_ores.world.feature;

import com.mojang.serialization.Codec;
import net.chocomint.more_ores.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class SurfaceSingleBlockFeature extends Feature<DefaultFeatureConfig> {
	private final Block block;

	public SurfaceSingleBlockFeature(Codec<DefaultFeatureConfig> configCodec, Block b) {
		super(configCodec);
		this.block = b;
	}

	@Override
	public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
		StructureWorldAccess world = context.getWorld();
		BlockPos pos = context.getOrigin();
		this.setBlockState(world, pos, block.getDefaultState());
		return false;
	}
}
