package net.chocomint.more_ores.util.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.math.Vec3d;

public class FreezeEffect extends StatusEffect {
	public FreezeEffect(StatusEffectCategory category, int color) {
		super(category, color);
	}

	@Override
	public void applyUpdateEffect(LivingEntity entity, int amplifier) {
		if(entity.world.isClient()) {
			Vec3d pos = entity.getPos();
			entity.teleport(pos.getX(), pos.getY(), pos.getZ());
			entity.setVelocity(0, 0, 0);
		}

		super.applyUpdateEffect(entity, amplifier);
	}

	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		return true;
	}
}
