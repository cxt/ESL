package com.cxt.esl.label.listener;

import java.util.ArrayList;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.SimpleExpandableListAdapter;

import com.cxt.esl.R;
import com.cxt.esl.label.activity.LabelActivity;
import com.cxt.esl.label.activity.LabelAddActivity;
import com.cxt.esl.label.activity.LabelUpdateActivity;
import com.cxt.esl.label.model.Label;

public class LabelGroupLongClickListener implements OnLongClickListener {
	private Context context;
	private SimpleExpandableListAdapter sela;
	private int position;

	public LabelGroupLongClickListener(int position,
			SimpleExpandableListAdapter sela, Context context) {
		super();
		this.context = context;
		this.sela = sela;
		this.position = position;
	}

	@Override
	public boolean onLongClick(View v) {
		// 取得所长按的groupview里面的对象，通过sela这个adapter去拿Label
		final Label label = ((Map<String, Label>) this.sela.getGroup(position))
				.get("group");
		// 长按时跳出一个对话框
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setItems(R.array.label_menu,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 查看
						if (which == 0) {
							AlertDialog.Builder d1 = new AlertDialog.Builder(context);
							CharSequence[] items = new CharSequence[6];
							items[0] = "标签编号：" + label.getEslId();
							items[1] = "绑定商品：" + label.getGoodName();
							items[2] = "模板：" + label.getPatternName();
							items[3] = "工作状态：" + label.getWorkStatus();
							items[4] = "用户自定义编号：" + label.getMycode();
							items[5] = "型号：" + label.getModelName();
							d1.setItems(items, null);
							d1.show();
						}
						// 更新
						else if (which == 1) {
							Intent intent = new Intent(context, LabelUpdateActivity.class);
							ArrayList<Label> list = new ArrayList<Label>();
							list.add(label);
							intent.putParcelableArrayListExtra("label", list );
							context.startActivity(intent);
						}
						// 删除
						else if (which == 2) {
							AlertDialog.Builder d2 = new AlertDialog.Builder(
									context);
							d2.setTitle("删除该ESL标签");
							d2.setMessage("确定删除该ESL标签");
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
