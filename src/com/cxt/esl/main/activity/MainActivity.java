package com.cxt.esl.main.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import com.cxt.esl.R;
import com.cxt.esl.good.activity.GoodActivity;
import com.cxt.esl.kind.activity.KindActivity;
import com.cxt.esl.label.activity.LabelActivity;
import com.cxt.esl.model.activity.ModelActivity;
import com.cxt.esl.pattern.activity.PatternActivity;

/*
 * 创建一个Activity，继承ExpandableListAcitivty
 */
public class MainActivity extends ExpandableListActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		List<Map<String, String>> groups = new ArrayList<Map<String, String>>();
		List<List<Map<String, String>>> childs = new ArrayList<List<Map<String, String>>>();

		Map<String, String> group1 = new HashMap<String, String>();
		group1.put("group", "系统设置");
		groups.add(group1);
		Map<String, String> group2 = new HashMap<String, String>();
		group2.put("group", "标签管理");
		groups.add(group2);
		Map<String, String> group3 = new HashMap<String, String>();
		group3.put("group", "商品管理");
		groups.add(group3);
		Map<String, String> group4 = new HashMap<String, String>();
		group4.put("group", "AP 管理");
		groups.add(group4);
		Map<String, String> group5 = new HashMap<String, String>();
		group5.put("group", "监控管理");
		groups.add(group5);
		
		List<Map<String, String>> child1 = new ArrayList<Map<String, String>>();
		Map<String, String> child1Data1 = new HashMap<String, String>();
		child1Data1.put("child", "用户定义");
		child1.add(child1Data1);
		Map<String, String> child1Data2 = new HashMap<String, String>();
		child1Data2.put("child", "角色定义");
		child1.add(child1Data2);
		Map<String, String> child1Data3 = new HashMap<String, String>();
		child1Data3.put("child", "模块定义");
		child1.add(child1Data3);
		Map<String, String> child1Data4 = new HashMap<String, String>();
		child1Data4.put("child", "功能定义");
		child1.add(child1Data4);
		Map<String, String> child1Data5 = new HashMap<String, String>();
		child1Data5.put("child", "权限控制");
		child1.add(child1Data5);
		Map<String, String> child1Data6 = new HashMap<String, String>();
		child1Data6.put("child", "访问控制");
		child1.add(child1Data6);
		Map<String, String> child1Data7 = new HashMap<String, String>();
		child1Data7.put("child", "日志开关");
		child1.add(child1Data7);
		Map<String, String> child1Data8 = new HashMap<String, String>();
		child1Data8.put("child", "手动pos同步开关");
		child1.add(child1Data8);
		Map<String, String> child1Data9 = new HashMap<String, String>();
		child1Data9.put("child", "自动pos同步开关");
		child1.add(child1Data9);
		Map<String, String> child1Data10 = new HashMap<String, String>();
		child1Data10.put("child", "参数配置");
		child1.add(child1Data10);
		Map<String, String> child1Data11 = new HashMap<String, String>();
		child1Data11.put("child", "价格更新时间");
		child1.add(child1Data11);
		childs.add(child1);
		
		List<Map<String, String>> child2 = new ArrayList<Map<String, String>>();
		Map<String, String> child2Data1 = new HashMap<String, String>();
		child2Data1.put("child", "标签列表");
		child2.add(child2Data1);
		Map<String, String> child2Data2 = new HashMap<String, String>();
		child2Data2.put("child", "模板列表");
		child2.add(child2Data2);
		Map<String, String> child2Data3 = new HashMap<String, String>();
		child2Data3.put("child", "型号列表");
		child2.add(child2Data3);
		childs.add(child2);
		
		
		List<Map<String, String>> child3 = new ArrayList<Map<String, String>>();
		Map<String, String> child3Data1 = new HashMap<String, String>();
		child3Data1.put("child", "商品列表");
		child3.add(child3Data1);
		Map<String, String> child3Data2 = new HashMap<String, String>();
		child3Data2.put("child", "类别管理");
		child3.add(child3Data2);
		Map<String, String> child3Data3 = new HashMap<String, String>();
		child3Data3.put("child", "快速绑定");
		child3.add(child3Data3);
		Map<String, String> child3Data4 = new HashMap<String, String>();
		child3Data4.put("child", "pos快速绑定");
		child3.add(child3Data4);
		Map<String, String> child3Data5 = new HashMap<String, String>();
		child3Data5.put("child", "商品替换");
		child3.add(child3Data5);
		Map<String, String> child3Data6 = new HashMap<String, String>();
		child3Data6.put("child", "商品编码转换");
		child3.add(child3Data6);
		Map<String, String> child3Data7 = new HashMap<String, String>();
		child3Data7.put("child", "商品导入历史");
		child3.add(child3Data7);
		childs.add(child3);
		
		List<Map<String, String>> child4 = new ArrayList<Map<String, String>>();
		Map<String, String> child4Data1 = new HashMap<String, String>();
		child4Data1.put("child", "AP管理");
		child4.add(child4Data1);
		childs.add(child4);
		
		List<Map<String, String>> child5 = new ArrayList<Map<String, String>>();
		Map<String, String> child5Data1 = new HashMap<String, String>();
		child5Data1.put("child", "成功显示的标签");
		child5.add(child5Data1);
		Map<String, String> child5Data2 = new HashMap<String, String>();
		child5Data2.put("child", "商品修改历史");
		child5.add(child5Data2);
		Map<String, String> child5Data3 = new HashMap<String, String>();
		child5Data3.put("child", "设备信息监控");
		child5.add(child5Data3);
		Map<String, String> child5Data4 = new HashMap<String, String>();
		child5Data4.put("child", "数据更新历史");
		child5.add(child5Data4);
		Map<String, String> child5Data5 = new HashMap<String, String>();
		child5Data5.put("child", "图片生成列表");
		child5.add(child5Data5);
		Map<String, String> child5Data6 = new HashMap<String, String>();
		child5Data6.put("child", "ESL休眠监控");
		child5.add(child5Data6);
		childs.add(child5);
		

		// 生成一个SimpleExpandableListAdapter对象
		// 1.context
		// 2.一级条目的数据
		// 3.用来设置一级条目样式的布局文件
		// 4.指定一级条目数据的key
		// 5.指定一级条目数据显示控件的id
		// 6.指定二级条目的数据
		// 7.用来设置二级条目样式的布局文件
		// 8.指定二级条目数据的key
		// 9.指定二级条目数据显示控件的id
		SimpleExpandableListAdapter sela = new SimpleExpandableListAdapter(
				this, groups, R.layout.main_group, new String[] { "group" },
				new int[] { R.id.mainGroupTo }, childs, R.layout.main_child,
				new String[] { "child" }, new int[] { R.id.mianChildTo });
		// 将SimpleExpandableListAdapter对象设置给当前的ExpandableListActivity
		setListAdapter(sela);
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		Log.d("Test", groupPosition + " " + childPosition + " " + id);
		if(groupPosition == 1){
			if(childPosition == 0){
				Intent intent = new Intent(MainActivity.this, LabelActivity.class);
				startActivity(intent);
				return true;
			}
			else if(childPosition == 1){
				Intent intent = new Intent(MainActivity.this, PatternActivity.class);
				startActivity(intent);
				return true;
			}
			else if(childPosition == 2){
				Intent intent = new Intent(MainActivity.this, ModelActivity.class);
				startActivity(intent);
				return true;
			}
		}
		else if(groupPosition == 2){
			if(childPosition == 0){
				Intent intent = new Intent(MainActivity.this, GoodActivity.class);
				startActivity(intent);
				return true;
			}else if(childPosition == 1){
				Intent intent = new Intent(MainActivity.this, KindActivity.class);
				startActivity(intent);
				return true;
			}
		}
		return false;
	}
}