package net.chocomint.more_ores.item;

public class material {

	public static final Tool TOOL_LEAD = new Tool("lead", 800, 7.0F, 2.0F, 10);
	public static final Tool TOOL_TITANIUM = new Tool("titanium", 1200, 8.5F, 3.0F, 15);
	public static final Tool TOOL_SILVER = new Tool("silver", 1800, 11.0F, 5.0F, 20);

	public static final Armor ARMOR_SILVER = new Armor("silver", 16, new int[]{3, 6, 8, 3}, 15, 3.0F, 0.1F);

	public static final ToolDetail TOOL_LEAD_DETAIL = new ToolDetail(new int[]{8, 8, 8, 8, 8}, new int[]{1, 1, 1, 1, 1});
	public static final ToolDetail TOOL_SILVER_DETAIL = new ToolDetail(new int[]{8, 8, 8, 8, 8}, new int[]{1, 1, 1, 1, 1});
	public static final ToolDetail TOOL_TITANIUM_DETAIL = new ToolDetail(new int[]{8, 8, 8, 8, 8}, new int[]{1, 1, 1, 1, 1});


	// Class
	public static class ToolDetail {
		public int[] atkDamage, atkSpeed;

		public ToolDetail(int[] atkD, int[] atkS) {
			atkDamage = atkD;
			atkSpeed  = atkS;
		}
	}

	public static class Tool {
		String name;
		int durability, enchantability;
		float miningSpeed, attackDamage;

		public Tool(String Name, int dur, float minS, float atkD, int ench) {
			name           = Name;
			durability     = dur;
			miningSpeed    = minS;
			attackDamage   = atkD;
			enchantability = ench;
		}
	}

	public static class Armor {
		String name;
		int durabilityMultiplier, enchantability;
		int[] protectionAmounts;
		float toughness, knockbackResistance;

		public Armor(String Name, int dur, int[] prot, int ench, float tough, float knock) {
			name                 = Name;
			durabilityMultiplier = dur;
			protectionAmounts    = prot;
			enchantability       = ench;
			toughness            = tough;
			knockbackResistance  = knock;
		}
	}
}
