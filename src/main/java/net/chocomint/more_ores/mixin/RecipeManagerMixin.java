package net.chocomint.more_ores.mixin;

import com.google.gson.JsonElement;
import net.chocomint.more_ores.More_Ores;
import net.chocomint.more_ores.dynamic_recipe.ModDynamicRecipes;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {

	@Inject(method = "apply", at = @At("HEAD"))
	public void interceptApply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo info) {

		map.put(new Identifier(More_Ores.MOD_ID, "silver_pickaxe"), ModDynamicRecipes.SILVER_PICKAXE_RECIPE);
	}
}
