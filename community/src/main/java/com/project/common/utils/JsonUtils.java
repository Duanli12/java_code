
package com.project.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;


public class JsonUtils {

	/***
	 * 将List对象序列化为JSON文本
	 */
	public static <T> String toJSONString(List<T> list) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		String jsonStr = gson.toJson(list);
		return jsonStr;
	}

	/***
	 * 将map转换为JSON文本
	 * 
	 * @param object
	 * @return
	 */
	public static JsonObject toJSONObject(String jsonStr) {
		// 创建一个JsonParser
		JsonParser parser = new JsonParser();

		// 通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
		JsonElement el = parser.parse(jsonStr);

		// 把JsonElement对象转换成JsonObject
		JsonObject jsonObj = null;
		if (el.isJsonObject()) {
			jsonObj = el.getAsJsonObject();
		}
		return jsonObj;
	}

	/***
	 * 将map转换为JSON文本
	 * 
	 * @param object
	 * @return
	 */
	public static String toJSONString(Map<String, Object> map) {
		Gson gson = new GsonBuilder().serializeNulls().registerTypeAdapter(String.class, new TypeAdapter<String>() {

			public String read(JsonReader reader) throws IOException {
				if (reader.peek() == JsonToken.NULL) {
					reader.nextNull();
					return "";
				}
				return reader.nextString();
			}
			public void write(JsonWriter writer, String value) throws IOException {
				if (value == null) {
					writer.value("");
					return;
				}
				writer.value(value);
			}
		}).create();
		String jsonStr = gson.toJson(map);
		return jsonStr;
	}

	/***
	 * 将对象序列化为JSON文本
	 * 
	 * @param object
	 * @return
	 */
	public static String toJSONString(Object object) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		String jsonStr = gson.toJson(object);
		return jsonStr;
	}

	/**
	 * json字符串转list
	 * 
	 * @param jsonStr
	 * @return
	 * @see
	 */
	public static List<Map<String, String>> convertJSON2List(String jsonStr, String[] fields) {
		jsonStr = jsonStr.replaceAll("\n", "").replaceAll(" ", "");
		Gson gson = new GsonBuilder().serializeNulls().create();
		List<Map<String, String>> list = gson.fromJson(jsonStr, new TypeToken<List<Map<String, String>>>() {
		}.getType());
		return list;
	}

	/**
	 * json字符串转map
	 * 
	 * @param jsonStr
	 * @return
	 * @see
	 */
	public static Map<String, Object> convertJSON2List(String jsonStr) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		Map<String, Object> map = gson.fromJson(jsonStr, new TypeToken<Map<String, Object>>() {
		}.getType());
		return map;
	}


	/**
	 * json转对象
	 * 
	 * @param jsonStr
	 * @param classType
	 * @return
	 */
	public static <T> T convertJsonToObject(String jsonStr, Class<T> classType) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.fromJson(jsonStr, classType);
	}

	public static void main(String[] args) {
		// String str = "{\"message\": \"登录成功\",\"data\": {\"responseName\":
		// \"13g\",\"cardInfo\": {\"idCardNum\":
		// \"b65c6e85-0afc-4098-80c5-582e313ff1d7\"}}, \"code\": \"1\"}";
	}
}
