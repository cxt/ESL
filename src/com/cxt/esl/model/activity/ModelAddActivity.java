package com.cxt.esl.model.activity;

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
import com.cxt.esl.model.dao.ModelDao;
import com.cxt.esl.model.domain.Model;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class ModelAddActivity extends Activity{

	private ESLDatebaseHelper helper;
	private ModelDao modelDao;
	
	
	private void init(){
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			modelDao = new ModelDao ( helper.getModelDao());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.model_add);
		init();
		final EditText etModelName = (EditText) findViewById(R.id.e_model_name);
		final EditText etModelNote = (EditText) findViewById(R.id.e_model_note);
		final EditText etInch= (EditText) findViewById(R.id.e_inch);
		final EditText etWidth = (EditText) findViewById(R.id.e_width);
		final EditText etHeight = (EditText) findViewById(R.id.e_height);
		final EditText etRorate = (EditText) findViewById(R.id.e_rotate);
		final EditText etBpp = (EditText) findViewById(R.id.e_bpp);
		
		
		Button btnAdd = (Button) findViewById(R.id.model_add_sure_btn);
		Button btnCancel = (Button) findViewById(R.id.model_add_cancel_btn);
		
		btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String strModelName = etModelName.getText().toString().trim();
				String strModelNote = etModelNote.getText().toString().trim();
				String strInch = etInch.getText().toString().trim();
				String strWidth = etWidth.getText().toString().trim();
				String strHeight = etHeight.getText().toString().trim();
				String strRorate = etRorate.getText().toString().trim();
				String strBpp = etBpp.getText().toString().trim();
				
				if(strModelName.length() == 0){
					Toast.makeText(ModelAddActivity.this, "型号规格填写有误!", Toast.LENGTH_SHORT).show();
					return ;
				}
				if(strInch.length() > 0 && !strInch.matches("^\\d+(\\.\\d+)?$")){
					Toast.makeText(ModelAddActivity.this, "屏幕填写有误!", Toast.LENGTH_SHORT).show();
					return ;
				}
				
				if(strWidth.length() > 0 && !strWidth.matches("^[0-9]*[1-9][0-9]*$")){
					Toast.makeText(ModelAddActivity.this, "宽度填写有误!", Toast.LENGTH_SHORT).show();
					return;
				}	
				if(strHeight.length() > 0 && !strHeight.matches("^[0-9]*[1-9][0-9]*$")){
					Toast.makeText(ModelAddActivity.this, "高度填写有误!", Toast.LENGTH_SHORT).show();
					return;
				}	
				if(strRorate.length() > 0 && !strRorate.matches("^[0-9]*[1-9][0-9]*$")){
					Toast.makeText(ModelAddActivity.this, "翻转角度填写有误!", Toast.LENGTH_SHORT).show();
					return;
				}	
				if(strBpp.length() > 0 && !strBpp.matches("^[0-9]*[1-9][0-9]*$")){
					Toast.makeText(ModelAddActivity.this, "每像素所占位填写有误!", Toast.LENGTH_SHORT).show();
					return;
				}	
				
				Model m = new Model();
				float inch = strInch.length() > 0 ? Float.valueOf(strInch) : 0.0f;
				int width = strWidth.length() > 0 ? Integer.valueOf(strWidth) : 0;
				int height = strHeight.length() > 0?Integer.valueOf(strHeight) : 0;
				int bpp = strBpp.length() > 0 ?Integer.valueOf(strBpp):0;
				int rorate = strRorate.length() > 0 ?Integer.valueOf(strRorate) : 0; 
				
				m.setBpp(bpp);
				m.setEslHeight(height);
				m.setEslWidth(width);
				m.setInch(inch);
				m.setModelName(strModelName);
				m.setModelNote(strModelNote);
				m.setRotate(rorate);
				try {
					modelDao.insert(m);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				Toast.makeText(ModelAddActivity.this, "添加成功!", Toast.LENGTH_SHORT).show();
				
				
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

