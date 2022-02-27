package net.chocomint.more_ores.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.chocomint.more_ores.More_Ores;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class AlloyManufactoryScreen extends HandledScreen<AlloyManufactoryScreenHandler> {
	private static final Identifier TEXTURE =
			new Identifier(More_Ores.MOD_ID, "textures/gui/alloy_manufactory_gui.png");

	public AlloyManufactoryScreen(AlloyManufactoryScreenHandler handler, PlayerInventory inventory, Text title) {
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

		if(handler.isCrafting()) {
			int progress = handler.getScaledProgress();
			this.drawTexture(matrices, x + 87, y + 41, 0, 168, progress, 5);
			// (x, y) is where the "object" will be
			// (u, v) is where the "object" ACTUALLY located at
			// (width, height) is the size of the "object"
		}

		int lava = handler.getLavaAmount() / 100;
		this.drawTexture(matrices, x + 17, y + 65 - lava, 176, 51 - lava, 12, lava + 1);

		if(inRange(mouseX, x + 17, x + 28) && inRange(mouseY, y + 14, y + 65)) {
			renderTooltip(matrices, new LiteralText("lava: " + handler.getLavaAmount()), mouseX, mouseY);
		}
	}

	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		renderBackground(matrices);
		super.render(matrices, mouseX, mouseY, delta);
		drawMouseoverTooltip(matrices, mouseX, mouseY);
	}

	private boolean inRange(int value, int lower, int higher) {
		return value >= lower && value <= higher;
	}
}
