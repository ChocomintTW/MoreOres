package net.chocomint.more_ores.mixin;

import net.chocomint.more_ores.util.Utilities;
import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {

	@Shadow public abstract String getTranslationKey();
	@Shadow public abstract boolean isCursed();

	@Inject(method = "getName", at = @At(value = "HEAD"), cancellable = true)
	public void getName(int level, CallbackInfoReturnable<Text> cir) {
		TranslatableText mutableText = new TranslatableText(getTranslationKey());
		mutableText.formatted(isCursed() ? Formatting.RED : Formatting.GRAY);

		if (Objects.equals(MinecraftClient.getInstance().getGame().getSelectedLanguage().getCode(), "lzh")) {
			mutableText.append(" ").append(new LiteralText(Utilities.toLZHNumber(level) + "階"));
			cir.setReturnValue(mutableText);
			return;
		}

		if (level > 10) {
			mutableText.append(" ").append(new LiteralText("lvl." + level));
			cir.setReturnValue(mutableText);
		}
	}
}
