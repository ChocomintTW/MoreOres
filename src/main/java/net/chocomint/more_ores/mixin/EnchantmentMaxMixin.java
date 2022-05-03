package net.chocomint.more_ores.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public class EnchantmentMaxMixin {

	@Inject(method = "createNbt", at = @At(value = "HEAD"), cancellable = true)
	public static void createNbt(@Nullable Identifier id, int lvl, CallbackInfoReturnable<NbtCompound> cir) {
		NbtCompound nbtCompound = new NbtCompound();
		nbtCompound.putString("id", String.valueOf(id));
		nbtCompound.putInt("lvl", lvl);
		cir.setReturnValue(nbtCompound);
	}

	@Inject(method = "writeLevelToNbt", at = @At(value = "HEAD"), cancellable = true)
	public static void writeLevelToNbt(NbtCompound nbt, int lvl, CallbackInfoReturnable<Void> cir) {
		nbt.putInt("lvl", lvl);
	}

	@Inject(method = "getLevelFromNbt", at = @At(value = "HEAD"), cancellable = true)
	public static void getLevelFromNbt(NbtCompound nbt, CallbackInfoReturnable<Integer> cir) {
		cir.setReturnValue(MathHelper.clamp(nbt.getInt("lvl"), 0, Integer.MAX_VALUE));
	}
}
