package com.cxt.esl.good.activity;

import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.cxt.esl.R;
import com.cxt.esl.good.adapter.GoodAdapter;
import com.cxt.esl.good.dao.GoodDao;
import com.cxt.esl.good.domain.Good;
import com.cxt.esl.good.listener.GoodItemClickListener;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class GoodMainActivity extends Activity{
	
	private List<Good> goodList;
	private ESLDatebaseHelper helper;
	private GoodDao goodDao;
	
	
	private GoodAdapter adapter;
	
	private void init(){
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			goodDao = new GoodDao ( helper.getGoodDao());
			goodList = goodDao.queryAll();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		setContentView(R.layout.good_main);
		
		Button btn = (Button) findViewById(R.id.good_search_btn);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(GoodMainActivity.this, GoodActivity.class);
				startActivity(intent);
			}
		});
		adapter = new GoodAdapter(GoodMainActivity.this,
				R.layout.good_item, goodList);
		ListView listView = (ListView) findViewById(R.id.good_list);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new GoodItemClickListener(this, goodList, goodDao, adapter));
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		try {
			goodList = goodDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		adapter = new GoodAdapter(GoodMainActivity.this,
				R.layout.good_item, goodList);
		ListView listView = (ListView) findViewById(R.id.good_list);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new GoodItemClickListener(this, goodList, goodDao, adapter));
	}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		helper.close();
	}
	
}