package net.chocomint.more_ores.item;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {
	TITANIUM(3, material.TOOL_TITANIUM, () -> {
		return Ingredient.ofItems(ModItems.TITANIUM_INGOT);
	}),
	LEAD(2, material.TOOL_LEAD, () -> {
		return Ingredient.ofItems(ModItems.LEAD_INGOT);
	}),
	SILVER(3, material.TOOL_SILVER, () -> {
		return Ingredient.ofItems(ModItems.SILVER_INGOT);
	});

	private final int miningLevel;
	private final int itemDurability;
	private final float miningSpeed;
	private final float attackDamage;
	private final int enchantability;
	private final Lazy<Ingredient> repairIngredient;

	private ModToolMaterials(int miningLevel, material.@NotNull Tool config, Supplier<Ingredient> repairIngredient) {
		this.miningLevel = miningLevel;
		this.itemDurability = config.durability;
		this.miningSpeed = config.miningSpeed;
		this.attackDamage = config.attackDamage;
		this.enchantability = config.enchantability;
		this.repairIngredient = new Lazy(repairIngredient);
	}

	public int getDurability() {
		return this.itemDurability;
	}

	public float getMiningSpeedMultiplier() {
		return this.miningSpeed;
	}

	public float getAttackDamage() {
		return this.attackDamage;
	}

	public int getMiningLevel() {
		return this.miningLevel;
	}

	public int getEnchantability() {
		return this.enchantability;
	}

	public Ingredient getRepairIngredient() {
		return (Ingredient)this.repairIngredient.get();
	}
}
