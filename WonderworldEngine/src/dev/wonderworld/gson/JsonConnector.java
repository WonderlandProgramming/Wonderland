package dev.wonderworld.gson;

import java.io.BufferedReader;
import java.io.FileReader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonConnector {
	private static JsonParser parser = new JsonParser();

	public static JsonObject getJson(String path) {
		String fileInfo = readFile(path);
		return !fileInfo.isEmpty() ? (JsonObject) parser.parse(fileInfo) : null;
	}

	private static String readFile(String path) {
		String file = "";

		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String readLine;
			while ((readLine = reader.readLine()) != null) {
				file += readLine + "/n";
			}
			reader.close();
		} catch (Exception e) {

		}

		return file;
	}
}
