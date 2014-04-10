package com.vic.http.db;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.vic.http.app.R;
import com.vic.http.entity.Store;

/**
 *
 * 本地数据库
 * @author Administrator
 *
 */
public class DBHelper extends SQLiteOpenHelper{
	private Context context;
	private SQLiteDatabase db;
	private static DBHelper mInstance=null;
	private static final String DBNAME = "cacheappBigData.db";
	private static final int VERSION = 1;

//	public  DBHelper(Context context) {
//		this.context = context;
//		if(dbHelper == null)
//		dbHelper = new SQLiteHelper(this.context);
//	}
//	private Context context;


	private DBHelper(Context context, CursorFactory factory) {
//		super(context, mDbName, factory, mDbVersion);
		super(context, DBNAME, factory, VERSION);
	}

	public static DBHelper getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new DBHelper(context, null);
		}
		mInstance.context = context;
		return mInstance;
	}
	public static DBHelper getInstance(Context context,
			SQLiteDatabase.CursorFactory factory) {
		if (mInstance == null) {
			mInstance = new DBHelper(context, factory);
		}
		mInstance.context = context;
		return mInstance;
	}


	/**
	 * 获得url 缓存
	 * @param url
	 * @return
	 */
	public String[] getURLData(String url){
		Cursor cursor = null;
		try {
			db = getReadableDatabase(); // 获得数据库读对象
			cursor = db.rawQuery("select cacheData,createTime from cacheUrlData where url = ?", new String[]{url});
			while (cursor.moveToNext()) {
				String data =cursor.getString(0);
				String time =cursor.getString(1);
				cursor.close();
				return new String[]{data,time};
//				return cursor.getString(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//				try {
//					if(db != null)
//					db.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
		}
		return null;
	}

	/**
	 * 添加或者更新  url 缓存
	 * @param url
	 * @param jsonData
	 */
	public synchronized void addOrUpdateURLData(String url,String jsonData){
		try {
			boolean isExists = checkURLData(url);
			db = getWritableDatabase(); // 获得数据库写对象
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(isExists){
				db.execSQL("update cacheUrlData set cacheData =?, createTime = ? where url =? ",new String[]{jsonData, jsonData, sdf.format(new Date()), url});
			}else{
				db.execSQL("insert into cacheUrlData (url, cacheData, createTime) values(?, ?, ?)",new String[]{url, jsonData, sdf.format(new Date())});
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.i("db", url);
		} finally {
//			db.close();
		}
	}

	/**
	 * 删除记录
	 * @return
	 */
	public boolean deleteURLData(){
		try{
			db = getWritableDatabase(); // 获得数据库写对象\
		return db.delete("cacheUrlData",null,null)  > 0;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
	//		db.close();
		}
		return false;
	}
	/**
	 * 删除有记录的url
	 * @return
	 */
	public boolean deleteURLData(String url){
		try{
			db = getWritableDatabase(); // 获得数据库写对象\
			return db.delete("cacheUrlData", "url like ?", new String[]{"%"+url+"%"}) > 0;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
//			db.close();
		}
		return false;
	}

	/**
	 * 检测是否有url该记录 本类中用  不开放
	 * @param url
	 * @return
	 */
	private synchronized boolean checkURLData(String url){
		Cursor cursor = null;
		try {
			db = getReadableDatabase();
			cursor = db.rawQuery("select cacheData from cacheUrlData where url = ?", new String[]{url});
			while (cursor.moveToNext()) {
				cursor.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			db.close();
		}
		return false;
	}

	/**
	 * 添加商店到数据库 暂时缓存
	 * @param store
	 * @return
	 * 创建时间：2012-9-21 下午2:07:03
	 */
	public boolean addStore(Store store){
		try {
			db = getWritableDatabase(); // 获得数据库写对象
			// 使用流对象 把对象进行二进制输出
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(byteStream);
			oos.writeObject(store);
			// 添加对象到数据库 并记录时间
			ContentValues values = new ContentValues();
			values.put("id", store.id);
			values.put("blobObj", byteStream.toByteArray());
			oos.close();
			return db.insert("store", "id", values) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			db.close();
		}
		return false;
	}

	/**
	 * 清空商店数据
	 * @return
	 */
	public boolean deleteStore(){
		try{
			db = getWritableDatabase(); // 获得数据库写对象\
			return db.delete("store",null,null)  > 0;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
//			db.close();
		}
		return false;
	}

	/**
	 * 获取暂缓商店数据
	 * @param id
	 * @return
	 * 创建时间：2012-9-21 下午2:10:04
	 */
	public Store getStore(int id){
		String sql = "select id, blobObj from store where id = ?";
		ByteArrayInputStream byteStream = null; // 输入二进制流对象
		ObjectInputStream is = null; // 输入数据流化对象
		Cursor cursor = null;
		try{
			db = getReadableDatabase(); // 获得数据库读对象
			cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});
			if (cursor.moveToNext()) {
				byte[] tmpByte = cursor.getBlob(cursor.getColumnIndex("blobObj"));
				byteStream = new ByteArrayInputStream(tmpByte); // 二进制输入
				is = new ObjectInputStream(new BufferedInputStream(byteStream));  // 输入对象
				Store store = (Store) is.readObject(); // 进行转化
				is.close();
				cursor.close();
				return store;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
//			cursor.close();
		}
		return null;
	}

	/**
	 * 添加收藏到数据库
	 * @param store
	 * @return
	 */
	public boolean addShouCang(Store store){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			db = getWritableDatabase(); // 获得数据库写对象
			// 使用流对象 把对象进行二进制输出
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(byteStream);
			oos.writeObject(store);
			// 添加对象到数据库 并记录时间
			ContentValues values = new ContentValues();
			values.put("id", store.id);
			values.put("blobObj", byteStream.toByteArray());
			values.put("time", sdf.format(System.currentTimeMillis()));
			oos.close();
			return db.insert("shoucang", "id", values) > 0;
//			db.execSQL("insert into shoucang (id, blobObj, time) values(?, ?, ?)",new Object[]{store.id, byteStream.toByteArray(),sdf.format(System.currentTimeMillis())});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			db.close();
		}
		return false;
	}

	/**
	 * 获取收藏列表
	 * @return
	 */
	public List<Store> getShouCang(){
		String sql = "select id, blobObj, time from shoucang order by time desc";
		List<Store> data = new ArrayList<Store>();
		ByteArrayInputStream byteStream = null; // 输入二进制流对象
		ObjectInputStream is = null; // 输入数据流化对象
		try{
			db = getReadableDatabase(); // 获得数据库读对象
			Cursor cursor = db.rawQuery(sql, null);
			while (cursor.moveToNext()) {
				byte[] tmpByte = cursor.getBlob(cursor.getColumnIndex("blobObj"));
				byteStream = new ByteArrayInputStream(tmpByte); // 二进制输入
				is = new ObjectInputStream(new BufferedInputStream(byteStream));  // 输入对象
				Store store = (Store) is.readObject(); // 进行转化
				is.close();
				data.add(store);
			}
			cursor.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
//			db.close();
		}
		return data;
	}

	/**
	 * 删除收藏
	 * @return
	 */
	public boolean deleteShouCang(String id){
		try{
			db = getWritableDatabase(); // 获得数据库写对象\
			if(id ==null)
				return db.delete("shoucang",null,null)  > 0;
			else
				return db.delete("shoucang", "id = ?", new String[]{id}) > 0;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
//			db.close();
		}
		return false;
	}

	/**
	 *
	 * 判断是否有收藏记录
	 *
	 * @param id 商店id
	 * @return
	 */
	public boolean isShouCang(String id){
		String sql = "select time from shoucang where id = ?";
		Cursor cursor=null;
		try{
			db = getReadableDatabase(); // 获得数据库读对象
			cursor = db.rawQuery(sql, new String[]{id});
			while (cursor.moveToNext()) {
				cursor.close();
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		}
		return false;
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		 String [] tables = context.getResources().getStringArray(R.array.sql);
		 for (int i = 0; i < tables.length; i++) {
			 db.execSQL(tables[i]);
		 }
//		 boolean cof = new DBHelper(context).isColumnExist("emp", "age");
//		 String updateSql = "alter table emp add column age text";
//		 if(!cof) db.execSQL(updateSql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	/**
	 * 判断是否有表
	 *
	 * @param tableName
	 * @return
	 */
	public boolean isTableExist(SQLiteDatabase db, String tableName) {
		boolean result = false;
		if (tableName == null) {
			return false;
		}

		try {
			Cursor cursor = null;
			String sql = "select count(1) as c from sqlite_master where type ='table' and name ='"
					+ tableName.trim() + "' ";
			cursor = db.rawQuery(sql, null);
			if (cursor.moveToNext()) {
				int count = cursor.getInt(0);
				if (count > 0) {
					result = true;
				}
			}
			cursor.close();
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 判断是否有字段
	 *
	 * @param tableName
	 * @param columnName
	 * @return
	 */
	public boolean isColumnExist(SQLiteDatabase db, String tableName,
			String columnName) {
		boolean result = false;
		if (tableName == null) {
			return false;
		}

		try {
			Cursor cursor = null;
			String sql = "select count(1) as c from sqlite_master where type ='table' and name ='"
					+ tableName.trim()
					+ "' and sql like '%"
					+ columnName.trim() + "%'";
			cursor = db.rawQuery(sql, null);
			if (cursor.moveToNext()) {
				int count = cursor.getInt(0);
				if (count > 0) {
					result = true;
				}
			}
			cursor.close();
		} catch (Exception e) {
		}
		return result;
	}
}
