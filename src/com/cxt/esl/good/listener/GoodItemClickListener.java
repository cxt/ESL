package com.cxt.esl.good.listener;

import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.cxt.esl.R;
import com.cxt.esl.good.activity.GoodUpdateActivity;
import com.cxt.esl.good.adapter.GoodAdapter;
import com.cxt.esl.good.dao.GoodDao;
import com.cxt.esl.good.domain.Good;

public class GoodItemClickListener implements OnItemClickListener{
	private Context ctx;
	private List<Good> goodList;
	private GoodDao goodDao;
	private GoodAdapter adapter;
	
	

	public GoodItemClickListener(Context ctx, List<Good> goodList,
			GoodDao goodDao, GoodAdapter adapter) {
		super();
		this.ctx = ctx;
		this.goodList = goodList;
		this.goodDao = goodDao;
		this.adapter = adapter;
	}



	@Override
	public void onItemClick(AdapterView<?> parent, View view,
			int position, long id) {
		final Good good = goodList.get(position);
		
		// 长按时跳出一个对话框
				AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
				builder.setItems(R.array.good_menu,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// 查看
								if (which == 0) {
									
								}
								// 更新
								else if (which == 1) {
									Intent intent = new Intent(ctx, GoodUpdateActivity.class);
									intent.putExtra("good", good);
									ctx.startActivity(intent);
								}
								// 删除
								else if (which == 2) {
									AlertDialog.Builder d2 = new AlertDialog.Builder(
											ctx);
									d2.setTitle("删除该商品");
									d2.setMessage("确定删除该商品");
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
													try {
														goodDao.delete(good);
														goodList = goodDao.queryAll();
														adapter = new GoodAdapter(ctx,
																R.layout.good_item, goodList);
														ListView listView = (ListView) ((Activity) ctx).findViewById(R.id.good_list);
														listView.setAdapter(adapter);
													} catch (SQLException e) {
														e.printStackTrace();
													}
												}
											});
									d2.show();
								}
							}
						});
				builder.show();
	}




}