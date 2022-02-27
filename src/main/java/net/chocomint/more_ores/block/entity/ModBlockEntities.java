package net.chocomint.more_ores.block.entity;

import net.chocomint.more_ores.More_Ores;
import net.chocomint.more_ores.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {
	public static BlockEntityType<CraftBlockEntity> CRAFT_BLOCK_ENTITY =
			Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(More_Ores.MOD_ID, "craft_block"),
					FabricBlockEntityTypeBuilder.create(CraftBlockEntity::new, ModBlocks.CRAFT_BLOCK).build(null));
	public static BlockEntityType<AlloyManufactoryBlockEntity> ALLOY_MANUFACTORY_BLOCK_ENTITY =
			Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(More_Ores.MOD_ID, "alloy_manufactory_block_entity"),
					FabricBlockEntityTypeBuilder.create(AlloyManufactoryBlockEntity::new, ModBlocks.ALLOY_MANUFACTORY).build(null));
}
