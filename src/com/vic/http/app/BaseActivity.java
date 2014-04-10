package com.vic.http.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ListFragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.Camera.Area;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.vic.http.common.C;
import com.vic.http.entity.Menu;
import com.vic.http.utils.Network;
//import com.actionbarsherlock.view.Menu;
//import com.actionbarsherlock.view.MenuItem;

public class BaseActivity extends Activity {

	public Map<String, String> map;

	public static List<Activity> activityList = new ArrayList<Activity>();
    public String tag = this.getClass().getSimpleName(); // tag 用于测试log用
	public  Context context; // 存储上下文对象

	public static boolean isChangeLangage = true;

	protected ListFragment mFrag;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

//		// set the Behind View
//		setBehindContentView(R.layout.menu_frame);
//		mFrag = (ListFragment)this.getSupportFragmentManager().findFragmentById(R.id.menu_frame);
//
//		// customize the SlidingMenu
//		SlidingMenu sm = getSlidingMenu();
//		sm.setShadowWidthRes(R.dimen.shadow_width);
//		sm.setShadowDrawable(R.drawable.shadow);
//		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
//		sm.setFadeDegree(0.35f);
//		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

//		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		if(!(C.display.widthPixels >0 && C.display.heightPixels >0 )){
	        DisplayMetrics dm = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(dm);
			// 获得手机的宽带和高度像素单位为px
			C.display.widthPixels = dm.widthPixels;
			C.display.heightPixels = dm.heightPixels;
        }

        //初始化
        if(activityList == null) activityList = new ArrayList<Activity>();
        activityList.add(this);
        context = this;

//        if(isChangeLangage){
//        	Resources resources = getResources();//获得res资源对象
//        	Configuration config = resources.getConfiguration();//获得设置对象
//        	DisplayMetrics dm = resources .getDisplayMetrics();//获得屏幕参数：主要是分辨率，像素等。
//        	String langage = LangageActivity.getStringLangage(context);
//        	if(langage != null && langage.equals("kr")){
//        		config.locale = Locale.KOREAN; // 韩文
//        	}else{
//        		config.locale = Locale.SIMPLIFIED_CHINESE; //简体中文
//        	}
//        	resources.updateConfiguration(config, dm);
//        	isChangeLangage = false;
//        }

      //控制切屏模式
