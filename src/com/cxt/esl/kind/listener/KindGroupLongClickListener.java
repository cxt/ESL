package com.cxt.esl.kind.listener;

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
import com.cxt.esl.kind.activity.KindUpdateActivity;
import com.cxt.esl.kind.domain.Kind;

public class KindGroupLongClickListener implements OnLongClickListener {
	private Context context;
	private SimpleExpandableListAdapter sela;
	private int position;

	public KindGroupLongClickListener(int position,
			SimpleExpandableListAdapter sela, Context context) {
		super();
		this.context = context;
		this.sela = sela;
		this.position = position;
	}

	@Override
	public boolean onLongClick(View v) {
		// 取得所长按的groupview里面的对象，通过sela这个adapter去拿Pattern
		final Kind k = ((Map<String, Kind>) this.sela.getGroup(position))
				.get("group");
		// 长按时跳出一个对话框
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setItems(R.array.kind_menu,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 查看
						if (which == 0) {
							AlertDialog.Builder d1 = new AlertDialog.Builder(context);
							CharSequence[] items = new CharSequence[3];
							items[0] = "类别编号：" + k.getKindId();
							items[1] = "商品类别：" + k.getKindname();
							items[2] = "备注：" + k.getRemarks();
							d1.setItems(items, null);
							d1.show();
						}
						// 更新
						else if (which == 1) {
							Intent intent = new Intent(context, KindUpdateActivity.class);
							ArrayList<Kind> list = new ArrayList<Kind>();
							list.add(k);
							intent.putParcelableArrayListExtra("kind", list );
							context.startActivity(intent);
						}
						// 删除
						else if (which == 2) {
							AlertDialog.Builder d2 = new AlertDialog.Builder(
									context);
							d2.setTitle("删除该类别");
							d2.setMessage("确定删除该类别");
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
