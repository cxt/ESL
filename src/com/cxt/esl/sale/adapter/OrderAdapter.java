package com.cxt.esl.sale.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cxt.esl.R;
import com.cxt.esl.sale.domain.Order;

public class OrderAdapter extends ArrayAdapter<Order> {

	private int resourceId;
	private static final String STR_OPERATOR = "操作者:";
	private static final String STR_ALL_PRICE = "总价:";
	private static final String STR_USER_PAY = "用户支付:";
	private static final String STR_CHANGE = "找回零钱:";
	private static final String STR_CREATE_DATE = "订单创建时间:";
	private static final String STR_GOODS = "购买了:";
	
	public OrderAdapter(Context context, int textViewResourceId,
			List<Order> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Order o = getItem(position);
		View view;
		ViewHolder viewHolder;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.allPrice = (TextView) view.findViewById(R.id.all_price);
			viewHolder.change = (TextView) view.findViewById(R.id.change);
			viewHolder.createDate = (TextView) view.findViewById(R.id.create_date);
			viewHolder.goods = (TextView) view.findViewById(R.id.goods);
			viewHolder.operator = (TextView) view.findViewById(R.id.operator);
			viewHolder.userPay = (TextView) view.findViewById(R.id.user_pay);
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.allPrice.setText(STR_ALL_PRICE + o.getAllPrice());
		viewHolder.change.setText(STR_CHANGE+o.getChange());
		viewHolder.createDate.setText(STR_CREATE_DATE+o.getCreateDate());
		viewHolder.goods.setText(STR_GOODS+o.getGoods());
		viewHolder.operator.setText(STR_OPERATOR+o.getOperator());
		viewHolder.userPay.setText(STR_USER_PAY+o.getUserPay());
		
		return view;
	}
	
	class ViewHolder {
		
		TextView operator;
		TextView allPrice;
		TextView userPay;
		TextView change;
		TextView createDate;
		TextView goods;
		
	}
	
	

}
