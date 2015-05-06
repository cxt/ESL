package com.cxt.esl.label.activity;


import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
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
import com.cxt.esl.label.adapter.LabelAdapter;
import com.cxt.esl.label.dao.LabelDao;
import com.cxt.esl.label.domain.Label;
import com.cxt.esl.label.listener.LabelItemLongClickListener;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class LabelActivity extends Activity{
	
	private List<Label> labelList;
	private ESLDatebaseHelper helper;
	private LabelDao labelDao;
	private int workStaPos;
	private LabelAdapter adapter;
	
	private void init(){
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			labelDao = new LabelDao ( helper.getLabelDao());
			labelList = labelDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		setContentView(R.layout.label);
		
		Button searchBtn = (Button) findViewById(R.id.label_search_btn);
		Button resetBtn = (Button) findViewById(R.id.label_reset_btn);
		Button addBtn = (Button) findViewById(R.id.label_add_btn);
		
		final Spinner workStaSpin = (Spinner) findViewById(R.id.esl_status_spinner);
		String[] workStaStr = {"无","未知异常","初始","正常","生成图片","下发图片没feedback","下发图片有feedback","esl不在线"};
		ArrayAdapter<String> workStaAda = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, workStaStr);
		workStaSpin.setAdapter(workStaAda);
		workStaSpin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				workStaPos = position;
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		
		});
		
		final EditText eslIdView = (EditText) findViewById(R.id.e_esl_id);
		
		searchBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					String eslIdStr = eslIdView.getText().toString().trim();
					long eslId = eslIdStr.matches("^\\d{1,20}$") ? Integer.valueOf(eslIdStr) : -1;
					labelList = labelDao.queryByEslIdOrWorkStatus(eslId, workStaPos - 2);
					adapter = new LabelAdapter(LabelActivity.this,
							R.layout.label_item, labelList);
					ListView listView = (ListView) findViewById(R.id.label_list);
					listView.setAdapter(adapter);
					listView.setOnItemLongClickListener(new LabelItemLongClickListener(LabelActivity.this, labelList, labelDao,adapter));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		resetBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				eslIdView.setText("");
				workStaSpin.setSelection(0);
			}
		});
		
		addBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LabelActivity.this, LabelAddActivity.class);
				startActivity(intent);
			}
		});
		
		adapter = new LabelAdapter(LabelActivity.this,
				R.layout.label_item, labelList);
		ListView listView = (ListView) findViewById(R.id.label_list);
		listView.setAdapter(adapter);
		
		listView.setOnItemLongClickListener(new LabelItemLongClickListener(this, labelList, labelDao,adapter));
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		try {
			labelList = labelDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		adapter = new LabelAdapter(LabelActivity.this,
				R.layout.label_item, labelList);
		ListView listView = (ListView) findViewById(R.id.label_list);
		listView.setAdapter(adapter);
		listView.setOnItemLongClickListener(new LabelItemLongClickListener(this, labelList, labelDao,adapter));
	}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		helper.close();
	}
	
}