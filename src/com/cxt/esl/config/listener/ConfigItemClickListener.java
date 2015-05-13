package com.cxt.esl.config.listener;

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
import com.cxt.esl.config.activity.ConfigUpdateActivity;
import com.cxt.esl.config.adapter.ConfigAdapter;
import com.cxt.esl.config.dao.ConfigDao;
import com.cxt.esl.config.domain.Config;

public class ConfigItemClickListener implements OnItemClickListener{
	private Context ctx;
	private List<Config> configList;
	private ConfigDao configDao;
	private ConfigAdapter adapter;
	
	

	public ConfigItemClickListener(Context ctx, List<Config> configList,
			ConfigDao configDao, ConfigAdapter adapter) {
		super();
		this.ctx = ctx;
		this.configList = configList;
		this.configDao = configDao;
		this.adapter = adapter;
	}



	@Override
	public void onItemClick(AdapterView<?> parent, View view,
			int position, long id) {
		final Config config = configList.get(position);
		
		// 长按时跳出一个对话框
				AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
				builder.setItems(R.array.config_menu,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// 更新
								if (which == 0) {
									Intent intent = new Intent(ctx, ConfigUpdateActivity.class);
									intent.putExtra("config", config);
									ctx.startActivity(intent);
								}
								// 删除
								else if (which == 1) {
									AlertDialog.Builder d2 = new AlertDialog.Builder(
											ctx);
									d2.setTitle("删除该配置");
									d2.setMessage("确定删除该配置");
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
														configDao.delete(config);
														configList = configDao.queryAll();
														adapter = new ConfigAdapter(ctx,
																R.layout.config_item, configList);
														ListView listView = (ListView) ((Activity) ctx).findViewById(R.id.config_list);
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