//		this.setRequestedOrientation(Configuration.ORIENTATION_LANDSCAPE);
		this.setRequestedOrientation(Configuration.ORIENTATION_PORTRAIT);

		checkNetWorkShowLog(sendType);

	}

	public void onCreate(Bundle savedInstanceState, boolean isCheckNetwork) {
		super.onCreate(savedInstanceState);
		if(!(C.display.widthPixels >0 && C.display.heightPixels >0 )){
	        DisplayMetrics dm = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(dm);
			// 获得手机的宽带和高度像素单位为px
			C.display.widthPixels = dm.widthPixels;
			C.display.heightPixels = dm.heightPixels;
        }

		//初始化
		if(activityList == null) activityList = new ArrayList<Activity>();
		activityList.add(this);
		context = this;

//		if(isChangeLangage){
//			Resources resources = getResources();//获得res资源对象
//			Configuration config = resources.getConfiguration();//获得设置对象
//			DisplayMetrics dm = resources .getDisplayMetrics();//获得屏幕参数：主要是分辨率，像素等。
//			String langage = LangageActivity.getStringLangage(context);
//			if(langage != null && langage.equals("kr")){
//				config.locale = Locale.KOREAN; // 韩文
//			}else{
//				config.locale = Locale.SIMPLIFIED_CHINESE; //简体中文
//			}
//			resources.updateConfiguration(config, dm);
//			isChangeLangage = false;
//		}

		//控制切屏模式
//		this.setRequestedOrientation(Configuration.ORIENTATION_LANDSCAPE);
		this.setRequestedOrientation(Configuration.ORIENTATION_PORTRAIT);
		if(isCheckNetwork)
			checkNetWorkShowLog(sendType);
	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//		case android.R.id.home:
//			toggle();
//			return true;
//		case R.id.github:
////			Util.goToGitHub(this);
//			showSecondaryMenu();
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		getSupportMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		try {
			if(activityList != null && activityList.size()>0 && activityList.contains(this))
				activityList.remove(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 退出程序
	 */
	public static void exit() {
		isChangeLangage = true;
		for (int i = 0; i < activityList.size(); i++) {
			if (null != activityList.get(i)) {
				activityList.get(i).finish();
			}
		}
		activityList = null;
	}

	public int sendType = 2;

	@SuppressWarnings("unchecked")
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null){

//        	App.area_name = savedInstanceState.getString("App.area_name");
//        	App.area_num = savedInstanceState.getString("App.area_num");
//        	App.latitude = savedInstanceState.getDouble("App.latitude", App.latitude);
//        	App.listAdvs = (ArrayList<Adv>) savedInstanceState.getSerializable("App.listAdvs");
//        	App.listAreas = (ArrayList<Area>) savedInstanceState.getSerializable("App.listAreas");
//        	App.listMenus = (ArrayList<Menu>) savedInstanceState.getSerializable("App.listMenus");
//        	App.longitude = savedInstanceState.getDouble("App.longitude", App.longitude);
//        	App.network_name = savedInstanceState.getString("App.network_name");
//        	App.nonelatitude = savedInstanceState.getDouble("App.nonelatitude", App.nonelatitude);
//        	App.nonelongitude = savedInstanceState.getDouble("App.nonelongitude", App.nonelongitude);
//        	C.display.heightPixels = savedInstanceState.getInt("C.display.heightPixels");
//        	C.display.widthPixels = savedInstanceState.getInt("C.display.widthPixels");
////        	App.user = (User) savedInstanceState.getSerializable("App.user");
////        	if(App.user == null) App.user = new User();
        }
	}

	@Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putString("App.area_name", App.area_name);
//        outState.putString("App.area_num", App.area_num);
//        outState.putDouble("App.latitude", App.latitude);
//        outState.putSerializable("App.listAdvs", App.listAdvs);
//        outState.putSerializable("App.listAreas", App.listAreas);
//        outState.putSerializable("App.listMenus", App.listMenus);
//        outState.putDouble("App.longitude", App.longitude);
//        outState.putString("App.network_name", App.network_name);
//        outState.putDouble("App.nonelatitude", App.nonelatitude);
//        outState.putDouble("App.nonelongitude", App.nonelongitude);
//        outState.putSerializable("App.user", App.user);
//        outState.putInt("C.display.heightPixels", C.display.heightPixels);
//        outState.putInt("C.display.widthPixels", C.display.widthPixels);
////        outState.putSerializable("App.mApp", App.mApp);
    }

	/**
	 *
	 * 检测网络是否正常
	 * @param type sendType
	 * @return true：正常 false：不正常
	 * 创建时间：2012-9-4 下午5:55:03
	 */
	public boolean checkNetWorkShowLog(final int type){
		//判断网络是否可用
		if (!Network.checkNetWork(context)) { // ?行?置??
			Builder b = new AlertDialog.Builder(context).setTitle(R.string.network_show_title)
					.setMessage(R.string.network_show_msg);
			b.setPositiveButton(R.string.network_show_setting, new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int whichButton) {
					if(type == 1){ // 设置打开wif
						Intent intent=new Intent(Settings.ACTION_WIFI_SETTINGS);
//						ComponentName cName = new ComponentName("com.android.phone","com.android.phone.Settings");
//						intent.setComponent(cName);
						startActivityForResult(intent,sendType);
						sendType = 2;
					} else { // 设置打开 3G
						Intent intent=new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS);
						ComponentName cName = new ComponentName("com.android.phone","com.android.phone.Settings");
						intent.setComponent(cName);
						startActivityForResult(intent,sendType);
						sendType = 1;
					}
				}
			}).setNeutralButton(R.string.network_show_exit, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
//					// 进行跳转
//					Message msg = new Message();
//					msg.what = 3;
//					mHandler.sendMessage(msg);
//					dialog.dismiss();
					BaseActivity.exit();
				}
			}).show();
			return false;
		}
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		checkNetWorkShowLog(sendType);
	}


	@Override
	public Dialog onCreateDialog(int id) {
//		String show_msg = "";
		int show_title = 0;
		LayoutInflater li = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
		View dv = (View) li.inflate(R.layout.dialog_progress, null);
		switch (id) {
		case 1:
//			TextView loading = (TextView)dv.findViewById(R.id.loading);
//			loading.setText(R.string.progress_loading);
			break;
		case 2:
//			TextView loading2 = (TextView)dv.findViewById(R.id.loading);
//			loading2.setText(R.string.progress_login);
			break;
		case 3:
//			TextView loading3 = (TextView)dv.findViewById(R.id.loading);
//			loading3.setText(R.string.progress_comment);
			break;
		case 4:
//			show_msg = "获取数据失败";
			show_title = R.string.dialog_title_getDataFail;
			break;
		case 5:
//			show_msg = "网络超时，服务器断开或者请检查网络连接是否正常";
			show_title = R.string.dialog_title_newwork_request_timeout;
			break;
		case 6:
//			show_msg = "暂无数据";
			show_title = R.string.dialog_title_nowData;
			break;
		case 7:
//			show_msg = "获取用户信息失败";
			show_title = R.string.dialog_title_getUserInfoDataFail;
			break;
		case 8:
//			show_msg = "发送数据失败";
			break;
		default:
			return null;
		}
		switch (id) {
		case 1:
		case 2:
		case 3:
			return new AlertDialog.Builder(this)
			.setView(dv).create();
		default:
			return new AlertDialog.Builder(this)
			.setMessage(show_title)
			.setPositiveButton(R.string.confirm,
					new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog,
						int which) {
					dialog.dismiss();
				}
			}).create();
		}
	}
}