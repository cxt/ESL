package com.cxt.esl.kind.listener;

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
import com.cxt.esl.kind.activity.KindUpdateActivity;
import com.cxt.esl.kind.adapter.KindAdapter;
import com.cxt.esl.kind.dao.KindDao;
import com.cxt.esl.kind.domain.Kind;

public class KindItemClickListener implements OnItemClickListener{
	private Context ctx;
	private List<Kind> kindList;
	private KindDao kindDao;
	private KindAdapter adapter;
	
	

	public KindItemClickListener(Context ctx, List<Kind> kindList,
			KindDao kindDao, KindAdapter adapter) {
		super();
		this.ctx = ctx;
		this.kindList = kindList;
		this.kindDao = kindDao;
		this.adapter = adapter;
	}



	@Override
	public void onItemClick(AdapterView<?> parent, View view,
			int position, long id) {
		final Kind kind = kindList.get(position);
		
		// 长按时跳出一个对话框
				AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
				builder.setItems(R.array.kind_menu,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// 更新
								if (which == 0) {
									Intent intent = new Intent(ctx, KindUpdateActivity.class);
									intent.putExtra("kind", kind);
									ctx.startActivity(intent);
								}
								// 删除
								else if (which == 1) {
									AlertDialog.Builder d2 = new AlertDialog.Builder(
											ctx);
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
													try {
														kindDao.delete(kind);
														kindList = kindDao.queryAll();
														adapter = new KindAdapter(ctx,
																R.layout.kind_item, kindList);
														ListView listView = (ListView) ((Activity) ctx).findViewById(R.id.kind_list);
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
 