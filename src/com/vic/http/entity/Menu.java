package com.vic.http.entity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

/**
 * 首页导航菜单
 *
 * 分类页面菜单
 * @author chen
 * @date 2012-11-5 上午11:32:52
 */
public class Menu implements Serializable {

	private static final long serialVersionUID = -7459567002575163628L;
    /// <summary>
    /// 父级目录索引号
    /// </summary>
    public String ParentId;
    /// <summary>
    /// 父级路径
    /// </summary>
    public String FamilyPath;
    /// <summary>
    /// 分类索引号
    /// </summary>
    public String CategoryId;
    /// <summary>
    /// 标题
    /// </summary>
    public String Title;
    /** 分级Tilte */
    public String CategoryTitle;
    /** 价格 */
    public Double Price;

    public String CreateDate;
    public Integer Sort;

	/**
	 * 获得数据集
	 * @param data
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 * @date 2012-11-2 下午2:06:30
	 */
	public ArrayList<Menu> getListInfo(String data) throws XmlPullParserException, IOException {
		InputStream inStream = new ByteArrayInputStream(data.getBytes());

		ArrayList<Menu> munus = null;
		Menu menu = null;
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(inStream, "UTF-8");
		int eventType = parser.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:// 判断当前事件是否是文档开始事件
				munus = new ArrayList<Menu>();// 初始化集合
				break;
			case XmlPullParser.START_TAG:// 判断当前事件是否是标签元素开始事件
				if ("Item".equals(parser.getName())) {// 判断开始标签元素
					menu = new Menu();
					// area.setId(Integer.parseInt(parser.getAttributeValue(0)));//得到book标签的属性值，并设置book的id
					// <book id="1">
				}
				if (menu != null) {
					if ("CategoryId".equals(parser.getName())) {// 判断开始标签元素
						menu.CategoryId = parser.nextText().trim();
					} else if ("Title".equals(parser.getName())) {
						menu.Title = parser.nextText().trim();
					} else if ("ParentId".equals(parser.getName())) {
						menu.ParentId = parser.nextText().trim();
					} else if ("FamilyPath".equals(parser.getName())) {
						menu.FamilyPath = parser.nextText().trim();
					} else if ("CategoryTitle".equals(parser.getName())) {
						menu.CategoryTitle = parser.nextText().trim();
					} else if ("Price".equals(parser.getName())) {
						menu.Price = Double.parseDouble(parser.nextText().trim());
					} else if ("Sort".equals(parser.getName())) {
						menu.Sort = Integer.parseInt(parser.nextText().trim());
					} else if ("CreateDate".equals(parser.getName())) {
						menu.CreateDate = parser.nextText().trim();
					}
				}
				break;
			case XmlPullParser.END_TAG:// 判断当前事件是否是标签元素结束事件
				if ("Item".equals(parser.getName())) {// 判断结束标签元素
					munus.add(menu);// 添加到集合
					menu = null;
				}
				break;
			}
			eventType = parser.next();
		}
		inStream.close();
		return munus;
	}

	/**
	 * 进行排序分组
	 * @param data
	 * @return
	 * @date 2012-11-13 下午4:31:45
	 */
	public ArrayList<ArrayList<Menu>> getSortInfo(List<Menu> data){
//		List<String> s = new ArrayList<String>();

		// 排序
		ComparatorUser comparator = new ComparatorUser();
		Collections.sort(data, comparator);

		// 分组
		ArrayList<ArrayList<Menu>> lldata = new ArrayList<ArrayList<Menu>>();
		int id = -1;
		ArrayList<Menu> ldata = new ArrayList<Menu>();

		for (Menu m : data) {
			// System.out.println(m);
			if (id != m.Sort) {
				ldata = new ArrayList<Menu>();
//				s.add(m.CategoryTitle);
				lldata.add(ldata);
			}
			ldata.add(m);
			id = m.Sort;
		}
		for (ArrayList<Menu> item : lldata) {
			ComparatorDate cd = new ComparatorDate();
			Collections.sort(item, cd);
		}

		return lldata;
	}
}

/**
 * 进行排序
 * @author chen
 * @date 2012-11-13 下午4:27:36
 */
class ComparatorUser implements Comparator<Menu> {
	@Override
	public int compare(Menu object1, Menu object2) {
		return object2.Sort.compareTo(object1.Sort);
	}
}
/**
 * 进行排序
 * @author chen
 * @date 2012-11-13 下午4:27:36
 */
class ComparatorDate implements Comparator<Menu> {
	@Override
	public int compare(Menu object1, Menu object2) {
		return object2.CreateDate.compareTo(object1.CreateDate);
	}
}
