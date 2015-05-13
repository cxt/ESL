package com.cxt.esl.config.activity;

import java.io.IOException;
import java.sql.SQLException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cxt.esl.R;
import com.cxt.esl.config.dao.ConfigDao;
import com.cxt.esl.config.domain.Config;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class ConfigAddActivity extends Activity{

	private ESLDatebaseHelper helper;
	private ConfigDao configDao;
	
	private EditText etCkey ;
	private EditText etCvalue;
	private EditText etNote;
	
	private Spinner spinCtype;
	
	private int ctypePos;
	
	
	private void init(){
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			configDao = new ConfigDao ( helper.getConfigDao());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		etCkey = (EditText) findViewById(R.id.e_ckey);
		etCvalue = (EditText) findViewById(R.id.e_cvalue);
		etNote = (EditText) findViewById(R.id.e_note);

		spinCtype = (Spinner) findViewById(R.id.spin_ctype);
		
		String[] staArr = {"系统配置","pos同步"};
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
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.config_add);
		init();
		
		
		Button btnAdd = (Button) findViewById(R.id.config_add_sure_btn);
		Button btnCancel = (Button) findViewById(R.id.config_add_cancel_btn);
		
		btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String ckey = etCkey.getText().toString().trim();
				String cvalue = etCvalue.getText().toString().trim();
				String note = etNote.getText().toString().trim();
				
				if(ckey.length() <= 0){
					Toast.makeText(ConfigAddActivity.this, "键不能为空!", Toast.LENGTH_SHORT).show();
					return ;
				}
				if(cvalue.length() <= 0){
					Toast.makeText(ConfigAddActivity.this, "值不能为空!", Toast.LENGTH_SHORT).show();
					return ;
				}
				
				Config c = new Config();
				c.setCkey(ckey);
				c.setCtype(ctypePos);
				c.setCvalue(cvalue);
				c.setNote(note);
				try {
					configDao.insert(c);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				Toast.makeText(ConfigAddActivity.this, "添加成功!", Toast.LENGTH_SHORT).show();
				
				
				// 返回上一个Activity
				Runtime runtime = Runtime.getRuntime();
				try {
					runtime.exec("input keyevent " + KeyEvent.KEYCODE_BACK);
				} catch (IOException e) {
					Log.e(ConfigAddActivity.class.getSimpleName(), e.getMessage(), e);
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

