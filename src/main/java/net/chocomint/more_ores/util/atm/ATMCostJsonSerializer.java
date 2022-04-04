package net.chocomint.more_ores.util.atm;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ATMCostJsonSerializer {
	public static HashMap<String, Integer> COST_LIST = new HashMap<>();
	private static final Path path = FabricLoader.getInstance().getConfigDir();

	public static HashMap<String, Integer> INIT = new HashMap<>() {{
		put("minecraft:diamond", 1000);
		put("minecraft:iron_ingot", 100);
	}};

	public static void init() throws IOException {
		File file = new File(path + "/cost.json");

		if(!file.exists()) {
			Files.createFile(new File(path + "/cost.json").toPath());
		}

		if(file.length() == 0) {
			FileWriter writer = new FileWriter(file);
			writer.write("{\n");

			int cnt = 0, MapSize = INIT.size();
			for(Map.Entry<String, Integer> entry : INIT.entrySet()) {
				String key = entry.getKey();
				int value = entry.getValue();

				writer.write("\t\"" + key + "\": " + value + (cnt == MapSize - 1 ? "" : ",") + "\n");
				cnt++;
			}

			writer.write("}");
			writer.close();
		}
	}

	public static void ReadJsonIntoList() throws FileNotFoundException {
		File file = new File(path + "/cost.json");
		JsonObject json = (JsonObject) JsonParser.parseReader(new FileReader(file));

		for (String key : json.keySet()) {
			COST_LIST.put(key, json.get(key).getAsInt());
		}
	}
}
