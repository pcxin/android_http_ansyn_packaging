package com.vic.http.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

/**
 * 
 * 附近距离实体类
 * 
 * @author Administrator
 *
 */
public class Store implements Serializable{

	private static final long serialVersionUID = -4553916875364006709L;
		/*
		{
		"address":"苗栗縣大湖鄉富興八村寮灣2-4號",
		"category":{
			"categoryName":"食","id":1
		},
		"city":{
			"cityName":"苗栗縣","id":8
		},
		"cityArea":{
			"areaName":"大湖鄉","id":114
		},
		"content":"閒酒莊，於91年12月21日正式營運，園內設有製酒中心景況。",
		"contentImageUrl":"\/images\/store\/content-8.jpg",
		"distance":0,
		"email":"hanlai@webusurf.com.cn",
		"id":8,
		"isVipStore":true,
		"latitude":31.235583,
		"longitude":121.417524,
		"name":"苗栗縣大湖農會農村休閑酒莊",
		"preferential":"酒品類滿5000元（含）送草莓淡酒2入（草莓酒6.5%）乙盒，市值250元。",
		"synopsis":"「帶進人潮、繁榮地方經濟，是大湖地區農會責無旁貸的任務。」",
		"telphone":"037-994986",
		"titleImageUrl":"\/images\/store\/title-8.jpg",
		"updateTime":"2012-07-23",
		"webUrl":"http:\/\/www.dahufarm.org.tw\/"
		}*/
	
	
/*	
	"cardTypeList":[{"id":1,"typeName":"VISA"},{"id":2,"typeName":"MASTERCARD"},{"id":3,"typeName":"JCB"},{"id":4,"typeName":"金融卡"},{"id":5,"typeName":"銀聯卡"}],
	"storeNum":"",
	"typeSet":[{"id":1,"typeName":"金融卡"}],
	},*/
	
	/** Map key : id,typeName ; "cardTypeList":[{"id":1,"typeName":"VISA"},*/
	public List<Map<String,String>> cardTypeList;
	public String storeNum;
	/** "typeSet":[{"id":1,"typeName":"金融卡"}], */
	public List<Map<String,String>> typeSet;
	
	public String address;
	public Map<String,String> category;
	public Map<String,String> city;
	public Map<String,String> cityArea;
	public String content;
	public String contentImageUrl;
	public Integer distance;
	public String email;
	public Integer id;
	/** 特惠商店 */
	public Boolean isVipStore;
	public Double latitude;
	public Double longitude;
	public String name;
	public String preferential;
	public String synopsis;
	public String telphone;
	public String titleImageUrl;
	public String updateTime;
	public String webUrl;
	
	/**
	 * 解析json数据
	 * @param context
	 * @param data json字符串
	 * @param isCache是否缓存到数据库
	 * @return
	 * 创建时间：2012-9-21 下午2:25:06
	 */
	public List<Store> getStoreList(Context context,String data,boolean isCache){
		List<Store> list = new ArrayList<Store>();
		String tempItem = null;
		boolean isAdd = false;
		try {
			JSONArray jsonArray = new JSONArray(data);
			Field[] fields = this.getClass().getDeclaredFields();
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Store store = new Store();
				for (int j = 0; j < fields.length; j++) {
					Field field = fields[j];
					try {
						tempItem = fields[j].getName();
						try {
							jsonObject.get(tempItem);
						}catch(Exception e){
							continue;
						}
						if(tempItem.equals("serialVersionUID") || jsonObject.get(tempItem) == null || jsonObject.get(tempItem).toString().equals("null") || jsonObject.get(tempItem).toString().trim().length() == 0) continue;
						
						if(tempItem.equals("category") || tempItem.equals("city") || tempItem.equals("cityArea")){
							Map<String,String> mmp = new HashMap<String, String>();
							JSONObject itemObj = jsonObject.getJSONObject(tempItem);
							Iterator<?>  keys = itemObj.keys();
					        while (keys.hasNext()) {
					        	String key = keys.next().toString();
//					        	Log.i("store", key+ ":" + itemObj.getString(key));
					        	mmp.put(key, itemObj.getString(key));
							}
					        field.set(store, mmp);
					        continue;
						}
						if(tempItem.equals("cardTypeList") || tempItem.equals("typeSet")){ // 解析 cardTypeList typeSet
							List<Map<String,String>> listItem = new ArrayList<Map<String,String>>();
							JSONArray jarray = jsonObject.getJSONArray(tempItem);
							for (int k = 0; k < jarray.length(); k++) {
								Map<String,String> mmp = new HashMap<String, String>();
								JSONObject itemObj = jarray.getJSONObject(k);
								Iterator<?>  keys = itemObj.keys();
								while (keys.hasNext()) {
									String key = keys.next().toString();
									mmp.put(key, itemObj.getString(key));
								}
								listItem.add(mmp);
							}
							field.set(store, listItem);
							continue;
						}
						
						field.set(store, jsonObject.get(tempItem));
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
				if(isCache){
					isAdd = false;
					try {
//						isAdd = new DBHelper(context).addStore(store);
					} catch (Exception e) {
					}
					if(isAdd){ // 进行数据重新设置
						for (int j = 0; j < fields.length; j++) {
							Field field = fields[j];
							try {
								tempItem = fields[j].getName();
								if( tempItem.equals("serialVersionUID") || 
									tempItem.equals("titleImageUrl") ||
									tempItem.equals("category") ||
									tempItem.equals("synopsis") ||
									tempItem.equals("name") ||
									tempItem.equals("id")
									) continue;
								field.set(store, null);
							} catch (Exception e) {
								e.printStackTrace();
							} 
						}
					}
				}
				list.add(store);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String toString() {
		return "Store [address=" + address + ", category=" + category
				+ ", city=" + city + ", cityArea=" + cityArea + ", content="
				+ content + ", contentImageUrl=" + contentImageUrl
				+ ", distance=" + distance + ", email=" + email + ", id=" + id
				+ ", isVipStore=" + isVipStore + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", name=" + name
				+ ", preferential=" + preferential + ", synopsis=" + synopsis
				+ ", telphone=" + telphone + ", titleImageUrl=" + titleImageUrl
				+ ", updateTime=" + updateTime + ", webUrl=" + webUrl + "]";
	}
	
}
