package com.cxt.esl.sale.activity;

import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.cxt.esl.R;
import com.cxt.esl.sale.adapter.OrderAdapter;
import com.cxt.esl.sale.dao.OrderDao;
import com.cxt.esl.sale.domain.Order;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class OrderActivity extends Activity{
	private ESLDatebaseHelper helper;
	private OrderDao orderDao;
	private List<Order> orderList;
	private OrderAdapter adapter;
	
	private void init(){
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			orderDao = new OrderDao(helper.getOrderDao());
			orderList = orderDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order);
		init();
		adapter = new OrderAdapter(OrderActivity.this,
				R.layout.order_item, orderList);
		ListView listView = (ListView) findViewById(R.id.order_list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				final Order order = orderList.get(position);
				AlertDialog.Builder d = new AlertDialog.Builder(
						OrderActivity.this);
				d.setTitle("删除该订单");
				d.setMessage("确定删除该订单");
				d.setCancelable(false);
				d.setNegativeButton("否",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(
									DialogInterface dialog,
									int which) {
							}
						});
				d.setPositiveButton("是",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(
									DialogInterface dialog,
									int which) {
								orderList.remove(order);
								try {
									orderDao.delete(order);
								} catch (SQLException e) {
									e.printStackTrace();
								}
								adapter = new OrderAdapter(OrderActivity.this,
										R.layout.order_item, orderList);
								ListView listView = (ListView) findViewById(R.id.order_list);
								listView.setAdapter(adapter);
								
							}
						});
				d.show();
			}
		});
	}
}
