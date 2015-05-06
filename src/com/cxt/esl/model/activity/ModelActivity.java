package com.cxt.esl.model.activity;

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
import com.cxt.esl.model.adapter.ModelAdapter;
import com.cxt.esl.model.dao.ModelDao;
import com.cxt.esl.model.domain.Model;
import com.cxt.esl.model.listener.ModelItemClickListener;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class ModelActivity extends Activity {
	
	private List<Model> modelList;
	private ESLDatebaseHelper helper;
	private ModelDao modelDao;
	private ModelAdapter adapter;
	
	private void init(){
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			modelDao = new ModelDao ( helper.getModelDao());
			modelList = modelDao.queryAll();
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
		setContentView(R.layout.model);
		
		Button addBtn = (Button) findViewById(R.id.model_add_btn);
		
		
		addBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ModelActivity.this, ModelAddActivity.class);
				startActivity(intent);
			}
		});
		
		adapter = new ModelAdapter(ModelActivity.this,
				R.layout.model_item, modelList);
		ListView listView = (ListView) findViewById(R.id.model_list);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new ModelItemClickListener(this, modelList, modelDao, adapter));
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
				try {
					modelList = modelDao.queryAll();
				} catch (java.sql.SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		adapter = new ModelAdapter(ModelActivity.this,
				R.layout.model_item, modelList);
		ListView listView = (ListView) findViewById(R.id.model_list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new ModelItemClickListener(this, modelList, modelDao, adapter));
	}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		helper.close();
	}

	
}