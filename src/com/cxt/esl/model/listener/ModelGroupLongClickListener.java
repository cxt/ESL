package com.cxt.esl.model.listener;

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
import com.cxt.esl.model.activity.ModelUpdateActivity;
import com.cxt.esl.model.domain.Model;

public class ModelGroupLongClickListener implements OnLongClickListener {
	private Context context;
	private SimpleExpandableListAdapter sela;
	private int position;

	public ModelGroupLongClickListener(int position,
			SimpleExpandableListAdapter sela, Context context) {
		super();
		this.context = context;
		this.sela = sela;
		this.position = position;
	}

	@Override
	public boolean onLongClick(View v) {
		// 取得所长按的groupview里面的对象，通过sela这个adapter去拿Pattern
		final Model m = ((Map<String, Model>) this.sela.getGroup(position))
				.get("group");
		// 长按时跳出一个对话框
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setItems(R.array.model_menu,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 查看
						if (which == 0) {
							AlertDialog.Builder d1 = new AlertDialog.Builder(context);
							CharSequence[] items = new CharSequence[7];
							items[0] = "规格型号：" + m.getModelName();
							items[1] = "尺寸：" + m.getInch();
							items[2] = "型号宽度：" + m.getWidth();
							items[3] = "型号高度：" + m.getHeight();
							items[4] = "旋转角度：" + m.getRotate();
							items[5] = "像素：" + m.getRotate();
							items[6] = "型号特征：" + m.getModelNote();
							d1.setItems(items, null);
							d1.show();
						}
						// 更新
						else if (which == 1) {
							Intent intent = new Intent(context, ModelUpdateActivity.class);
							ArrayList<Model> list = new ArrayList<Model>();
							list.add(m);
							intent.putParcelableArrayListExtra("model", list );
							context.startActivity(intent);
						}
						// 删除
						else if (which == 2) {
							AlertDialog.Builder d2 = new AlertDialog.Builder(
									context);
							d2.setTitle("删除该ESL型号");
							d2.setMessage("确定删除该ESL型号");
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
