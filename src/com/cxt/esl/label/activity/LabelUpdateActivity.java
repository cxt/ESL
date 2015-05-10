package com.cxt.esl.label.activity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

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

public class LabelUpdateActivity extends Activity {

	private ESLDatebaseHelper helper;
	private LabelDao labelDao;
	private GoodDao goodDao;
	private PatternDao patternDao;
	private ModelDao modelDao;
	
	private List<Good> goodList;
	private List<Pattern> patternList;
	private List<Model> modelList;
	private Good good;
	private Label label;
	private Pattern pattern;
	private Model model;
	private int workStaPos;
	
	private Spinner spinGoods ;
	private Spinner spinPattern ;
	private Spinner spinWorkSta;
	private Spinner spinModel;
	private EditText etEslId;
	private EditText etSleepPeriod;
	private EditText etMycode;
	private EditText etMacId;

	private void init() {
		Intent intent = getIntent();
		label = (Label) intent.getSerializableExtra("label");
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

		etEslId = (EditText) findViewById(R.id.e_esl_id);
		etSleepPeriod = (EditText) findViewById(R.id.e_sleep_peroid);
		etMycode = (EditText) findViewById(R.id.e_my_code);
		etMacId = (EditText) findViewById(R.id.e_mac_id);

		spinGoods = (Spinner) findViewById(R.id.spin_goods);
		spinPattern = (Spinner) findViewById(R.id.spin_pattern);
		spinWorkSta = (Spinner) findViewById(R.id.spin_work_status);
		spinModel = (Spinner) findViewById(R.id.spin_model);
		
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
		
		etEslId.setText(label.getEslId()+"");
		etSleepPeriod.setText(label.getSleepPeriod()+"");
		etMycode.setText(label.getMycode());
		etMacId.setText(label.getMacId());
		
		spinGoods.setSelection(findPosInGoodList(goodList, label.getGoodsId()));
		spinPattern.setSelection(findPosInPatternList(patternList, label.getPatternId()));
		spinModel.setSelection(findPosInModelList(modelList, label.getModelId()));
		spinWorkSta.setSelection(label.getWorkStatus());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.label_update);
		init();

		
		
		Button btnUpda = (Button) findViewById(R.id.label_update_btn);
		Button btnCancel = (Button) findViewById(R.id.label_updateCancel_btn);

		btnUpda.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String strEslId = etEslId.getText().toString().trim();
				String strSleepPeriod = etSleepPeriod.getText().toString()
						.trim();
				String strMycode = etMycode.getText().toString().trim();
				String strMacId = etMacId.getText().toString().trim();
				if (!strEslId.matches("^\\d{1,20}$")) {
					Toast.makeText(LabelUpdateActivity.this, "标签编号填写有误!",
							Toast.LENGTH_SHORT).show();
					return;
				}
				if (!strSleepPeriod.matches("^\\d{1,5}$")) {
					Toast.makeText(LabelUpdateActivity.this, "睡眠时长填写有误!",
							Toast.LENGTH_SHORT).show();
					return;
				}
				if (strMacId.length()>0 && !strMacId
						.matches("^([0-9a-fA-F]{2})(([0-9a-fA-F]{2}){5})$")) {
					Toast.makeText(LabelUpdateActivity.this, "物理地址填写有误!",
							Toast.LENGTH_SHORT).show();
					return;
				}

				int eslId = Integer.valueOf(strEslId);
				int sleepPeriod = Integer.valueOf(strSleepPeriod);

				Label label = new Label();
				label.setEslId(eslId);
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
					labelDao.update(label);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				Toast.makeText(LabelUpdateActivity.this, "更新成功!",
						Toast.LENGTH_SHORT).show();

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

	private int findPosInGoodList(List<Good> list,int id){
		int size = 0;
		for (Good good : list) {
			if(good.getGoodsId() == id)
				return size;
			size++;
		}
		return 0;
	}
	private int findPosInPatternList(List<Pattern> list,int id){
		int size = 0;
		for (Pattern pattern : list) {
			if(pattern.getPatternId() == id)
				return size;
			size++;
		}
		return 0;
	}
	private int findPosInModelList(List<Model> list,int id){
		int size = 0;
		for (Model model : list) {
			if(model.getModelId() == id)
				return size;
			size++;
		}
		return 0;
	}
}
