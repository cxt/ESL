package com.cxt.esl.good.activity;

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
import com.cxt.esl.util.arrayAdapter.GoodArrayAdapter;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class GoodReplaceActivity extends Activity {

	private ESLDatebaseHelper helper;
	private GoodDao goodDao;
	private LabelDao labelDao;
	private List<Good> goodList1;
	private List<Good> goodList2;

	private Good good1;
	private Good good2;

	private Spinner spinGood1;
	private Spinner spinGood2;

	private Button btnAdd;
	private Button btnCancel;

	private void init() {
		try {
			helper = ESLDatebaseHelper.getHelper(this);

			goodDao = new GoodDao(helper.getGoodDao());
			labelDao = new LabelDao(helper.getLabelDao());
			goodList1 = goodDao.queryAll();
			goodList2 = goodDao.queryAll();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		spinGood1 = (Spinner) findViewById(R.id.spin_good1);
		spinGood2 = (Spinner) findViewById(R.id.spin_good2);

		btnAdd = (Button) findViewById(R.id.replace_sure_btn);
		btnCancel = (Button) findViewById(R.id.replace_cancel_btn);

		spinGood1.setAdapter(new GoodArrayAdapter(this, goodList1));
		spinGood1.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				good1 = goodList1.get(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		spinGood2.setAdapter(new GoodArrayAdapter(this, goodList2));
		spinGood2.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				good2 = goodList2.get(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.good_replace);
		init();

		btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				List<Label> labelList = null;
				try {
					labelList = labelDao.findLabelByGoodId(good1.getGoodsId());
					for(Label l : labelList){
						l.setGoodsId(good2.getGoodsId());
						labelDao.update(l);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				Toast.makeText(GoodReplaceActivity.this, "商品替换成功",
						Toast.LENGTH_SHORT).show();

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
