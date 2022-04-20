package net.chocomint.more_ores.block.entity;

import net.chocomint.more_ores.More_Ores;
import net.chocomint.more_ores.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {
	// Machine
	public static BlockEntityType<AlloyManufactoryBlockEntity> ALLOY_MANUFACTORY_BLOCK_ENTITY;
	public static BlockEntityType<FillerBlockEntity> FILLER_BLOCK_ENTITY;
	public static BlockEntityType<ATMBlockEntity> ATM_BLOCK_ENTITY;

	// Another
	public static BlockEntityType<ElectricTubeBlockEntity> ELECTRIC_TUBE_BLOCK_ENTITY;

	public static void registerAllBlockEntities() {
		ALLOY_MANUFACTORY_BLOCK_ENTITY =
				Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(More_Ores.MOD_ID, "alloy_manufactory_block_entity"),
						FabricBlockEntityTypeBuilder.create(AlloyManufactoryBlockEntity::new, ModBlocks.ALLOY_MANUFACTORY).build(null));
		FILLER_BLOCK_ENTITY =
				Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(More_Ores.MOD_ID, "filler_block_entity"),
						FabricBlockEntityTypeBuilder.create(FillerBlockEntity::new, ModBlocks.FILLER).build(null));
		ATM_BLOCK_ENTITY =
				Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(More_Ores.MOD_ID, "atm_block_entity"),
						FabricBlockEntityTypeBuilder.create(ATMBlockEntity::new, ModBlocks.ATM).build(null));
		ELECTRIC_TUBE_BLOCK_ENTITY =
				Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(More_Ores.MOD_ID, "electric_tube_block_entity"),
						FabricBlockEntityTypeBuilder.create(ElectricTubeBlockEntity::new, ModBlocks.ELECTRIC_TUBE).build(null));
	}
}
