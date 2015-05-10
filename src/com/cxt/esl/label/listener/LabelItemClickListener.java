package com.cxt.esl.label.listener;

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
import com.cxt.esl.label.activity.LabelUpdateActivity;
import com.cxt.esl.label.adapter.LabelAdapter;
import com.cxt.esl.label.dao.LabelDao;
import com.cxt.esl.label.domain.Label;

public class LabelItemClickListener implements OnItemClickListener{
	private Context ctx;
	private List<Label> labelList;
	private LabelDao labelDao;
	private LabelAdapter adapter;
	
	

	public LabelItemClickListener(Context ctx, List<Label> labelList,
			LabelDao labelDao, LabelAdapter adapter) {
		super();
		this.ctx = ctx;
		this.labelList = labelList;
		this.labelDao = labelDao;
		this.adapter = adapter;
	}



	@Override
	public void onItemClick(AdapterView<?> parent, View view,
			int position, long id) {
		final Label label = labelList.get(position);
		
		// 长按时跳出一个对话框
				AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
				builder.setItems(R.array.label_menu,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// 查看
								if (which == 0) {
									AlertDialog.Builder d1 = new AlertDialog.Builder(ctx);
									CharSequence[] items = new CharSequence[12];
									items[0] = "商品编号：" + label.getEslId();
									items[1] = "绑定商品：" + label.getGoodsId();
									items[2] = "模板：" + label.getPatternId();
									items[3] = "工作状态：" + label.getWorkStatus();
									items[4] = "AP_ID：" + label.getApId();
									items[5] = "休眠时长：" + label.getSleepPeriod();
									items[6] = "用户自定义编号：" + label.getMycode();
									items[7] = "电池电量：" + label.getPower();
									items[8] = "物理地址：" + label.getMacId();
									items[9] = "型号：" + label.getPatternId();
									items[10] = "最后通信时间：" + label.getLastCommTime()==null?"":"";
									items[11] = "备注：" + label.getRemarks();
									d1.setItems(items, null);
									d1.show();
								}
								// 更新
								else if (which == 1) {
									Intent intent = new Intent(ctx, LabelUpdateActivity.class);
									intent.putExtra("label", label);
									ctx.startActivity(intent);
								}
								// 删除
								else if (which == 2) {
									AlertDialog.Builder d2 = new AlertDialog.Builder(
											ctx);
									d2.setTitle("删除该ESL标签");
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
													try {
														labelDao.delete(label);
														labelList = labelDao.queryAll();
														adapter = new LabelAdapter(ctx,
																R.layout.label_item, labelList);
														ListView listView = (ListView) ((Activity) ctx).findViewById(R.id.label_list);
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
