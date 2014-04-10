package com.vic.http.entity;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class Index implements Serializable{
	
	/** 
	 * @Fields serialVersionUID : 
	 */ 
	private static final long serialVersionUID = 1L;
	
	public String upgrade;
	public String forAudit;
	public String request;
	public String current_time;
	public String device_id;
	public String resultcode;
	public String download_addr;
	public String qq;
	public String alertMsg;
	public HttpHead httpHead;
	
	// else static
	public String encrypt;
	public String user_id = "1";
	public boolean is501 = false;
	public boolean is502 = false;
	/**
	 * @Description: 
	 * @author vic
	 * @param context
	 * @param data
	 * @param isCache
	 * @return
	 */
	public Index convertHttpHead(Context context,String data){
		String tempItem = null;
		try {
			Field[] fields = this.getClass().getDeclaredFields();
				JSONObject jsonObject = new JSONObject(data);
				for (int j = 0; j < fields.length; j++) {
					Field field = fields[j];
					try {
						tempItem = fields[j].getName();
						if(tempItem.equals("serialVersionUID") || jsonObject.isNull(tempItem) || jsonObject.get(tempItem) == JSONObject.NULL) continue;
						if(tempItem.equals("request")){
							this.httpHead = new HttpHead().convertHttpHead(context, jsonObject.get(tempItem).toString());
							continue;
						}
						field.set(this, jsonObject.get(tempItem).toString());
						
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public String toString() {
		return "Index [upgrade=" + upgrade + ", forAudit=" + forAudit
				+ ", current_time=" + current_time
				+ ", device_id=" + device_id + ", resultcode=" + resultcode
				+ ", download_addr=" + download_addr + ", qq=" + qq
				+ ", httpHead=" + httpHead + "]";
	}

}
