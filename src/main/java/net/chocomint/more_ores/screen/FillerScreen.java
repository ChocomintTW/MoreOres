package net.chocomint.more_ores.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.chocomint.more_ores.More_Ores;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public class FillerScreen extends HandledScreen<FillerScreenHandler> {
	private static final Identifier TEXTURE =
			new Identifier(More_Ores.MOD_ID, "textures/gui/filler_gui.png");

	public FillerScreen(FillerScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title);
	}

	@Override
	protected void init() {
		super.init();
		// Center the title
		titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
	}

	@Override
	protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE);
		int x = (width - backgroundWidth) / 2;
		int y = (height - backgroundHeight) / 2;
		drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);

		int lava = (int)Math.round(handler.getLavaAmount() * 49.0 / 20000.0);
		this.drawTexture(matrices, x + 47, y + 69 - lava, 0, 218 - lava, 81, lava + 1);

		if(inZone(mouseX, mouseY, 47, 19, 127, 69)) {
			renderTooltip(matrices, new LiteralText(new TranslatableText("fluid.minecraft.lava").getString()
					+ ": " + handler.getLavaAmount()), mouseX, mouseY);
		}
	}

	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		renderBackground(matrices);
		super.render(matrices, mouseX, mouseY, delta);
		drawMouseoverTooltip(matrices, mouseX, mouseY);
	}

	private boolean inRange(int value, int lower, int higher) { return value >= lower && value <= higher; }
	private boolean inZone(int mouseX, int mouseY, int Start_X, int Start_Y, int End_X, int End_Y) {
		int x = (width - backgroundWidth) / 2;
		int y = (height - backgroundHeight) / 2;
		return inRange(mouseX, x + Start_X, x + End_X) && inRange(mouseY, y + Start_Y, y + End_Y);
	}
}
