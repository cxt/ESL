package com.cxt.esl.kind.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cxt.esl.R;
import com.cxt.esl.kind.domain.Kind;

public class KindAdapter extends ArrayAdapter<Kind>{

	
	private int resourceId;
	private static final String STR_KIND_ID = "类别编号: ";
	private static final String STR_ORDER_NUM= "排列号: ";
	private static final String STR_KIND_NAME = "商品类别: ";
	private static final String STR_REMARKS = "备注: ";
	
	public KindAdapter(Context context, int textViewResourceId,
			List<Kind> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Kind k = getItem(position);
		View view;
		ViewHolder viewHolder;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.kindId = (TextView) view.findViewById(R.id.kind_id);
			viewHolder.orderNum = (TextView) view.findViewById(R.id.order_num);
			viewHolder.kindName = (TextView) view.findViewById(R.id.kind_name);
			viewHolder.remarks = (TextView) view.findViewById(R.id.remarks);
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.kindId.setText(STR_KIND_ID+k.getKindId());
		viewHolder.orderNum.setText(STR_ORDER_NUM+k.getOrderNum());
		viewHolder.kindName.setText(STR_KIND_NAME+k.getKindName());
		viewHolder.remarks.setText(STR_REMARKS+k.getRemarks());
		
		return view;
	}
	
	class ViewHolder {
		
		TextView kindId;
		TextView orderNum;
		TextView kindName;
		TextView remarks;
		
	}
	

}
