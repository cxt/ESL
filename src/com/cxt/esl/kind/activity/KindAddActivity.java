package com.cxt.esl.kind.activity;

import java.io.IOException;
import java.sql.SQLException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cxt.esl.R;
import com.cxt.esl.kind.dao.KindDao;
import com.cxt.esl.kind.domain.Kind;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class KindAddActivity extends Activity{

	private ESLDatebaseHelper helper;
	private KindDao kindDao;
	
	
	private void init(){
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			kindDao = new KindDao ( helper.getKindDao());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kind_add);
		init();
		final EditText etOrderNum = (EditText) findViewById(R.id.e_order_num);
		final EditText etKindName = (EditText) findViewById(R.id.e_kind_name);
		final EditText etRemarks= (EditText) findViewById(R.id.e_remarks);
		
		
		Button btnAdd = (Button) findViewById(R.id.kind_add_sure_btn);
		Button btnCancel = (Button) findViewById(R.id.kind_add_cancel_btn);
		
		btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String strOrderNum = etOrderNum.getText().toString().trim();
				String strKindName = etKindName.getText().toString().trim();
				String strRemarks = etRemarks.getText().toString().trim();
				
				if(strOrderNum.length() > 0 && !strOrderNum.matches("^[0-9]*[1-9][0-9]*$")){
					Toast.makeText(KindAddActivity.this, "排列号填写有误!", Toast.LENGTH_SHORT).show();
					return ;
				}
				
				if(strKindName.length() == 0){
					Toast.makeText(KindAddActivity.this, "商品类别填写有误!", Toast.LENGTH_SHORT).show();
					return ;
				}
				
				
				Kind k = new Kind();
				k.setOrderNum(strOrderNum.length() > 0?Integer.valueOf(strOrderNum):0);
				k.setKindName(strKindName);
				k.setRemarks(strRemarks);
				try {
					kindDao.insert(k);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				Toast.makeText(KindAddActivity.this, "添加成功!", Toast.LENGTH_SHORT).show();
				
				
				// 返回上一个Activity
				Runtime runtime = Runtime.getRuntime();
				try {
					runtime.exec("input keyevent " + KeyEvent.KEYCODE_BACK);
				} catch (IOException e) {
					Log.e(KindAddActivity.class.getSimpleName(), e.getMessage(), e);
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


