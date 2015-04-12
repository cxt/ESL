package com.cxt.esl.label.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;

import com.cxt.esl.R;
import com.cxt.esl.label.domain.Label;

public class LabelUpdateActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.label_update);
		// 取得传过来的label对象
		Intent intent = getIntent();
		ArrayList<Label> labels = intent.getParcelableArrayListExtra("label");
		Label label = labels.get(0);
		((EditText) this.findViewById(R.id.e_esl_id)).setText(String.valueOf(label.getEslId()));
		((EditText) this.findViewById(R.id.e_good_name)).setText(label.getGoodName());
		((EditText) this.findViewById(R.id.e_pattern_name)).setText(label.getPatternName());
		((EditText) this.findViewById(R.id.e_model_name)).setText(label.getModelName());
		((EditText) this.findViewById(R.id.e_work_status)).setText(String.valueOf(label.getWorkStatus()));
		((EditText) this.findViewById(R.id.e_my_code)).setText(String.valueOf(label.getMycode()));
		
	}

}
