package com.cxt.esl.kind.activity;

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
import com.cxt.esl.kind.adapter.KindAdapter;
import com.cxt.esl.kind.dao.KindDao;
import com.cxt.esl.kind.domain.Kind;
import com.cxt.esl.kind.listener.KindItemClickListener;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class KindMainActivity extends Activity {
	
	private List<Kind> kindList;
	private ESLDatebaseHelper helper;
	private KindDao kindDao;
	private KindAdapter adapter;
	
	private void init(){
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			kindDao = new KindDao ( helper.getKindDao());
			kindList = kindDao.queryAll();
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
		setContentView(R.layout.kind_main);
		Button btn = (Button) findViewById(R.id.kind_search_btn);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(KindMainActivity.this, KindActivity.class);
				startActivity(intent);
			}
		});
		
		adapter = new KindAdapter(KindMainActivity.this,
				R.layout.kind_item, kindList);
		ListView listView = (ListView) findViewById(R.id.kind_list);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new KindItemClickListener(this, kindList, kindDao, adapter));
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
				try {
					kindList = kindDao.queryAll();
				} catch (java.sql.SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				adapter = new KindAdapter(KindMainActivity.this,
						R.layout.kind_item, kindList);
				ListView listView = (ListView) findViewById(R.id.kind_list);
				listView.setAdapter(adapter);
				
				listView.setOnItemClickListener(new KindItemClickListener(this, kindList, kindDao, adapter));
			}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		helper.close();
	}

	
}