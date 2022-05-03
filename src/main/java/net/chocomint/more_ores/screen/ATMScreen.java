package net.chocomint.more_ores.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.chocomint.more_ores.More_Ores;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.packet.c2s.play.RenameItemC2SPacket;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.chocomint.more_ores.util.Enums.*;

import static net.chocomint.more_ores.block.custom.ATMBlock.*;

public class ATMScreen extends HandledScreen<ATMScreenHandler> {
	private ButtonWidget BUY;
	private TextFieldWidget nameField;

	private static final Identifier TEXTURE = new Identifier(More_Ores.MOD_ID, "textures/gui/atm_gui.png");

	public ATMScreen(ATMScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title);
	}

	@Override
	protected void init() {
		super.init();
		// Center the title
		titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;

		// Create Buttons
		int x = (width - backgroundWidth) / 2;
		int y = (height - backgroundHeight) / 2;
		this.BUY = this.addDrawableChild(new ButtonWidget(x + 110, y + 50, 35, 20, new LiteralText("Buy"), button -> {
			System.out.println("Buy!");
			this.close();
		}));
	}

	private void onRenamed(String name) {
		if (!name.isEmpty()) {
			System.out.println(name);
		}
	}

	@Override
	protected void onMouseClick(Slot slot, int slotId, int button, SlotActionType actionType) {
		super.onMouseClick(slot, slotId, button, actionType);
	}

	@Override
	protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

		int x = (width - backgroundWidth) / 2;
		int y = (height - backgroundHeight) / 2;
		TextureRenderer TextureRenderer = (texture, relateX, relateY, u, v, w, h) -> {
			RenderSystem.setShaderTexture(0, texture);
			drawTexture(matrices, x + relateX, y + relateY, u, v, w, h);
		};

		TextureRenderer.draw(TEXTURE, 0, 0, 0, 0, backgroundWidth, backgroundHeight);

		TextRenderer TextRenderer = (text, relateX, relateY) -> textRenderer.draw(matrices, text, x + relateX, y + relateY, 0);
		TextRenderer.draw(new LiteralText("Welcome! ").append(new LiteralText(PLAYER).formatted(Formatting.ITALIC, Formatting.BLUE)), 8, 18);
		TextRenderer.draw(new LiteralText("Rank: ").append(CARD.getText()), 8, 28);
		TextRenderer.draw(new LiteralText("$" + COIN).formatted(Formatting.YELLOW), 8, 38);
		System.out.println(COST);
		TextRenderer.draw(new LiteralText(COST == -1 ? "Invalid item!" : "Cost: " + COST)
				.formatted(Formatting.RED), 28, 55);
	}

	interface TextureRenderer {
		void draw(Identifier texture, int relateX, int relateY, int u, int v, int w, int h);
	}
	interface TextRenderer {
		void draw(Text text, int relateX, int relateY);
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
