package net.chocomint.more_ores.entity;

import net.chocomint.more_ores.More_Ores;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntities {

	public static final EntityType<NetheriteGolemEntity> NETHERITE_GOLEM_ENTITY = Registry.register(Registry.ENTITY_TYPE,
			new Identifier(More_Ores.MOD_ID, "netherite_golem"),
				FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, NetheriteGolemEntity::new)
						.dimensions(EntityDimensions.fixed(1.4f, 2.7f)).build());;

	public static void registerModEntities() {
		FabricDefaultAttributeRegistry.register(NETHERITE_GOLEM_ENTITY, NetheriteGolemEntity.createMobAttributes());

		System.out.println("Registering Mod Entities for " + More_Ores.MOD_ID);
	}
}
