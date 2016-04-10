package com.lyp.neulife.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class NetworkUtil {

	/** 检查是否有网络 */
	public static boolean isNetworkConnected(Context context) {
		if (context != null) {
			NetworkInfo info = getNetworkInfo(context);
			if (info != null) {
				return info.isAvailable();
			}
		}
		return false;
	}


	/** 检查是否是WIFI */
	public static boolean isWifi(Context context) {
		NetworkInfo info = getNetworkInfo(context);
		if (info != null) {
			if (info.getType() == ConnectivityManager.TYPE_WIFI)
				return true;
		}
		return false;
	}

	/** 检查是否是移动网络 */
	public static boolean isMobile(Context context) {
		NetworkInfo info = getNetworkInfo(context);
		if (info != null) {
			if (info.getType() == ConnectivityManager.TYPE_MOBILE)
				return true;
		}
		return false;
	}

	private static NetworkInfo getNetworkInfo(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		return cm.getActiveNetworkInfo();
	}


	/**
	 * 得到网络返回的流数据
	 * @param inputStream
	 * @return
     */
	public static String readStream(InputStream inputStream) {
		String result = "";
		InputStreamReader inputStreamReader;
		try {
			String line = "";
			inputStreamReader = new InputStreamReader(inputStream, "utf-8"); //将字节流转化为字符流
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader); //从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取
			while ((line = bufferedReader.readLine()) != null) {
				result += line;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
