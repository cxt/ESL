package com.cxt.esl.bind.activity;

import com.cxt.esl.R;

import android.R.anim;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;


public class QuickBindActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quick_bind);
		Spinner eslSpinner = (Spinner) this.findViewById(R.id.esl_spinner);
		Spinner goodSpinner = (Spinner) this.findViewById(R.id.good_spinner);
		Spinner patternSpinner = (Spinner) this.findViewById(R.id.pattern_spinner);
		String[] eslStr = {"","未知异常","初始","正常","生成图片","下发图片没feedback","下发图片有feedback","esl不在线"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, eslStr);
		eslSpinner.setAdapter(adapter);
		eslSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(QuickBindActivity.this, "cao", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		
		});
	}

}
