package com.cxt.esl.bind.activity;

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
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.cxt.esl.R;
import com.cxt.esl.good.dao.GoodDao;
import com.cxt.esl.good.domain.Good;
import com.cxt.esl.label.dao.LabelDao;
import com.cxt.esl.label.domain.Label;
import com.cxt.esl.pattern.dao.PatternDao;
import com.cxt.esl.pattern.domain.Pattern;
import com.cxt.esl.util.arrayAdapter.GoodArrayAdapter;
import com.cxt.esl.util.arrayAdapter.LabelArrayAdapter;
import com.cxt.esl.util.arrayAdapter.PatternArrayAdapter;
import com.cxt.esl.util.db.ESLDatebaseHelper;


public class QuickBindActivity extends Activity{
	
	private ESLDatebaseHelper helper;
	private LabelDao labelDao;
	private List<Label> labelList;
	private GoodDao goodDao;
	private List<Good> goodList;
	private PatternDao patternDao;
	private List<Pattern> patternList;
	
	private Label label;
	private Good good;
	private Pattern pattern;
	
	private Spinner spinLabel;
	private Spinner spinGood ;
	private Spinner spinPattern;
	
	private Button btnAdd;
	private Button btnCancel;
	
	private void init(){
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			labelDao = new LabelDao(helper.getLabelDao());
			labelList = labelDao.queryByEslIdOrWorkStatus(-1, 0);
			
			goodDao = new GoodDao ( helper.getGoodDao());
			goodList = goodDao.queryAll();
			
			patternDao = new PatternDao(helper.getPatternDao());
			patternList = patternDao.queryAll();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		spinLabel = (Spinner) findViewById(R.id.spin_esl);
		spinGood = (Spinner) findViewById(R.id.spin_good);
		spinPattern = (Spinner) findViewById(R.id.spin_pattern);
		
		btnAdd = (Button) findViewById(R.id.bind_sure_btn);
		btnCancel = (Button) findViewById(R.id.bind_cancel_btn);
		
		spinLabel.setAdapter(new LabelArrayAdapter(this, labelList));
		spinLabel.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				label = labelList.get(position);
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		spinGood.setAdapter(new GoodArrayAdapter(this, goodList));
		spinGood.setOnItemSelectedListener(new OnItemSelectedListener() {
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
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quick_bind);
		init();
		
		btnAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(label != null){
					if(good != null){
						label.setGoodsId(good.getGoodsId());
					}
					if(pattern != null){
						label.setPatternId(pattern.getPatternId());
					}
					try {
						labelDao.update(label);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				Toast.makeText(QuickBindActivity.this, "绑定成功", Toast.LENGTH_SHORT).show();
				
				
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

}
