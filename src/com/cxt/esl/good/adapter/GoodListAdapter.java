package com.cxt.esl.good.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.cxt.esl.R;
import com.cxt.esl.good.domain.Good;
import com.cxt.esl.good.listener.GoodGroupLongClickListener;

public class GoodListAdapter extends SimpleExpandableListAdapter{

	private Context context;
	private int groupLayoutId;
	private int childLayoutId;
	
	
	public GoodListAdapter(Context context,
			List<? extends Map<String, Good>> groupData, int expandedGroupLayout,
			int collapsedGroupLayout, String[] groupFrom, int[] groupTo,
			List<? extends List<? extends Map<String, Good>>> childData,
			int childLayout, int lastChildLayout, String[] childFrom,
			int[] childTo) {
		super(context, groupData, expandedGroupLayout, collapsedGroupLayout, groupFrom,
				groupTo, childData, childLayout, lastChildLayout, childFrom, childTo);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public GoodListAdapter(Context context,
			List<? extends Map<String, Good>> groupData, int expandedGroupLayout,
			int collapsedGroupLayout, String[] groupFrom, int[] groupTo,
			List<? extends List<? extends Map<String, Good>>> childData,
			int childLayout, String[] childFrom, int[] childTo) {
		super(context, groupData, expandedGroupLayout, collapsedGroupLayout, groupFrom,
				groupTo, childData, childLayout, childFrom, childTo);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public GoodListAdapter(Context context,
			List<? extends Map<String, Good>> groupData, int groupLayout,
			String[] groupFrom, int[] groupTo,
			List<? extends List<? extends Map<String, Good>>> childData,
			int childLayout, String[] childFrom, int[] childTo) {
		super(context, groupData, groupLayout, groupFrom, groupTo, childData,
				childLayout, childFrom, childTo);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.groupLayoutId = groupLayout;
		this.childLayoutId = childLayout;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		Good g = ((Map<String, Good>) getChild(groupPosition, childPosition)).get("child"); // 获取当前项的Pattern实例
		View view = LayoutInflater.from(this.context).inflate(this.childLayoutId, null);
		TextView textView = (TextView) view.findViewById(R.id.goodChildTo);
		switch (childPosition) {
		case 0:
			textView.setText("商品名:" + g.getEslName());
			break;
		case 1:
			textView.setText("显示名称:" + g.getPosName());
			break;
		case 2:
			textView.setText("现价:" + g.getPresPrice());
			break;
		default:
			break;
		}
		view.setOnLongClickListener(new GoodGroupLongClickListener(groupPosition, this, this.context));
		return view;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		Good g = ((Map<String, Good>)getGroup(groupPosition)).get("group"); // 获取当前项的Model实例
		View view = LayoutInflater.from(this.context).inflate(this.groupLayoutId, null);
		TextView textView = (TextView) view.findViewById(R.id.goodGroupTo);
		textView.setText("商品条码:" + g.getBarCode());
		return view;
	}

	
	
	

}
