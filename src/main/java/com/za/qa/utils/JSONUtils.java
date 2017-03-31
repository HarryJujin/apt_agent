package com.za.qa.utils; 

import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/** 
 * @author jujinxin 
 * @version 创建时间：2017年3月23日 上午11:24:40 
 * 类说明 
 */

public class JSONUtils {
	
	private static boolean isJsonObject(String json) {
		return isJson(json, false);
	}

	private static boolean isJsonArray(String json) {
		return isJson(json, true);
	}

	private static boolean isJson(String json, boolean isArray) {
		try {
			if (json == null || json.trim().equals(""))
				return false;
			if (isArray)
				JSONArray.parseArray(json);
			else
				JSONObject.parseObject(json);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean isJson(String json) {
		return isJsonObject(json) || isJsonArray(json);
	}
	
	/**
	 * @param jsonStr
	 *            传进来的json接口
	 * @param keyName
	 *            key
	 * @param target
	 *            value
	 * @return
	 */
	public static boolean verifyJSONKeyValueEquals(String jsonStr,
			String keyName, String target) {
		if (isJsonObject(jsonStr)) {
			JSONObject jsonObject = JSONObject.parseObject(jsonStr);
			// System.out.println("--------jsonObject: " + jsonObject);
			for (String k : jsonObject.keySet()) {
				Object value = jsonObject.get(k);
				if (keyName.equals(k)) {
					System.out.println("--------key: " + k + ", value: "
							+ value);
					if (value.equals(target)) {
						return true;
					}
				} else {
					if (value != null && isJson(value.toString())) {
						if (verifyJSONKeyValueEquals(value.toString(), keyName,
								target)) {
							return true;
						}
					}
				}
			}
		} else if (isJsonArray(jsonStr)) {
			JSONArray jsonArray = JSONArray.parseArray(jsonStr);
			// System.out.println("--------jsonArray: " + jsonArray);
			Iterator<Object> iterator = jsonArray.iterator();
			while (iterator.hasNext()) {
				Object o = iterator.next();
				if (o != null && isJson(o.toString())) {
					if (verifyJSONKeyValueEquals(o.toString(), keyName, target))
						return true;
				}
			}
		} else {
			return false;
		}
		return false;
	}

	public static boolean verifyJSONKeyValueNotEquals(String jsonStr,
			String keyName, String target) {
		if (isJsonObject(jsonStr)) {
			JSONObject jsonObject = JSONObject.parseObject(jsonStr);
			// System.out.println("--------jsonObject: " + jsonObject);
			for (String k : jsonObject.keySet()) {
				Object value = jsonObject.get(k);
				if (keyName.equals(k)) {
					System.out.println("--------key: " + k + ", value: "
							+ value);
					if (!value.equals(target)) {
						return true;
					} else {
						return false;
					}
				} else {
					if (value != null && isJson(value.toString())) {
						if (verifyJSONKeyValueNotEquals(value.toString(),
								keyName, target)) {
							return true;
						}
					}
				}
			}
		} else if (isJsonArray(jsonStr)) {
			JSONArray jsonArray = JSONArray.parseArray(jsonStr);
			// System.out.println("--------jsonArray: " + jsonArray);
			Iterator<Object> iterator = jsonArray.iterator();
			while (iterator.hasNext()) {
				Object o = iterator.next();
				if (o != null && isJson(o.toString())) {
					if (verifyJSONKeyValueNotEquals(o.toString(), keyName,
							target))
						return true;
				}
			}
		} else {
			return false;
		}
		return false;
	}

	/**
	 * verifyJSONKeyValueContains 值包含某个字符返回true
	 * 
	 * @param jsonStr
	 * @param keyName
	 * @param target
	 * @return
	 */
	public static boolean verifyJSONKeyValueContains(String jsonStr,
			String keyName, String target) {
		if (isJsonObject(jsonStr)) {
			JSONObject jsonObject = JSONObject.parseObject(jsonStr);
			// System.out.println("--------jsonObject: " + jsonObject);
			for (String k : jsonObject.keySet()) {
				Object value = jsonObject.get(k);
				if (keyName.equals(k)) {
					System.out.println("--------key: " + k + ", value: "
							+ value);
					if (value.toString().contains(target) && !target.equals("")) {
						return true;
					} else {
						return false;
					}
				} else {
					if (value != null && isJson(value.toString())) {
						if (verifyJSONKeyValueContains(value.toString(),
								keyName, target)) {
							return true;
						}
					}
				}
			}
		} else if (isJsonArray(jsonStr)) {
			JSONArray jsonArray = JSONArray.parseArray(jsonStr);
			// System.out.println("--------jsonArray: " + jsonArray);
			Iterator<Object> iterator = jsonArray.iterator();
			while (iterator.hasNext()) {
				Object o = iterator.next();
				if (o != null && isJson(o.toString())) {
					if (verifyJSONKeyValueContains(o.toString(), keyName,
							target))
						return true;
				}
			}
		} else {
			return false;
		}
		return false;
	}

	/**
	 * key的值为null返回false不为null返回true
	 * 
	 * @param jsonStr
	 * @param keyName
	 * @param target
	 * @return
	 */
	public static boolean verifyJSONKeyValueNotNull(String jsonStr, String keyName) {

		if (isJsonObject(jsonStr)) {
			JSONObject jsonObject = JSONObject.parseObject(jsonStr);
			// System.out.println("--------jsonObject: " + jsonObject);
			for (String k : jsonObject.keySet()) {
				Object value = jsonObject.get(k);
				if (keyName.equals(k)) {
					System.out.println("--------key: " + k + ", value: " + value);
					if (!value.equals("") && value != null && !value.equals(" ")) {
						return true;
					} else {
						return false;
					}
				} else {
					if (value != null && isJson(value.toString())) {
						if (verifyJSONKeyValueNotNull(value.toString(), keyName)) {
							return true;
						}
					}
				}
			}
		} else if (isJsonArray(jsonStr)) {
			JSONArray jsonArray = JSONArray.parseArray(jsonStr);
			// System.out.println("--------jsonArray: " + jsonArray);
			Iterator<Object> iterator = jsonArray.iterator();
			while (iterator.hasNext()) {
				Object o = iterator.next();
				if (o != null && isJson(o.toString())) {
					if (verifyJSONKeyValueNotNull(o.toString(), keyName))
						return true;
				}
			}
		} else {
			return false;
		}
		return false;
	}
	
	public static String Map2JsonString(Map map){
		JSONObject json=new JSONObject(map);
		return json.toJSONString();
	}
	
	public static Map JsonString2Map(String json){	
		return (Map)JSON.parse(json); 
	
	}


}
 