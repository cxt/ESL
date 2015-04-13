package com.cxt.esl.good.listener;

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
import com.cxt.esl.good.activity.GoodUpdateActivity;
import com.cxt.esl.good.domain.Good;

public class GoodGroupLongClickListener implements OnLongClickListener {
	private Context context;
	private SimpleExpandableListAdapter sela;
	private int position;

	public GoodGroupLongClickListener(int position,
			SimpleExpandableListAdapter sela, Context context) {
		super();
		this.context = context;
		this.sela = sela;
		this.position = position;
	}

	@Override
	public boolean onLongClick(View v) {
		// 取得所长按的groupview里面的对象，通过sela这个adapter去拿Pattern
		final Good g = ((Map<String, Good>) this.sela.getGroup(position))
				.get("group");
		// 长按时跳出一个对话框
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setItems(R.array.good_menu,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 查看
						if (which == 0) {
							AlertDialog.Builder d1 = new AlertDialog.Builder(context);
							CharSequence[] items = new CharSequence[7];
							items[0] = "商品条码：" + g.getBarCode();
							items[1] = "商品名称：" + g.getEslName();
							items[2] = "原价：" + g.getOrigPrice();
							items[3] = "现价：" + g.getPresPrice();
							items[4] = "计价单位：" + g.getPriceUnit();
							items[5] = "显示名称：" + g.getPosName();
							items[6] = "最后修改时间：" + g.getModiTtime();
							d1.setItems(items, null);
							d1.show();
						}
						// 更新
						else if (which == 1) {
							Intent intent = new Intent(context, GoodUpdateActivity.class);
							ArrayList<Good> list = new ArrayList<Good>();
							list.add(g);
							intent.putParcelableArrayListExtra("good", list );
							context.startActivity(intent);
						}
						// 删除
						else if (which == 2) {
							AlertDialog.Builder d2 = new AlertDialog.Builder(
									context);
							d2.setTitle("删除该ESL商品");
							d2.setMessage("确定删除该ESL商品");
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
						// 下架
						else if (which == 3) {
							AlertDialog.Builder d2 = new AlertDialog.Builder(
									context);
							d2.setTitle("下架该ESL商品");
							d2.setMessage("确定下架该ESL商品");
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
