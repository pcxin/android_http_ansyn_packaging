package com.vic.http.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 *
 * 检测网络
 * @author Administrator
 *
 */
public class Network {

	/** 网络不可用 */
	public static final int NONETWORK= 0;
	/** 是wifi连接 */
	public static final int WIFI = 1;
	/** 不是wifi连接 */
	public static final int NOWIFI = 2;

	/**
	 * 检验网络连接 并判断是否是wifi连接
	 * @param context
	 * @return <li>没有网络：Network.NONETWORK;</li> <li>wifi 连接：Network.WIFI;</li> <li>mobile 连接：Network.NOWIFI</li>
	 */
	public static int checkNetWorkType(Context context) {

		if (!checkNetWork(context)) {
			return Network.NONETWORK;
		}
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//		cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting())
			return Network.WIFI;
		else
			return Network.NOWIFI;
	}

	/**
	 * 检测网络是否连接
	 * @param context
	 * @return
	 */
	public static boolean checkNetWork(Context context){
		// 1.获得连接设备管理器
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(cm == null){
			return false;
		}
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if(ni == null || !ni.isAvailable()){
			return false;
		}
		return true;
	}
}
