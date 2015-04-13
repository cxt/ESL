package com.cxt.esl.kind.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.cxt.esl.R;
import com.cxt.esl.kind.domain.Kind;

public class KindUpdateActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kind_update);
		// 取得传过来的label对象
		Intent intent = getIntent();
		ArrayList<Kind> list = intent.getParcelableArrayListExtra("kind");
		Kind k = list.get(0);
		((EditText) this.findViewById(R.id.e_kind_id)).setText(String.valueOf(k.getKindId()));
		((EditText) this.findViewById(R.id.e_kind_name)).setText(k.getKindname());
		((EditText) this.findViewById(R.id.e_remarks)).setText(k.getRemarks());
		
	}

}

