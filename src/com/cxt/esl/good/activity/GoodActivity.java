package com.cxt.esl.good.activity;

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
import com.cxt.esl.good.adapter.GoodAdapter;
import com.cxt.esl.good.dao.GoodDao;
import com.cxt.esl.good.domain.Good;
import com.cxt.esl.good.listener.GoodItemClickListener;
import com.cxt.esl.kind.dao.KindDao;
import com.cxt.esl.kind.domain.Kind;
import com.cxt.esl.util.arrayAdapter.KindArrayAdapter;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class GoodActivity extends Activity{
	
	private List<Good> goodList;
	private ESLDatebaseHelper helper;
	private GoodDao goodDao;
	
	private KindDao kindDao;
	private List<Kind> kindList;
	private Kind kind;
	
 	private int staPos;
	private GoodAdapter adapter;
	
	private void init(){
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			goodDao = new GoodDao ( helper.getGoodDao());
			goodList = goodDao.queryAll();
			
			kindDao = new KindDao(helper.getKindDao());
			kindList = kindDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		setContentView(R.layout.good);
		
		Button searchBtn = (Button) findViewById(R.id.good_search_btn);
		Button resetBtn = (Button) findViewById(R.id.good_reset_btn);
		Button addBtn = (Button) findViewById(R.id.good_add_btn);
		
		final Spinner staSpin = (Spinner) findViewById(R.id.spin_status);
		final Spinner kindSpin = (Spinner) findViewById(R.id.spin_kind);
		String[] workStaStr = {"无","正常","更新"};
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
		kindSpin.setAdapter(new KindArrayAdapter(this, kindList));
		kindSpin.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				kind = kindList.get(position);
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
					goodList = goodDao.queryForBarCodeOrPosNameOrStatusOrKindId(barCode, posName, staPos-1, kind==null?-1:kind.getKindId());;
					adapter = new GoodAdapter(GoodActivity.this,
							R.layout.good_item, goodList);
					ListView listView = (ListView) findViewById(R.id.good_list);
					listView.setAdapter(adapter);
					listView.setOnItemClickListener(new GoodItemClickListener(GoodActivity.this, goodList, goodDao, adapter));
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
		
		addBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(GoodActivity.this, GoodAddActivity.class);
				startActivity(intent);
			}
		});
		
		adapter = new GoodAdapter(GoodActivity.this,
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
		adapter = new GoodAdapter(GoodActivity.this,
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