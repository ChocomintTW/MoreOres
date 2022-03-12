package net.chocomint.more_ores.mixin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.chocomint.more_ores.More_Ores;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

import static net.chocomint.more_ores.item.ModTools.*;

//                                                    __----~~~~~~~~~~~------___
//                                   .  .   ~~//====......          __--~ ~~
//                   -.            \_|//     |||\\  ~~~~~~::::... /~
//                ___-==_       _-~o~  \/    |||  \\            _/~~-
//        __---~~~.==~||\=_    -_--~/_-~|-   |\\   \\        _/~
//    _-~~     .=~    |  \\-_    '-~7  /-   /  ||    \      /
//  .~       .~       |   \\ -_    /  /-   /   ||      \   /
// /  ____  /         |     \\ ~-_/  /|- _/   .||       \ /
// |~~    ~~|--~~~~--_ \     ~==-/   | \~--===~~        .\
//          '         ~-|      /|    |-~\~~       __--~~
//                      |-~~-_/ |    |   ~\_   _-~            /\
//                           /  \     \__   \/~                \__
//                       _--~ _/ | .-~~____--~-/                  ~~==.
//                      ((->/~   '.|||' -_|    ~~-/ ,              . _||
//                                 -_     ~\      ~~---l__i__i__i--~~_/
//                                 _-~-__   ~)  \--______________--~~
//                               //.-~~~-~_--~- |-------~~~~~~~~
//                                      //.-~~~--\)
//                               神獸保佑 Mixin 沒問題！

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {

	@Inject(method = "apply", at = @At("HEAD"))
	public void interceptApply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo info) {
		RE RecipeRegister = (String name, JsonObject[] recipe) -> {
			map.put(new Identifier(More_Ores.MOD_ID, name + "_sword"), recipe[0]);
			map.put(new Identifier(More_Ores.MOD_ID, name + "_shovel"), recipe[1]);
			map.put(new Identifier(More_Ores.MOD_ID, name + "_pickaxe"), recipe[2]);
			map.put(new Identifier(More_Ores.MOD_ID, name + "_axe"), recipe[3]);
			map.put(new Identifier(More_Ores.MOD_ID, name + "_hoe"), recipe[4]);
		};

		RecipeRegister.register("silver", SILVER_TOOL_RECIPE);
		RecipeRegister.register("titanium", TITANIUM_TOOL_RECIPE);
	}

	interface RE {
		void register(String name, JsonObject[] recipe);
	}
}
