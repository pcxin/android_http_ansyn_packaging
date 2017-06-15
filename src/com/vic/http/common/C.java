package com.vic.http.common;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


/**
 * 公共静态类
 * @author chen
 * @date 2012-10-25 下午3:13:44
 */
public final class C {
	/**
	 * 显示屏信息
	 * @author chen
	 * @date 2012-10-25 下午3:15:23
	 */
    public static final class display {
        /** 屏幕分辨率宽度 */
    	public static int widthPixels = 0;
    	/** 屏幕分辨率高度 */
    	public static int heightPixels = 0;

    	/** 容器宽度 */
    	public static int contentWidth = 0;
    	/** 容器高度 */
    	public static int contentHeight = 0;

    }

    /** 百度地图key */
    public static final String MAP_BAIDU_KEY = "AC57AC62D5D7BE2A42218C33983A164E7B4C1666";

    /** 微信key */
    public static final String WEIXIN_APP_ID = "wx53384059ceb70d85";
    /** 微信发图片大小 */
    public static final int THUMB_SIZE = 150;

    /**
     * 分页信息
     * @author chen
     * @date 2012-11-8 下午1:44:36
     */
    public static final class page {
    	/** 每页数量 */
    	public static final int pageSize = 10;
    }

    /** google 统计前缀*/
    public static final String GA_TITLE = "/Android/app/";

    /**
     * 网络连接
     * @author chen
     * @date 2012-10-25 下午3:14:36
     */
    public static final class http {
    	/** 服务器地址 */
    	public static final String CType = "1";
    	public static final String CChannel = "dev";
    	public static boolean isInstall = false;
    	public static final String http_request_head = "https://raw.githubusercontent.com/pchangxin/android_http_ansyn_packaging/master/res/raw/aaa.json";
    	public static String http_img = "";
    	public static final String http_test_bb = "https://raw.githubusercontent.com/pchangxin/android_http_ansyn_packaging/master/res/raw/bbb.json";
    	public static final String http_test_cc = "https://raw.githubusercontent.com/pchangxin/android_http_ansyn_packaging/master/res/raw/ccc.json";

    	/*** http 标识配置 */
//    	public static final int http_area = "http_area".hashCode(); //待优化
    	public static final int http_area = 0xffffff00; //
    	public static final int http_menu = 0xffffff01;
    	public static final int http_adv = 0xffffff02;


    	/** 替换https 为http */
    	public static String httpPic(){
    		if(http_request_head.startsWith("https:")) return http_request_head.replaceAll("https:", "http:");
    		return http_request_head;
    	}

    	public static String joinUrl(Map<String, String> map){
    		StringBuffer buffer = new StringBuffer();
    		buffer.append(C.http.http_request_head)
//    			.append(context.getResources().getString(url))
    			.append("?");
    		if(map != null && map.size()>0)
    		for (String key : map.keySet()) {
    			buffer.append(key)
    				.append("=")
    				.append(map.get(key))
    				.append("&");
    		}
    		String requestUrl = buffer.toString();
    		requestUrl = requestUrl.substring(0,requestUrl.length()-1);
			return requestUrl;
    	}
    }
    public static final class message_title {
    	public static final String success = "SUCCESS";
    }
    public static final class cache{
    	public static final String PHONE114_AREA = "phone114_area";
    	public static final String CHECK_AREA = "check_area";
    	public static final String CHECK_AREA_TITLE = "check_area_title";
    	public static final String CHECK_FENLEI = "check_fenlei";
    	public static final String CHECK_YOUHUI = "check_youhui";
    	public static final String CHECK_WAIMAI = "check_waimai";
    	public static final String USER_NAME = "user_name";
    	public static final String USER_PWD = "user_pwd";

    	/**
    	 * @Description: 缓存数据
    	 * @author vic
    	 * @param key
    	 * @param value
    	 */
    	public static void saveCache(Context context, String key, String value) {
    		SharedPreferences preferences = context.getSharedPreferences("cacheInfo",
    				Context.MODE_PRIVATE);
    		Editor editor = preferences.edit();
    		editor.putString(key, value);
    		editor.commit();
    	}

    	/**
    	 * @Description: 获取缓存数据
    	 * @author vic
    	 * @param key
    	 * @param defaultValue
    	 * @return
    	 */
    	public static String getCacheByKey(Context context, String key,String defaultValue) {
    		SharedPreferences preferences = context.getSharedPreferences("cacheInfo",
    				Context.MODE_PRIVATE);
    		return preferences.getString(key, defaultValue);
    	}
    }
}
