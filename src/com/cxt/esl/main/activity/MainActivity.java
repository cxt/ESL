package com.cxt.esl.main.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import com.cxt.esl.R;
import com.cxt.esl.bind.activity.QuickBindActivity;
import com.cxt.esl.config.activity.ConfigMainActivity;
import com.cxt.esl.good.activity.GoodMainActivity;
import com.cxt.esl.good.activity.GoodReplaceActivity;
import com.cxt.esl.good.activity.GoodUpdateHistoryActivity;
import com.cxt.esl.kind.activity.KindMainActivity;
import com.cxt.esl.label.activity.LabelMainActivity;
import com.cxt.esl.model.activity.ModelActivity;
import com.cxt.esl.pattern.activity.PatternActivity;
import com.cxt.esl.sale.activity.OrderActivity;
import com.cxt.esl.sale.activity.SaleActivity;
import com.cxt.esl.user.activity.UserActivity;
import com.cxt.esl.util.db.ESLDatebaseHelper;

/*
 * 创建一个Activity，继承ExpandableListAcitivty
 */
public class MainActivity extends ExpandableListActivity {
	private ESLDatebaseHelper helper;
	protected boolean isQuit;
	/** Called when the activity is first created. */
	
	private void init(){
		helper = ESLDatebaseHelper.getHelper(this.getApplicationContext());
		helper.getWritableDatabase();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
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
		group4.put("group", "销售管理");
		groups.add(group4);
		Map<String, String> group5 = new HashMap<String, String>();
		group5.put("group", "监控管理");
		groups.add(group5);
		
		List<Map<String, String>> child1 = new ArrayList<Map<String, String>>();
		Map<String, String> child1Data1 = new HashMap<String, String>();
		child1Data1.put("child", "用户定义");
		child1.add(child1Data1);
		Map<String, String> child1Data2 = new HashMap<String, String>();
		child1Data2.put("child", "参数配置");
		child1.add(child1Data2);
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
		child3Data4.put("child", "商品替换");
		child3.add(child3Data4);
		childs.add(child3);
		
		List<Map<String, String>> child4 = new ArrayList<Map<String, String>>();
		Map<String, String> child4Data1 = new HashMap<String, String>();
		child4Data1.put("child", "商品销售");
		child4.add(child4Data1);
		Map<String, String> child4Data2 = new HashMap<String, String>();
		child4Data2.put("child", "订单管理");
		child4.add(child4Data2);
		childs.add(child4);
		
		List<Map<String, String>> child5 = new ArrayList<Map<String, String>>();
		Map<String, String> child5Data2 = new HashMap<String, String>();
		child5Data2.put("child", "商品修改历史");
		child5.add(child5Data2);
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
		this.getExpandableListView().expandGroup(1);
		this.getExpandableListView().expandGroup(2);
		this.getExpandableListView().expandGroup(3);
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		Log.d("Test", groupPosition + " " + childPosition + " " + id);
		if(groupPosition == 0){
			if(childPosition == 0){
				Intent intent = new Intent(MainActivity.this, UserActivity.class);
				startActivity(intent);
				return true;
			}
			else if(childPosition == 1){
				Intent intent = new Intent(MainActivity.this, ConfigMainActivity.class);
				startActivity(intent);
				return true;
			}
		}
		if(groupPosition == 1){
			if(childPosition == 0){
				Intent intent = new Intent(MainActivity.this, LabelMainActivity.class);
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
				Intent intent = new Intent(MainActivity.this, GoodMainActivity.class);
				startActivity(intent);
				return true;
			}else if(childPosition == 1){
				Intent intent = new Intent(MainActivity.this, KindMainActivity.class);
				startActivity(intent);
				return true;
			}
			else if(childPosition == 2){
				Intent intent = new Intent(MainActivity.this, QuickBindActivity.class);
				startActivity(intent);
				return true;
			}else {
				Intent intent = new Intent(MainActivity.this, GoodReplaceActivity.class);
				startActivity(intent);
				return true;
			}
		}
		else if(groupPosition == 3){
			if(childPosition == 0){
				Intent intent = new Intent(MainActivity.this, SaleActivity.class);
				startActivity(intent);
				return true;
			}
			else if(childPosition == 1){
				Intent intent = new Intent(MainActivity.this, OrderActivity.class);
				startActivity(intent);
				return true;
			}
		}
		else if(groupPosition == 4){
			 if(childPosition == 0){
				Intent intent = new Intent(MainActivity.this, GoodUpdateHistoryActivity.class);
				startActivity(intent);
				return true;
			}
		}
		return false;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		helper.close();
	}
	
	Handler mHandler = new Handler() {  
        @Override  
        public void handleMessage(Message msg) {  
            super.handleMessage(msg);  
            isQuit = false;  
        }  
    };  
  
    @Override  
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
        if (keyCode == KeyEvent.KEYCODE_BACK) {  
            if (!isQuit) {  
                isQuit = true;  
                Toast.makeText(getApplicationContext(), "再按一次返回登陆界面",  
                        Toast.LENGTH_SHORT).show();  
                // 利用handler延迟发送更改状态信息   
                mHandler.sendEmptyMessageDelayed(0, 2000);  
            } else {  
                finish();  
                System.exit(0);  
            }  
        }  
        return false;  
    }  

}