package com.cxt.esl.kind.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.cxt.esl.R;
import com.cxt.esl.kind.adapter.KindAdapter;
import com.cxt.esl.kind.dao.KindDao;
import com.cxt.esl.kind.domain.Kind;
import com.cxt.esl.kind.listener.KindItemClickListener;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class KindActivity extends Activity {
	
	private List<Kind> kindList;
	private ESLDatebaseHelper helper;
	private KindDao kindDao;
	private KindAdapter adapter;
	
	private void init(){
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			kindDao = new KindDao ( helper.getKindDao());
			kindList = new ArrayList<Kind>();
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
		setContentView(R.layout.kind);
		
		final EditText kindNameView = (EditText) findViewById(R.id.e_kind_name);
		Button searchBtn = (Button) findViewById(R.id.kind_search_btn);
		Button resetBtn = (Button) findViewById(R.id.kind_reset_btn);
		Button addBtn = (Button) findViewById(R.id.kind_add_btn);
		
		searchBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					String kindName = kindNameView.getText().toString().trim();
					kindList = kindDao.queryForKindName(kindName);
					adapter = new KindAdapter(KindActivity.this,
							R.layout.kind_item, kindList);
					ListView listView = (ListView) findViewById(R.id.kind_list);
					listView.setAdapter(adapter);
					listView.setOnItemClickListener(new KindItemClickListener(KindActivity.this, kindList, kindDao, adapter));
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (java.sql.SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		resetBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				kindNameView.setText("");
				
			}
		});
		
		
		addBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(KindActivity.this, KindAddActivity.class);
				startActivity(intent);
			}
		});
		
		adapter = new KindAdapter(KindActivity.this,
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
				adapter = new KindAdapter(KindActivity.this,
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