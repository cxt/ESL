package com.cxt.esl.pattern.activity;

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
import com.cxt.esl.pattern.dao.PatternDao;
import com.cxt.esl.pattern.domain.Pattern;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class PatternAddActivity extends Activity{

	private ESLDatebaseHelper helper;
	private PatternDao patternDao;
	
	
	private void init(){
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			patternDao = new PatternDao ( helper.getPatternDao());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pattern_add);
		init();
		final EditText etPatterName = (EditText) findViewById(R.id.e_pattern_name);
		final EditText etOrderNum = (EditText) findViewById(R.id.e_order_num);
		final EditText etCode= (EditText) findViewById(R.id.e_code);
		final EditText etReadme = (EditText) findViewById(R.id.e_readme);
		
		final Spinner spinModel = (Spinner) findViewById(R.id.spin_model);
		
		Button btnAdd = (Button) findViewById(R.id.pattern_add_sure_btn);
		Button btnCancel = (Button) findViewById(R.id.pattern_add_cancel_btn);
		
		btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String strPatternName = etPatterName.getText().toString().trim();
				String strOrderNum = etOrderNum.getText().toString().trim();
				String strCode = etCode.getText().toString().trim();
				String strReadme = etReadme.getText().toString().trim();
				
				if(strPatternName.length() == 0){
					Toast.makeText(PatternAddActivity.this, "模板名称填写有误!", Toast.LENGTH_SHORT).show();
					return ;
				}
				
				if(!strOrderNum.matches("^\\d{0,5}$")){
					Toast.makeText(PatternAddActivity.this, "排列号填写有误!", Toast.LENGTH_LONG).show();
					return;
				}	
				
				int orderNum = 0;
				if(strOrderNum.length() > 0)
					orderNum = Integer.valueOf(strOrderNum);
				Pattern p = new Pattern();
				p.setCode(strCode);
				p.setOrderNum(orderNum);
				p.setPatternName(strPatternName);
				p.setReadme(strReadme);
				
				try {
					patternDao.insert(p);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				Toast.makeText(PatternAddActivity.this, "添加成功!", Toast.LENGTH_LONG).show();
				
				
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

