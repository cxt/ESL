package com.cxt.esl.config.activity;

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
import com.cxt.esl.config.adapter.ConfigAdapter;
import com.cxt.esl.config.dao.ConfigDao;
import com.cxt.esl.config.domain.Config;
import com.cxt.esl.config.listener.ConfigItemClickListener;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class ConfigMainActivity extends Activity {
	
	private List<Config> configList;
	private ESLDatebaseHelper helper;
	private ConfigDao configDao;
	private ConfigAdapter adapter;
	
	
	private void init(){
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			configDao = new ConfigDao ( helper.getConfigDao());
			configList = configDao.queryAll();
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
		setContentView(R.layout.config_main);
		
		Button btn = (Button) findViewById(R.id.config_search_btn);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ConfigMainActivity.this, ConfigActivity.class);
				startActivity(intent);
			}
		});
		
		adapter = new ConfigAdapter(ConfigMainActivity.this,
				R.layout.config_item, configList);
		ListView listView = (ListView) findViewById(R.id.config_list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new ConfigItemClickListener(ConfigMainActivity.this, configList, configDao, adapter));
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
				try {
					configList = configDao.queryAll();
				} catch (java.sql.SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				adapter = new ConfigAdapter(ConfigMainActivity.this,
						R.layout.config_item, configList);
				ListView listView = (ListView) findViewById(R.id.config_list);
				listView.setAdapter(adapter);
				listView.setOnItemClickListener(new ConfigItemClickListener(ConfigMainActivity.this, configList, configDao, adapter));
			}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		helper.close();
	}

	
}