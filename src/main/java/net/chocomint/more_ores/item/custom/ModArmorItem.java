package net.chocomint.more_ores.item.custom;


import com.google.common.collect.ImmutableMap;
import net.chocomint.more_ores.item.ModArmorMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModArmorItem extends ArmorItem {

	private static final Map<ArmorMaterial, StatusEffectInstance> MATERIAL_TO_EFFECT_MAP = putEffect(
			new MaterialEffect(ModArmorMaterials.SILVER, StatusEffects.SPEED, 200, 1)
	);

	public ModArmorItem(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
		super(material, slot, settings);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if(!world.isClient()) {
			if(entity instanceof PlayerEntity) {
				PlayerEntity player = (PlayerEntity)entity;

				if(hasFullSuitOfArmorOn(player)) {
					evaluateArmorEffects(player);
				}
			}
		}

		super.inventoryTick(stack, world, entity, slot, selected);
	}

	private void evaluateArmorEffects(PlayerEntity player) {
		for (Map.Entry<ArmorMaterial, StatusEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
			ArmorMaterial mapArmorMaterial = entry.getKey();
			StatusEffectInstance mapStatusEffect = entry.getValue();

			if(hasCorrectArmorOn(mapArmorMaterial, player)) {
				addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
			}
		}
	}

	private void addStatusEffectForMaterial(PlayerEntity player, ArmorMaterial mapArmorMaterial, StatusEffectInstance mapStatusEffect) {
		boolean hasPlayerEffect = player.hasStatusEffect(mapStatusEffect.getEffectType());

		if(hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
			player.addStatusEffect(new StatusEffectInstance(mapStatusEffect.getEffectType(),
					mapStatusEffect.getDuration(), mapStatusEffect.getAmplifier()));

			// if(new Random().nextFloat() > 0.6f) { // 40% of damaging the armor! Possibly!
			//     player.getInventory().damageArmor(DamageSource.MAGIC, 1f, new int[]{0, 1, 2, 3});
			// }
		}
	}

	private boolean hasFullSuitOfArmorOn(PlayerEntity player) {
		ItemStack boots = player.getInventory().getArmorStack(0);
		ItemStack leggings = player.getInventory().getArmorStack(1);
		ItemStack breastplate = player.getInventory().getArmorStack(2);
		ItemStack helmet = player.getInventory().getArmorStack(3);

		return !helmet.isEmpty() && !breastplate.isEmpty()
				&& !leggings.isEmpty() && !boots.isEmpty();
	}

	private boolean hasCorrectArmorOn(ArmorMaterial material, PlayerEntity player) {
		ArmorItem boots = ((ArmorItem)player.getInventory().getArmorStack(0).getItem());
		ArmorItem leggings = ((ArmorItem)player.getInventory().getArmorStack(1).getItem());
		ArmorItem breastplate = ((ArmorItem)player.getInventory().getArmorStack(2).getItem());
		ArmorItem helmet = ((ArmorItem)player.getInventory().getArmorStack(3).getItem());

		return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
				leggings.getMaterial() == material && boots.getMaterial() == material;
	}

	private static Map<ArmorMaterial, StatusEffectInstance> putEffect(MaterialEffect... materialEffects) {
		Map<ArmorMaterial, StatusEffectInstance> map = new HashMap<>();
		for(MaterialEffect ME_List : List.of(materialEffects)) {
			map.put(ME_List.getMaterial(), ME_List.getEffect());
		}
		return new ImmutableMap.Builder<ArmorMaterial, StatusEffectInstance>().putAll(map).build();
	}

	private static class MaterialEffect {
		private final ArmorMaterial material;
		private final StatusEffectInstance effect;

		public MaterialEffect(ArmorMaterial material, StatusEffectInstance effect) {
			this.material = material;
			this.effect = effect;
		}

		public MaterialEffect(ArmorMaterial material, StatusEffect EffectType, int duration, int amplifier) {
			this.material = material;
			this.effect = new StatusEffectInstance(EffectType, duration, amplifier);
		}

		public ArmorMaterial getMaterial() {
			return material;
		}

		public StatusEffectInstance getEffect() {
			return effect;
		}
	}
}