package com.cxt.esl.label.activity;

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
import com.cxt.esl.label.adapter.LabelAdapter;
import com.cxt.esl.label.dao.LabelDao;
import com.cxt.esl.label.domain.Label;
import com.cxt.esl.label.listener.LabelItemClickListener;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class LabelMainActivity extends Activity{
	
	private List<Label> labelList;
	private ESLDatebaseHelper helper;
	private LabelDao labelDao;
	private LabelAdapter adapter;
	
	private void init(){
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			labelDao =  new LabelDao(helper.getLabelDao(), helper.getPatternDao(), helper.getModelDao(), helper.getGoodDao());
			labelList = labelDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		setContentView(R.layout.label_main);
		
		Button searchBtn = (Button) findViewById(R.id.label_search_btn);
		searchBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LabelMainActivity.this, LabelActivity.class);
				startActivity(intent);
			}
		});
		adapter = new LabelAdapter(LabelMainActivity.this,
				R.layout.label_item, labelList);
		ListView listView = (ListView) findViewById(R.id.label_list);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new LabelItemClickListener(this, labelList, labelDao,adapter));
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		try {
			labelList = labelDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		adapter = new LabelAdapter(LabelMainActivity.this,
				R.layout.label_item, labelList);
		ListView listView = (ListView) findViewById(R.id.label_list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new LabelItemClickListener(this, labelList, labelDao,adapter));
	}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		helper.close();
	}
	
}