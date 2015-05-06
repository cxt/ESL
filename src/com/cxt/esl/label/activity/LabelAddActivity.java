package com.cxt.esl.label.activity;

import java.io.IOException;
import java.sql.SQLException;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cxt.esl.R;
import com.cxt.esl.label.dao.LabelDao;
import com.cxt.esl.label.domain.Label;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class LabelAddActivity extends Activity{
	
	private ESLDatebaseHelper helper;
	private LabelDao labelDao;
	
	
	private void init(){
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			labelDao = new LabelDao ( helper.getLabelDao());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.label_add);
		init();
		final EditText etEslId= (EditText) findViewById(R.id.e_esl_id);
		final EditText etSleepPeriod= (EditText) findViewById(R.id.e_sleep_peroid);
		final EditText etMycode= (EditText) findViewById(R.id.e_my_code);
		final EditText etMacId= (EditText) findViewById(R.id.e_mac_id);
		
		final Spinner spinGoods = (Spinner) findViewById(R.id.spin_goods);
		final Spinner spinPattern = (Spinner) findViewById(R.id.spin_pattern);
		final Spinner spinWorkSta = (Spinner) findViewById(R.id.spin_work_status);
		final Spinner spinModel = (Spinner) findViewById(R.id.spin_model);
		
		Button btnAdd = (Button) findViewById(R.id.label_add_sure_btn);
		Button btnCancel = (Button) findViewById(R.id.label_add_cancel_btn);
		
		btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String strEslId = etEslId.getText().toString().trim();
				String strSleepPeriod = etSleepPeriod.getText().toString().trim();
				String strMycode = etMycode.getText().toString().trim();
				String strMacId = etMacId.getText().toString().trim();
				if(!strEslId.matches("^\\d{1,20}$")){
					Toast.makeText(LabelAddActivity.this, "标签编号填写有误!", Toast.LENGTH_LONG).show();
					return;
				}
				if(!strSleepPeriod.matches("^\\d{1,5}$")){
					Toast.makeText(LabelAddActivity.this, "睡眠时长填写有误!", Toast.LENGTH_LONG).show();
					return;
				}
				if(!strMacId.matches("^([0-9a-fA-F]{2})(([0-9a-fA-F]{2}){5})$")){
					Toast.makeText(LabelAddActivity.this, "物理地址填写有误!", Toast.LENGTH_LONG).show();
					return;
				}
					
				long eslId = Integer.valueOf(strEslId);
				int sleepPeriod = Integer.valueOf(strSleepPeriod);
				
				Label label = new Label();
				label.setEslId(eslId);
				label.setSleepPeriod(sleepPeriod);
				label.setMycode(strMycode);
				label.setMacId(strMacId);
				
				try {
					helper.getLabelDao().create(label);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				Toast.makeText(LabelAddActivity.this, "添加成功!", Toast.LENGTH_LONG).show();
				
				
				// 返回上一个Activity
				Runtime runtime = Runtime.getRuntime();
				try {
					runtime.exec("input keyevent " + KeyEvent.KEYCODE_BACK);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		btnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 返回上一个Activity
				Runtime runtime = Runtime.getRuntime();
				try {
					runtime.exec("input keyevent " + KeyEvent.KEYCODE_BACK);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		helper.close();
	}
}
