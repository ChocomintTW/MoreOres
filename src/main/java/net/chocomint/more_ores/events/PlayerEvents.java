package net.chocomint.more_ores.events;

import net.chocomint.more_ores.util.IEntityDataSaver;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerEvents implements ServerPlayerEvents.CopyFrom {
	@Override
	public void copyFromPlayer(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
		IEntityDataSaver original = (IEntityDataSaver) oldPlayer;
		IEntityDataSaver player   = (IEntityDataSaver) newPlayer;

		player.getPersistentData().putIntArray("homepos", original.getPersistentData().getIntArray("homepos"));
		player.getPersistentData().putString("pos_name", original.getPersistentData().getString("pos_name"));
	}
}
