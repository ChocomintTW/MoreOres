package net.chocomint.more_ores.rei;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class DrawUtil {
	public static void drawOverlay(DrawableHelper helper, MatrixStack matrices, Identifier id, int x, int y, int u, int v, int width, int height) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, id);
		helper.drawTexture(matrices, x, y, u, v, width, height);
	}
}
