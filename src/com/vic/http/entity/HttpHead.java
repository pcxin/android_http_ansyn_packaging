package com.vic.http.entity;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.vic.http.common.C;

/**
 * @Description: 请求�?
 * @author vic
 * @date 2013-4-12 下午9:53:00
 */
public class HttpHead implements Serializable {

	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = 1L;

	public String login;
	public String register;
	public String getMsg;
	public String recommendList;
	public String recommendJump;
	public String getUserInfo;
	public String setUserInfo;
	public String setPassword;
	public String submitFeedback;
	public String getHelp;
	public String getAvailable;
	public String getReview;
	public String getSuccess;
	public String getFailed;
	public String getDetail;
	public String getDownload;
	public String submitTask;
	public String setAlipayInfo;
	public String getAccountInfo;
	public String getFlow;
	public String chargeAlipay;
	public String chargeMobile;
	public String setPreInstalled;
	public String setInstalled;
	public String submitNoImgTask;

	/**
	 * @Description:
	 * @author vic
	 * @param context
	 * @param data
	 * @param isCache
	 * @return
	 */
	public HttpHead convertHttpHead(Context context, String data) {
		String tempItem = null;
		StringBuffer item;
		String[] s;
		try {
			Field[] fields = this.getClass().getDeclaredFields();
			JSONObject jsonObject = new JSONObject(data);
			for (int j = 0; j < fields.length; j++) {
				Field field = fields[j];
				try {
					tempItem = fields[j].getName();
					try {
						jsonObject.get(tempItem);
					} catch (Exception e) {
						continue;
					}
					if (tempItem.equals("serialVersionUID")
							|| jsonObject.isNull(tempItem)
							|| jsonObject.get(tempItem) == JSONObject.NULL
							)
						continue;
					item = new StringBuffer();
					s = jsonObject.get(tempItem).toString().split("&");
					for (int k = 0; k < s.length; k++) {
						switch (k) {
						case 0:
//							item.append("http://").append(C.http.CChannel1).append(":").append(s[k]);
							break;

						default:
							item.append("/").append(s[k]);
							break;
						}
					}
					field.set(this, item.toString());
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
		return "HttpHead [login=" + login + ", register=" + register
				+ ", getMsg=" + getMsg + ", recommendList=" + recommendList
				+ ", recommendJump=" + recommendJump + ", getUserInfo="
				+ getUserInfo + ", setUserInfo=" + setUserInfo
				+ ", setPassword=" + setPassword + ", submitFeedback="
				+ submitFeedback + ", getHelp=" + getHelp + ", getAvailable="
				+ getAvailable + ", getReview=" + getReview + ", getSuccess="
				+ getSuccess + ", getFailed=" + getFailed + ", getDetail="
				+ getDetail + ", getDownload=" + getDownload + ", submitTask="
				+ submitTask + ", setAlipayInfo=" + setAlipayInfo
				+ ", getAccountInfo=" + getAccountInfo + ", getFlow=" + getFlow
				+ ", chargeAlipay=" + chargeAlipay + ", chargeMobile="
				+ chargeMobile + ", setPreInstalled=" + setPreInstalled
				+ ", setInstalled=" + setInstalled + "]";
	}
}
