package com.cxt.esl.pattern.activity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.cxt.esl.R;
import com.cxt.esl.model.dao.ModelDao;
import com.cxt.esl.model.domain.Model;
import com.cxt.esl.pattern.dao.PatternDao;
import com.cxt.esl.pattern.domain.Pattern;
import com.cxt.esl.util.arrayAdapter.ModelArrayAdapter;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class PatternUpdateActivity extends Activity {

	private Pattern pattern;
	private ESLDatebaseHelper helper;
	private PatternDao patternDao;

	private ModelDao modelDao;

	private List<Model> modelList;
	private Model model;

	private EditText etPatterName;
	private EditText etOrderNum;
	private EditText etCode;
	private EditText etReadme;

	private Spinner spinModel;

	private void init() {
		Intent intent = getIntent();
		pattern = (Pattern) intent.getSerializableExtra("pattern");
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			patternDao = new PatternDao(helper.getPatternDao());
			modelDao = new ModelDao(helper.getModelDao());
			modelList = modelDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		etPatterName = (EditText) findViewById(R.id.e_pattern_name);
		etOrderNum = (EditText) findViewById(R.id.e_order_num);
		etCode = (EditText) findViewById(R.id.e_code);
		etReadme = (EditText) findViewById(R.id.e_readme);

		spinModel = (Spinner) findViewById(R.id.spin_model);

		etPatterName.setText(pattern.getPatternName());
		etOrderNum.setText(pattern.getOrderNum() + "");
		etCode.setText(pattern.getCode());
		etReadme.setText(pattern.getReadme());

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

		spinModel.setSelection(findPosInModelList(modelList,
				pattern.getModelId()));

	}

	private int findPosInModelList(List<Model> list, int modelId) {
		int size = 0;
		for (Model model : list) {
			if (model.getModelId() == modelId)
				return size;
			size++;
		}
		return 0;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pattern_update);
		init();

		Button btnUpda = (Button) findViewById(R.id.pattern_update_sure_btn);
		Button btnCancel = (Button) findViewById(R.id.pattern_update_cancel_btn);

		final int patternId = pattern.getPatternId();
		btnUpda.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String strPatternName = etPatterName.getText().toString()
						.trim();
				String strOrderNum = etOrderNum.getText().toString().trim();
				String strCode = etCode.getText().toString().trim();
				String strReadme = etReadme.getText().toString().trim();

				if (strPatternName.length() == 0) {
					Toast.makeText(PatternUpdateActivity.this, "模板名称填写有误!",
							Toast.LENGTH_SHORT).show();
					return;
				}

				if (!strOrderNum.matches("^\\d{0,5}$")) {
					Toast.makeText(PatternUpdateActivity.this, "排列号填写有误!",
							Toast.LENGTH_SHORT).show();
					return;
				}

				int orderNum = 0;
				if (strOrderNum.length() > 0)
					orderNum = Integer.valueOf(strOrderNum);
				Pattern p = new Pattern();
				if (model != null) {
					p.setModelId(model.getModelId());
					p.setModel(model.getModelName());
				}
				p.setCode(strCode);
				p.setOrderNum(orderNum);
				p.setPatternName(strPatternName);
				p.setReadme(strReadme);
				p.setPatternId(patternId);
				try {
					patternDao.update(p);
				} catch (SQLException e) {
					Log.e(PatternUpdateActivity.class.getSimpleName(),
							e.getMessage(), e);
				}

				Toast.makeText(PatternUpdateActivity.this, "更新成功!",
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

}
