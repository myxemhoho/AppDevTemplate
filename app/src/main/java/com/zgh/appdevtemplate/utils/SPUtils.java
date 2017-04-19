package com.zgh.appdevtemplate.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * 
 * @author woods
 * 
 */
public class SPUtils {
    /**
     * 保存在手机里面的文件名
     */
    public static final String FILE_NAME = "share_data";

	public SPUtils() {

	}

	public static void setSharePre(Context context, String key, String value) {
		setSharePre(context, FILE_NAME, key, value);
	}

	public static void setBooleanSharePre(Context context, String key, boolean value) {
		setBooleanSharePre(context, FILE_NAME, key, value);
	}

	public static void setSharePre(Context context, String key, int value) {
		setSharePre(context, FILE_NAME, key, value);
	}

	public static String getStrSharePre(Context context, String key) {
		return getStrSharePre(context, FILE_NAME, key);
	}

	public static boolean getBooleanSharePre(Context context, String key) {
		return getBooleanSharePre(context, FILE_NAME, key);
	}

	public static int getIntSharePre(Context context, String key) {
		return getIntSharePre(context, FILE_NAME, key);
	}

    public static boolean clearSharePre(Context context) {
        return clearSharePre(context, FILE_NAME);
    }

    public static boolean clearSharePreKey(Context context,String key) {
        return clearSharePre(context, FILE_NAME,key);
    }

    public static boolean contains(Context context, String key) {
        return contains(context, FILE_NAME, key);
    }

    public static Map<String, ?> getAll(Context context) {
        return getAll(context, FILE_NAME);
    }

    /**
	 * 写入Android共享存储
	 * 
	 * @param context
	 *            上下文
	 * @param fileName
	 *            文件名
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	private static void setSharePre(Context context, String fileName, String key, String value) {
		SharedPreferences pre = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = pre.edit();
		editor.putString(key, value);
		editor.apply();
//		editor.commit();
	}

	private static void setBooleanSharePre(Context context, String fileName, String key, boolean value) {
		SharedPreferences pre = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = pre.edit();
		editor.putBoolean(key, value);
		editor.apply();
//		editor.commit();
	}

	/**
	 * 写入Android共享存储
	 * 
	 * @param context
	 *            上下文
	 * @param fileName
	 *            文件名
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	private static void setSharePre(Context context, String fileName, String key, int value) {
		SharedPreferences pre = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = pre.edit();
		editor.putInt(key, value);
		editor.apply();
//		editor.commit();
	}

	/**
	 * 读取Android共享存储
	 * 
	 * @param context
	 *            上下文
	 * @param key
	 *            键
	 */
	private static String getStrSharePre(Context context, String fileName, String key) {
		SharedPreferences pre = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return pre.getString(key, "");
	}

	private static boolean getBooleanSharePre(Context context, String fileName, String key) {
		SharedPreferences pre = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return pre.getBoolean(key, false);
	}

	/**
	 * 读取Android共享存储
	 * 
	 * @param context
	 *            上下文
	 * @param key
	 *            键
	 */
	private static int getIntSharePre(Context context, String fileName, String key) {
		SharedPreferences pre = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return pre.getInt(key, 0);
	}

	/**
	 * 清除SharePre
	 * 
	 * @param context
	 * @param fileName
	 * @param key
	 */
	public static boolean clearSharePre(Context context, String fileName, String key) {
		SharedPreferences pre = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = pre.edit();
		editor.remove(key);
		return editor.commit();
	}

	/**
	 * 清除SharePre
	 * 
	 * @param context
	 * @param fileName
	 */
	public static boolean clearSharePre(Context context, String fileName) {
		SharedPreferences pre = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = pre.edit();
		return editor.clear().commit();
	}

    /**
     * 查询某个key是否已经存在
     *
     * @param context
     * @param key
     * @return
     */
    private static boolean contains(Context context,String fileName, String key) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @param context
     * @return
     */
    private static Map<String, ?> getAll(Context context,String fileName) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getAll();
    }
}
