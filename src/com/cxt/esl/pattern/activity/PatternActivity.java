package com.cxt.esl.pattern.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.cxt.esl.R;
import com.cxt.esl.pattern.adapter.PatternAdapter;
import com.cxt.esl.pattern.dao.PatternDao;
import com.cxt.esl.pattern.domain.Pattern;
import com.cxt.esl.pattern.listener.PatternItemClickListener;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class PatternActivity extends Activity {
	
	private List<Pattern> patternList;
	private ESLDatebaseHelper helper;
	private PatternDao patternDao;
	private PatternAdapter adapter;
	private ListView listView;
	
	private void init(){
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			patternDao = new PatternDao ( helper.getPatternDao());
			patternList = patternDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		setContentView(R.layout.pattern);
		
		Button addBtn = (Button) findViewById(R.id.pattern_add_btn);
		
		
		addBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PatternActivity.this, PatternAddActivity.class);
				startActivity(intent);
			}
		});
		
		adapter = new PatternAdapter(PatternActivity.this,
				R.layout.pattern_item, patternList);
		listView = (ListView) findViewById(R.id.pattern_list);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new PatternItemClickListener(this, patternList, patternDao, adapter));
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		init();
		setContentView(R.layout.pattern);
		
		Button addBtn = (Button) findViewById(R.id.pattern_add_btn);
		
		
		addBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PatternActivity.this, PatternAddActivity.class);
				startActivity(intent);
			}
		});
		
		adapter = new PatternAdapter(PatternActivity.this,
				R.layout.pattern_item, patternList);
		listView = (ListView) findViewById(R.id.pattern_list);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new PatternItemClickListener(this, patternList, patternDao, adapter));
	}
	
	

	
	
}