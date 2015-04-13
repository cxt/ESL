package com.cxt.esl.good.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.cxt.esl.R;
import com.cxt.esl.good.domain.Good;

public class GoodUpdateActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.good_update);
		// 取得传过来的label对象
		Intent intent = getIntent();
		ArrayList<Good> list = intent.getParcelableArrayListExtra("good");
		Good g = list.get(0);
		((EditText) this.findViewById(R.id.e_bar_code)).setText("" + g.getBarCode());
		((EditText) this.findViewById(R.id.e_esl_name)).setText(g.getEslName());
		((EditText) this.findViewById(R.id.e_pos_name)).setText(g.getPosName());
		((EditText) this.findViewById(R.id.e_orig_price)).setText(String.valueOf(g.getOrigPrice()));
		((EditText) this.findViewById(R.id.e_pres_price)).setText(String.valueOf(g.getPresPrice()));
		((EditText) this.findViewById(R.id.e_price_unit)).setText(g.getPriceUnit());
		((EditText) this.findViewById(R.id.e_modi_time)).setText(g.getModiTtime());
		
	}

}

