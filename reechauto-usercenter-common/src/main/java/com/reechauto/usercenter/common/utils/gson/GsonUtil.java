package com.reechauto.usercenter.common.utils.gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

@SuppressWarnings("rawtypes")
public class GsonUtil {
	// 不用创建对象,直接使用Gson.就可以调用方法
	private static Gson gson = null;
	static {
		if (gson == null) {
			// gson = new Gson();
			// 当使用GsonBuilder方式时属性为空的时候输出来的json字符串是有键值key的,显示形式是"key":null，而直接new出来的就没有"key":null的
			gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();

			/*
			 * //null返回空 //.registerTypeAdapterFactory(new
			 * NullStringToEmptyAdapterFactory()) //打开Export注解，但打开了这个注解,副作用，要转换和不转换都要加注解
			 * .excludeFieldsWithoutExposeAnnotation() //是否序列化空值 .serializeNulls() //序列化日期格式
			 * "yyyy-MM-dd" .setDateFormat("yyyy-MM-dd HH:mm:ss") //会把字段首字母大写
			 * .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE) //自动格式化换行
			 * .setPrettyPrinting() //需要结合注解使用，有的字段在1。0的版本的时候解析，但0。1版本不解析 .setVersion(1.0)
			 * .create();
			 */
		}
	}

	// 无参的私有构造方法
	private GsonUtil() {
	}

	/**
	 * 将对象转成json格式
	 * 
	 * @param object
	 * @return String
	 */
	public static String GsonString(Object object) {
		String gsonString = null;
		if (gson != null) {
			gsonString = gson.toJson(object);
		}
		return gsonString;
	}

	/**
	 * 将json转成特定的cls的对象
	 * 
	 * @param gsonString
	 * @param cls
	 * @return
	 */
	public static <T> T GsonToBean(String gsonString, Class<T> cls) {
		T t = null;
		if (gson != null) {
			// 传入json对象和对象类型,将json转成对象
			t = gson.fromJson(gsonString, cls);
		}
		return t;
	}

	/**
	 * json字符串转成list
	 * 
	 * @param gsonString
	 * @param cls
	 * @return
	 */
	public static <T> List<T> GsonToList(String gsonString, Class<T> cls) {
		List<T> list = null;
		if (gson != null) {
			// 根据泛型返回解析指定的类型,TypeToken<List<T>>{}.getType()获取返回类型
			list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
			}.getType());
		}
		return list;
	}
	
	public static <T> List<T> GsonToArrayList(String gsonString, Class<T> cls) {
		 List<T> list = new ArrayList<T>();  
	        try {  
	            JsonArray arry = new JsonParser().parse(gsonString).getAsJsonArray();  
	            for (JsonElement jsonElement : arry) {  
	                list.add(gson.fromJson(jsonElement, cls));  
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return list;  
	}

	/**
	 * json字符串转成list中有map的
	 * 
	 * @param gsonString
	 * @return
	 */
	public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
		List<Map<String, T>> list = null;
		if (gson != null) {
			list = gson.fromJson(gsonString, new TypeToken<List<Map<String, T>>>() {
			}.getType());
		}
		return list;
	}

	/**
	 * json字符串转成map的
	 * 
	 * @param gsonString
	 * @return
	 */
	public static <T> Map<String, T> GsonToMaps(String gsonString) {
		Map<String, T> map = null;
		if (gson != null) {
			map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
			}.getType());
		}
		return map;
	}
}
