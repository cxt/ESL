package com.cxt.esl.kind.activity;

import java.io.IOException;
import java.sql.SQLException;

import android.app.Activity;
import android.os.Bundle;
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

public class KindUpdateActivity extends Activity{

	private ESLDatebaseHelper helper;
	private KindDao kindDao;
	private Kind kind;
	
	private EditText etOrderNum;
	private EditText etKindName;
	private EditText etRemarks;
	private void init(){
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			kindDao = new KindDao ( helper.getKindDao());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		kind = (Kind) getIntent().getSerializableExtra("kind");
		
		etOrderNum = (EditText) findViewById(R.id.e_order_num);
		etKindName = (EditText) findViewById(R.id.e_kind_name);
		etRemarks= (EditText) findViewById(R.id.e_remarks);
	
		etOrderNum.setText(""+kind.getOrderNum());
		etKindName.setText(kind.getKindName());
		etRemarks.setText(kind.getRemarks());
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kind_update);
		init();
		
		
		
		Button btnAdd = (Button) findViewById(R.id.kind_update_sure_btn);
		Button btnCancel = (Button) findViewById(R.id.kind_update_cancel_btn);
		
		btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String strOrderNum = etOrderNum.getText().toString().trim();
				String strKindName = etKindName.getText().toString().trim();
				String strRemarks = etRemarks.getText().toString().trim();
				
				if(strOrderNum.length() > 0 && !strOrderNum.matches("^[0-9]*[1-9][0-9]*$")){
					Toast.makeText(KindUpdateActivity.this, "排列号填写有误!", Toast.LENGTH_SHORT).show();
					return ;
				}
				
				if(strKindName.length() == 0){
					Toast.makeText(KindUpdateActivity.this, "商品类别填写有误!", Toast.LENGTH_SHORT).show();
					return ;
				}
				
				
				Kind k = new Kind();
				k.setOrderNum(strOrderNum.length() > 0?Integer.valueOf(strOrderNum):0);
				k.setKindName(strKindName);
				k.setRemarks(strRemarks);
				k.setKindId(kind.getKindId());
				try {
					kindDao.update(k);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				Toast.makeText(KindUpdateActivity.this, "更新成功!", Toast.LENGTH_SHORT).show();
				
				
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

