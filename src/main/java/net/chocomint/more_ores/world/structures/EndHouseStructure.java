package net.chocomint.more_ores.world.structures;

import net.minecraft.structure.*;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.Optional;

public class EndHouseStructure extends StructureFeature<StructurePoolFeatureConfig> {

	public EndHouseStructure() {
		super(StructurePoolFeatureConfig.CODEC, EndHouseStructure::createPiecesGenerator, PostPlacementProcessor.EMPTY);
	}

	private static boolean isFeatureChunk(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {
		// Grabs the chunk position we are at
		ChunkPos chunkpos = context.chunkPos();

		// Checks to make sure our structure does not spawn within 10 chunks of an Ocean Monument
		// to demonstrate how this method is good for checking extra conditions for spawning
		return !context.chunkGenerator().method_41053(StructureSetKeys.VILLAGES, context.seed(), chunkpos.x, chunkpos.z, 10);
	}

	public static Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> createPiecesGenerator(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {
		if (!EndHouseStructure.isFeatureChunk(context))
			return Optional.empty();

		BlockPos blockpos = context.chunkPos().getCenterAtY(0);
		int topLandY = context.chunkGenerator().getHeightOnGround(blockpos.getX(), blockpos.getZ(), Heightmap.Type.WORLD_SURFACE_WG, context.world());

		Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> structurePiecesGenerator = StructurePoolBasedGenerator.generate(
				context,
				PoolStructurePiece::new,
				blockpos,
				false,
				true
		);

		if (structurePiecesGenerator.isPresent()) {
			System.out.println("Mod Structure");
		}
		return structurePiecesGenerator;
	}
}
