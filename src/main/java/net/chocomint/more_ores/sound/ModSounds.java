package net.chocomint.more_ores.sound;

import net.chocomint.more_ores.More_Ores;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {

	public static SoundEvent LEMON = registerSoundEvent("lemon");

	private static SoundEvent registerSoundEvent(String name) {
		Identifier id = new Identifier(More_Ores.MOD_ID, name);
		return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
	}
}
