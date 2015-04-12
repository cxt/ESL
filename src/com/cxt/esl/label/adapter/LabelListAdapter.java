package com.cxt.esl.label.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.cxt.esl.R;
import com.cxt.esl.label.listener.LabelGroupLongClickListener;
import com.cxt.esl.label.model.Label;


public class LabelListAdapter extends SimpleExpandableListAdapter{

	private Context context;
	private int groupLayoutId;
	private int childLayoutId;
	
	
	public LabelListAdapter(Context context,
			List<? extends Map<String, Label>> groupData, int expandedGroupLayout,
			int collapsedGroupLayout, String[] groupFrom, int[] groupTo,
			List<? extends List<? extends Map<String, Label>>> childData,
			int childLayout, int lastChildLayout, String[] childFrom,
			int[] childTo) {
		super(context, groupData, expandedGroupLayout, collapsedGroupLayout, groupFrom,
				groupTo, childData, childLayout, lastChildLayout, childFrom, childTo);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public LabelListAdapter(Context context,
			List<? extends Map<String, Label>> groupData, int expandedGroupLayout,
			int collapsedGroupLayout, String[] groupFrom, int[] groupTo,
			List<? extends List<? extends Map<String, Label>>> childData,
			int childLayout, String[] childFrom, int[] childTo) {
		super(context, groupData, expandedGroupLayout, collapsedGroupLayout, groupFrom,
				groupTo, childData, childLayout, childFrom, childTo);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public LabelListAdapter(Context context,
			List<? extends Map<String, Label>> groupData, int groupLayout,
			String[] groupFrom, int[] groupTo,
			List<? extends List<? extends Map<String, Label>>> childData,
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
		Label label = ((Map<String, Label>) getChild(groupPosition, childPosition)).get("child"); // 获取当前项的Label实例
		View view = LayoutInflater.from(this.context).inflate(this.childLayoutId, null);
		TextView textView = (TextView) view.findViewById(R.id.lableChildTo);
		switch (childPosition) {
		case 0:
			textView.setText("绑定商品:" + label.getGoodName());
			break;
		case 1:
			textView.setText("模板:" + label.getPatternName());
			break;
		case 2:
			textView.setText("工作状态:" + (label.getWorkStatus() == 0 ? "工作中" : "停用"));
			break;
		case 3:
			textView.setText("用户自定义编号:" + label.getMycode());
			break;
		case 4:
			textView.setText("型号:" + label.getModelName());
			break;
		default:
			break;
		}
		view.setOnLongClickListener(new LabelGroupLongClickListener(groupPosition, this, this.context));
		return view;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		Label label = ((Map<String, Label>)getGroup(groupPosition)).get("group"); // 获取当前项的Label实例
		View view = LayoutInflater.from(this.context).inflate(this.groupLayoutId, null);
		TextView textView = (TextView) view.findViewById(R.id.labelGroupTo);
		textView.setText("标签编号:" + label.getEslId());
		return view;
	}

	
	
	

}
