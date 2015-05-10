package com.cxt.esl.label.activity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
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
import com.cxt.esl.good.dao.GoodDao;
import com.cxt.esl.good.domain.Good;
import com.cxt.esl.label.dao.LabelDao;
import com.cxt.esl.label.domain.Label;
import com.cxt.esl.model.dao.ModelDao;
import com.cxt.esl.model.domain.Model;
import com.cxt.esl.pattern.dao.PatternDao;
import com.cxt.esl.pattern.domain.Pattern;
import com.cxt.esl.util.arrayAdapter.GoodArrayAdapter;
import com.cxt.esl.util.arrayAdapter.ModelArrayAdapter;
import com.cxt.esl.util.arrayAdapter.PatternArrayAdapter;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class LabelAddActivity extends Activity{
	
	private ESLDatebaseHelper helper;
	private LabelDao labelDao;
	private GoodDao goodDao;
	private PatternDao patternDao;
	private ModelDao modelDao;
	
	private List<Good> goodList;
	private List<Pattern> patternList;
	private List<Model> modelList;
	private Good good;
	private Pattern pattern;
	private Model model;
	private int workStaPos;
	
	private Spinner spinGoods ;
	private Spinner spinPattern ;
	private Spinner spinWorkSta;
	private Spinner spinModel;
	private void init(){
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			labelDao = new LabelDao ( helper.getLabelDao());
			goodDao = new GoodDao ( helper.getGoodDao());
			patternDao = new PatternDao(helper.getPatternDao());
			modelDao = new ModelDao(helper.getModelDao());
			goodList = goodDao.queryAll();
			patternList = patternDao.queryAll();
			modelList = modelDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		spinGoods = (Spinner) findViewById(R.id.spin_goods);
		spinPattern = (Spinner) findViewById(R.id.spin_pattern);
		spinWorkSta = (Spinner) findViewById(R.id.spin_work_status);
		spinModel = (Spinner) findViewById(R.id.spin_model);
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
		
		spinGoods.setAdapter(new GoodArrayAdapter(this, goodList));
		spinGoods.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
					good = goodList.get(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		
		});
		spinPattern.setAdapter(new PatternArrayAdapter(this, patternList));
		spinPattern.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				pattern = patternList.get(position);
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
			
		});
		spinModel.setAdapter(new ModelArrayAdapter(this, modelList));
		spinModel.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				model = modelList.get(position);
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
			
		});
		
		String[] workStaStr = {"工作中","停用"};
		ArrayAdapter<String> workStaAda = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, workStaStr);
		spinWorkSta.setAdapter(workStaAda);
		spinWorkSta.setOnItemSelectedListener(new OnItemSelectedListener() {

			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				workStaPos = position;
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		
		});
		
		Button btnAdd = (Button) findViewById(R.id.label_add_sure_btn);
		Button btnCancel = (Button) findViewById(R.id.label_add_cancel_btn);
		
		btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String strSleepPeriod = etSleepPeriod.getText().toString().trim();
				String strMycode = etMycode.getText().toString().trim();
				String strMacId = etMacId.getText().toString().trim();
				if(!strSleepPeriod.matches("^\\d{1,5}$")){
					Toast.makeText(LabelAddActivity.this, "睡眠时长填写有误!", Toast.LENGTH_SHORT).show();
					return;
				}
				if(strMacId.length()>0 && !strMacId.matches("^([0-9a-fA-F]{2})(([0-9a-fA-F]{2}){5})$")){
					Toast.makeText(LabelAddActivity.this, "物理地址填写有误!", Toast.LENGTH_SHORT).show();
					return;
				}
					
				int sleepPeriod = Integer.valueOf(strSleepPeriod);
				
				Label label = new Label();
				label.setSleepPeriod(sleepPeriod);
				label.setMycode(strMycode);
				label.setMacId(strMacId);
				if(good != null)
					label.setGoodsId(good.getGoodsId());
				if(pattern != null)
					label.setPatternId(pattern.getPatternId());
				if(model != null)
					label.setModelId(pattern.getModelId());
				label.setWorkStatus(workStaPos);
				
				try {
					helper.getLabelDao().create(label);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				Toast.makeText(LabelAddActivity.this, "添加成功!", Toast.LENGTH_SHORT).show();
				
				
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
