package com.cxt.esl.pattern.listener;

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
import com.cxt.esl.pattern.activity.PatternUpdateActivity;
import com.cxt.esl.pattern.activity.PatternWebviewActivity;
import com.cxt.esl.pattern.adapter.PatternAdapter;
import com.cxt.esl.pattern.dao.PatternDao;
import com.cxt.esl.pattern.domain.Pattern;

public class PatternItemClickListener implements OnItemClickListener {
	private Context ctx;
	private List<Pattern> patternList;
	private PatternDao patternDao;
	private PatternAdapter adapter;

	public PatternItemClickListener(Context ctx, List<Pattern> patternList,
			PatternDao patternDao, PatternAdapter adapter) {
		super();
		this.ctx = ctx;
		this.patternList = patternList;
		this.patternDao = patternDao;
		this.adapter = adapter;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		final Pattern pattern = patternList.get(position);

		// 长按时跳出一个对话框
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setItems(R.array.pattern_menu,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 查看
						if (which == 0) {
							Intent intent = new Intent(ctx,
									PatternWebviewActivity.class);
							intent.putExtra("pattern", pattern);
							ctx.startActivity(intent);
						}
						// 更新
						else if (which == 1) {
							Intent intent = new Intent(ctx,
									PatternUpdateActivity.class);
							intent.putExtra("pattern", pattern);
							ctx.startActivity(intent);
						}
						// 删除
						else if (which == 2) {
							AlertDialog.Builder d2 = new AlertDialog.Builder(
									ctx);
							d2.setTitle("删除该模板");
							d2.setMessage("确定删除该模板");
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
												patternDao.delete(pattern);
												patternList = patternDao
														.queryAll();
												adapter = new PatternAdapter(
														ctx,
														R.layout.pattern_item,
														patternList);
												ListView listView = (ListView) ((Activity) ctx)
														.findViewById(R.id.pattern_list);
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
