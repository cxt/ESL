package com.cxt.esl.pattern.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.cxt.esl.R;
import com.cxt.esl.pattern.domain.Pattern;

public class PatternUpdateActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pattern_update);
		// 取得传过来的label对象
		Intent intent = getIntent();
		ArrayList<Pattern> patterns = intent.getParcelableArrayListExtra("pattern");
		Pattern pattern = patterns.get(0);
		((EditText) this.findViewById(R.id.e_order_num)).setText(String.valueOf(pattern.getOrderNum()));
		((EditText) this.findViewById(R.id.e_pattern_name)).setText(pattern.getPatternName());
		((EditText) this.findViewById(R.id.e_model_name)).setText(pattern.getModelName());
		((EditText) this.findViewById(R.id.e_readme)).setText(String.valueOf(pattern.getReadme()));
		((EditText) this.findViewById(R.id.e_code)).setText(String.valueOf(pattern.getCode()));
		
	}

}

