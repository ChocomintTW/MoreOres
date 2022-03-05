package net.chocomint.more_ores.screen.button;

import com.mojang.blaze3d.systems.RenderSystem;
import net.chocomint.more_ores.More_Ores;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.Identifier;

public class ScreenButton {
	private static final Identifier BUTTON_TEXTURE =
			new Identifier(More_Ores.MOD_ID, "textures/gui/filler_gui.png");

	public void click() {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, new Identifier(More_Ores.MOD_ID, "textures/gui/button" + ".png"));
	}
}
