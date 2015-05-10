package com.cxt.esl.good.activity;

import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
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
import com.cxt.esl.good.adapter.GoodUpdateHistoryAdapter;
import com.cxt.esl.good.dao.GoodUpdateHistoryDao;
import com.cxt.esl.good.domain.GoodUpdateHistory;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class GoodUpdateHistoryActivity extends Activity{
	
	private List<GoodUpdateHistory> guhList;
	private ESLDatebaseHelper helper;
	private GoodUpdateHistoryDao guhDao;
	
	
 	private int staPos;
	private GoodUpdateHistoryAdapter adapter;
	
	private void init(){
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			guhDao = new GoodUpdateHistoryDao ( helper.getGoodUpdateHistoryDao());
			guhList = guhDao.queryAll();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.good_his);
		init();
		
		Button searchBtn = (Button) findViewById(R.id.good_his_search_btn);
		Button resetBtn = (Button) findViewById(R.id.good_his_reset_btn);
		
		final Spinner staSpin = (Spinner) findViewById(R.id.spin_status);
		String[] workStaStr = {"未生成","已生成","已作废"};
		ArrayAdapter<String> workStaAda = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, workStaStr);
		staSpin.setAdapter(workStaAda);
		staSpin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				staPos = position;
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		
		});
		
		final EditText barCodeView = (EditText) findViewById(R.id.e_bar_code);
		final EditText posNameView = (EditText) findViewById(R.id.e_pos_name);
		
		searchBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					String barCode = barCodeView.getText().toString().trim();
					String posName = posNameView.getText().toString().trim();
					guhList = guhDao.queryForBarCodeOrPosNameOrStatus(barCode, posName, staPos);;
					adapter = new GoodUpdateHistoryAdapter(GoodUpdateHistoryActivity.this,
							R.layout.good_his_item, guhList);
					ListView listView = (ListView) findViewById(R.id.good_his_list);
					listView.setAdapter(adapter);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		resetBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				barCodeView.setText("");
				posNameView.setText("");
				staSpin.setSelection(0);
			}
		});
		
		
		adapter = new GoodUpdateHistoryAdapter(GoodUpdateHistoryActivity.this,
				R.layout.good_his_item, guhList);
		ListView listView = (ListView) findViewById(R.id.good_his_list);
		listView.setAdapter(adapter);
		
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		try {
			guhList = guhDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		adapter = new GoodUpdateHistoryAdapter(GoodUpdateHistoryActivity.this,
				R.layout.good_his_item, guhList);
		ListView listView = (ListView) findViewById(R.id.good_his_list);
		listView.setAdapter(adapter);}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		helper.close();
	}
	
}