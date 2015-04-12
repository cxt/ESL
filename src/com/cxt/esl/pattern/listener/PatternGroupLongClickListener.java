package com.cxt.esl.pattern.listener;

import java.util.ArrayList;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.SimpleExpandableListAdapter;

import com.cxt.esl.R;
import com.cxt.esl.label.activity.LabelUpdateActivity;
import com.cxt.esl.label.domain.Label;
import com.cxt.esl.pattern.activity.PatternUpdateActivity;
import com.cxt.esl.pattern.domain.Pattern;

public class PatternGroupLongClickListener implements OnLongClickListener {
	private Context context;
	private SimpleExpandableListAdapter sela;
	private int position;

	public PatternGroupLongClickListener(int position,
			SimpleExpandableListAdapter sela, Context context) {
		super();
		this.context = context;
		this.sela = sela;
		this.position = position;
	}

	@Override
	public boolean onLongClick(View v) {
		// 取得所长按的groupview里面的对象，通过sela这个adapter去拿Pattern
		final Pattern p = ((Map<String, Pattern>) this.sela.getGroup(position))
				.get("group");
		// 长按时跳出一个对话框
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setItems(R.array.pattern_menu,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 查看
						if (which == 0) {
							AlertDialog.Builder d1 = new AlertDialog.Builder(context);
							CharSequence[] items = new CharSequence[5];
							items[0] = "排列号：" + p.getOrderNum();
							items[1] = "模板名称：" + p.getPatternName();
							items[2] = "适用型号：" + p.getModelName();
							items[3] = "readme：" + p.getReadme();
							items[4] = "html代码：" + p.getCode();
							d1.setItems(items, null);
							d1.show();
						}
						// 更新
						else if (which == 1) {
							Intent intent = new Intent(context, PatternUpdateActivity.class);
							ArrayList<Pattern> list = new ArrayList<Pattern>();
							list.add(p);
							intent.putParcelableArrayListExtra("pattern", list );
							context.startActivity(intent);
						}
						// 删除
						else if (which == 2) {
							AlertDialog.Builder d2 = new AlertDialog.Builder(
									context);
							d2.setTitle("删除该ESL模板");
							d2.setMessage("确定删除该ESL模板");
							d2.setCancelable(false);
							d2.setNegativeButton("否",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
										}
									});
							d2.setPositiveButton("是",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
										}
									});
							d2.show();
						}
					}
				});
		builder.show();
		return false;
	}

}
