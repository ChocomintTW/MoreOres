package net.chocomint.more_ores.mixin;

import net.minecraft.enchantment.UnbreakingEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(UnbreakingEnchantment.class)
public class UnbreakingEnchantmentMixin {

	@Inject(method = "getMaxLevel", at = @At(value = "HEAD"), cancellable = true)
	public void getMaxLevel(CallbackInfoReturnable<Integer> cir) {
		cir.setReturnValue(5);
	}
}
