package com.cxt.esl.model.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.cxt.esl.R;
import com.cxt.esl.model.domain.Model;

public class ModelUpdateActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.model_update);
		// 取得传过来的label对象
		Intent intent = getIntent();
		ArrayList<Model> list = intent.getParcelableArrayListExtra("model");
		Model m = list.get(0);
		((EditText) this.findViewById(R.id.e_model_name)).setText(m.getModelName());
		((EditText) this.findViewById(R.id.e_model_note)).setText(m.getModelNote());
		((EditText) this.findViewById(R.id.e_inch)).setText(String.valueOf(m.getInch()));
		((EditText) this.findViewById(R.id.e_width)).setText(String.valueOf(m.getWidth()));
		((EditText) this.findViewById(R.id.e_height)).setText(String.valueOf(m.getHeight()));
		((EditText) this.findViewById(R.id.e_bpp)).setText(String.valueOf(m.getBpp()));
		((EditText) this.findViewById(R.id.e_rotate)).setText(String.valueOf(m.getRotate()));
		
	}

}

