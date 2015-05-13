package com.cxt.esl.config.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.cxt.esl.R;
import com.cxt.esl.config.adapter.ConfigAdapter;
import com.cxt.esl.config.dao.ConfigDao;
import com.cxt.esl.config.domain.Config;
import com.cxt.esl.config.listener.ConfigItemClickListener;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class ConfigActivity extends Activity {
	
	private List<Config> configList;
	private ESLDatebaseHelper helper;
	private ConfigDao configDao;
	private ConfigAdapter adapter;
	
	private int ctypePos;
	
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
		setContentView(R.layout.config);
		
		final Spinner spinCtype = (Spinner) findViewById(R.id.spin_ctype);
		String[] staArr = {"系统配置","同步pos"};
		ArrayAdapter<String> workStaAda = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, staArr);
		spinCtype.setAdapter(workStaAda);
		spinCtype.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				ctypePos = position;
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		
		});
		
		final EditText etCkey = (EditText) findViewById(R.id.e_ckey);
		
		Button searchBtn = (Button) findViewById(R.id.config_search_btn);
		Button resetBtn = (Button) findViewById(R.id.config_reset_btn);
		Button addBtn = (Button) findViewById(R.id.config_add_btn);
		
		searchBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					String ckey = etCkey.getText().toString().trim();
					configList = configDao.queryForCtypeOrCkey(ctypePos, ckey);
					adapter = new ConfigAdapter(ConfigActivity.this,
							R.layout.config_item, configList);
					ListView listView = (ListView) findViewById(R.id.config_list);
					listView.setAdapter(adapter);
					listView.setOnItemClickListener(new ConfigItemClickListener(ConfigActivity.this, configList, configDao, adapter));
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
				spinCtype.setSelection(0);
				etCkey.setText("");
				
			}
		});
		
		
		addBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ConfigActivity.this, ConfigAddActivity.class);
				startActivity(intent);
			}
		});
		
		adapter = new ConfigAdapter(ConfigActivity.this,
				R.layout.config_item, configList);
		ListView listView = (ListView) findViewById(R.id.config_list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new ConfigItemClickListener(ConfigActivity.this, configList, configDao, adapter));
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
				adapter = new ConfigAdapter(ConfigActivity.this,
						R.layout.config_item, configList);
				ListView listView = (ListView) findViewById(R.id.config_list);
				listView.setAdapter(adapter);
				listView.setOnItemClickListener(new ConfigItemClickListener(ConfigActivity.this, configList, configDao, adapter));
			}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		helper.close();
	}

	
}