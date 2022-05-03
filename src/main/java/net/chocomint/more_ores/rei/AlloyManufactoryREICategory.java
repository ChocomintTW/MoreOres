package net.chocomint.more_ores.rei;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.chocomint.more_ores.More_Ores;
import net.chocomint.more_ores.block.ModBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AlloyManufactoryREICategory implements DisplayCategory<AlloyManufactoryREIDisplay> {
	private static final Identifier TEXTURE = new Identifier(More_Ores.MOD_ID, "textures/gui/rei/alloy_manufactory_rei_gui.png");

	private final EntryStack<ItemStack> icon = EntryStacks.of(ModBlocks.ALLOY_MANUFACTORY);

	@Override
	public CategoryIdentifier<? extends AlloyManufactoryREIDisplay> getCategoryIdentifier() {
		return ModREICategoryIdentifiers.ALLOY_MANUFACTORY;
	}

	@Override
	public Renderer getIcon() {
		return icon;
	}

	@Override
	public Text getTitle() {
		return new TranslatableText("rei.more_ores.alloy_manufacture");
	}

	@Override
	public @NotNull List<Widget> setupDisplay(AlloyManufactoryREIDisplay display, Rectangle bounds) {
		List<Widget> widgets = new ArrayList<>();
		int lava = display.getLava();
		List<EntryIngredient> inputs = display.getInputEntries();
		EntryStack<?> output = display.getOutputEntries().get(0).get(0);

		int x = bounds.getMinX(), y = bounds.getMinY();
		int w = bounds.getWidth(), h = bounds.getHeight(); // 150 x 65
		widgets.add(Widgets.createRecipeBase(bounds));
		widgets.add(Widgets.createDrawableWidget(((helper, matrices, mouseX, mouseY, delta) ->
				DrawUtil.drawOverlay(helper, matrices,TEXTURE, x, y, 0, 0, w, h))));

		widgets.add(Widgets.createLabel(new Point(x + 86, y + 20), new LiteralText(
				new TranslatableText("block.minecraft.lava").getString() + ": " + lava).formatted(Formatting.RED)));

		widgets.add(Widgets.createSlot(new Point(x + 18 , y + 24)).entries(inputs.get(0)).disableBackground());
		widgets.add(Widgets.createSlot(new Point(x + 42 , y + 24)).entries(inputs.get(1)).disableBackground());

		widgets.add(Widgets.createSlot(new Point(x + 116, y + 24)).entry(output).disableBackground());

		return widgets;
	}
}
