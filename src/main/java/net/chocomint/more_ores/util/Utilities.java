package net.chocomint.more_ores.util;

import java.util.ArrayList;
import java.util.List;

public class Utilities {

	public static final String[] NUMBERS = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
	public static final String[] SMALL_UNITS = {"", "十", "百", "千"};
	public static final String[] BIG_UNITS = {"", "萬", "億", "兆", "京"};

	public static String toLZHNumber(long n) {
		StringBuilder res = new StringBuilder();
		StringBuilder number = new StringBuilder(Long.toString(n < 0 ? -n : n));
		List<String> numberList = new ArrayList<>();
		List<String> resList = new ArrayList<>();

		number.reverse();
		while (number.length() > 4) {
			numberList.add(new StringBuilder(number.substring(0, 4)).reverse().toString());
			number.delete(0, 4);
		}
		numberList.add(number.reverse().toString());

		for (String s : numberList)
			resList.add(fourToLZH(s));

		if (n < 0) res.append("負");

		for (int i = resList.size() - 1; i >= 0; i--)
			res.append(resList.get(i)).append(BIG_UNITS[i]);

		return res.toString();
	}

	public static String fourToLZH(String n) {
		StringBuilder builder = new StringBuilder();
		int len = n.length();
		for (int i = len - 1; i >= 0; i--) {
			int x = n.toCharArray()[len - 1 - i] - '0';
			builder.append(NUMBERS[x]).append(x == 0 ? "" : SMALL_UNITS[i]);
		}
		return builder.toString().replaceAll("零+", "零")
				.replaceAll("零$", "").replaceAll("^一十", "十");
	}
}
