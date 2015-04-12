package com.cxt.esl.pattern.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import com.cxt.esl.R;
import com.cxt.esl.pattern.adapter.PatternListAdapter;
import com.cxt.esl.pattern.domain.Pattern;

/*
 * 创建一个Activity，继承ExpandableListAcitivty
 */
public class PatternActivity extends ExpandableListActivity {
	/** Called when the activity is first created. */
	SimpleExpandableListAdapter sela;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pattern);

		List<Map<String, Pattern>> groups = new ArrayList<Map<String, Pattern>>();
		List<List<Map<String, Pattern>>> childs = new ArrayList<List<Map<String, Pattern>>>();
		for(int i = 0; i < 100; i++){
			Map<String, Pattern> group1 = new HashMap<String, Pattern>();
			String code = "<table border=\'0\' cellpadding=\'0\' cellspacing=\'0\' width=\'172\' height=\'72\' >\n\t<tr>\n\t\t<td bgcolor=\'#FFFFFF\' width=\'172\' height=\'12\' bordercolor=\'#000000\' style=\'border-bottom-style: solid; border-bottom-width: 1px\'>\n\t\t<span style=\'font-size: 1pt\'>　</span></td>\n\t</tr>\n\t<tr>\n\t\t<td bgcolor=\'#FFFFFF\' width=\'172\' height=\'12\' bordercolor=\'#000000\' style=\'border-bottom-style: solid; border-bottom-width: 1px\'>　</td>\n\t</tr>\n\t<tr>\n\t\t<td bgcolor=\'#FFFFFF\' width=\'172\' height=\'12\' bordercolor=\'#000000\' style=\'border-bottom-style: solid; border-bottom-width: 1px\'>　</td>\n\t</tr>\n\t<tr>\n\t\t<td bgcolor=\'#FFFFFF\' width=\'172\' height=\'12\' bordercolor=\'#000000\' style=\'border-bottom-style: solid; border-bottom-width: 1px\'>　</td>\n\t</tr>\n\t<tr>\n\t\t<td bgcolor=\'#FFFFFF\' width=\'172\' height=\'12\' bordercolor=\'#000000\' style=\'border-bottom-style: solid; border-bottom-width: 1px\'>　</td>\n\t</tr>\n\t<tr>\n\t\t<td bgcolor=\'#FFFFFF\' width=\'172\' height=\'12\' bordercolor=\'#000000\' style=\'border-bottom-style: solid; border-bottom-width: 1px\'>　</td>\n\t</tr>\n</table>";
			Pattern p = new Pattern(i, "2.1寸白板", "2.1寸白板", "ble2.1w", code);
			group1.put("group", p);
			groups.add(group1);
			
			List<Map<String, Pattern>> child1 = new ArrayList<Map<String, Pattern>>();
			Map<String, Pattern> child1Data1 = new HashMap<String, Pattern>();
			child1Data1.put("child", p);
			Map<String, Pattern> child1Data2 = new HashMap<String, Pattern>();
			child1Data2.put("child", p);
			Map<String, Pattern> child1Data3 = new HashMap<String, Pattern>();
			child1Data3.put("child", p);
			child1.add(child1Data1);
			child1.add(child1Data2);
			child1.add(child1Data3);
			childs.add(child1);
		}
		
		

		
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
		sela = new PatternListAdapter(
				this, groups, R.layout.pattern_group, new String[] { "group" },
				new int[] { R.id.patternGroupTo }, childs, R.layout.pattern_child,
				new String[] { "child" }, new int[] { R.id.patternChildTo });
		// 将SimpleExpandableListAdapter对象设置给当前的ExpandableListActivity
		setListAdapter(sela);
		
		ExpandableListView expandableListView = this.getExpandableListView();
		Button addBtn = (Button) this.findViewById(R.id.pattern_add_btn);
		addBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PatternActivity.this, PatternAddActivity.class);
				PatternActivity.this.startActivity(intent);
			}
		});
	
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
        
	}
	
	
}