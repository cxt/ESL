package com.cxt.esl.pattern.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.cxt.esl.R;
import com.cxt.esl.pattern.domain.Pattern;
import com.cxt.esl.pattern.listener.PatternGroupLongClickListener;


public class PatternListAdapter extends SimpleExpandableListAdapter{

	private Context context;
	private int groupLayoutId;
	private int childLayoutId;
	
	
	public PatternListAdapter(Context context,
			List<? extends Map<String, Pattern>> groupData, int expandedGroupLayout,
			int collapsedGroupLayout, String[] groupFrom, int[] groupTo,
			List<? extends List<? extends Map<String, Pattern>>> childData,
			int childLayout, int lastChildLayout, String[] childFrom,
			int[] childTo) {
		super(context, groupData, expandedGroupLayout, collapsedGroupLayout, groupFrom,
				groupTo, childData, childLayout, lastChildLayout, childFrom, childTo);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public PatternListAdapter(Context context,
			List<? extends Map<String, Pattern>> groupData, int expandedGroupLayout,
			int collapsedGroupLayout, String[] groupFrom, int[] groupTo,
			List<? extends List<? extends Map<String, Pattern>>> childData,
			int childLayout, String[] childFrom, int[] childTo) {
		super(context, groupData, expandedGroupLayout, collapsedGroupLayout, groupFrom,
				groupTo, childData, childLayout, childFrom, childTo);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public PatternListAdapter(Context context,
			List<? extends Map<String, Pattern>> groupData, int groupLayout,
			String[] groupFrom, int[] groupTo,
			List<? extends List<? extends Map<String, Pattern>>> childData,
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
		Pattern p = ((Map<String, Pattern>) getChild(groupPosition, childPosition)).get("child"); // 获取当前项的Pattern实例
		View view = LayoutInflater.from(this.context).inflate(this.childLayoutId, null);
		TextView textView = (TextView) view.findViewById(R.id.patternChildTo);
		switch (childPosition) {
		case 0:
			textView.setText("排列号:" + p.getOrderNum());
			break;
		case 1:
			textView.setText("适用型号:" + p.getModelName());
			break;
		case 2:
			textView.setText("readme:" + p.getReadme());
			break;
		default:
			break;
		}
		view.setOnLongClickListener(new PatternGroupLongClickListener(groupPosition, this, this.context));
		return view;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		Pattern p = ((Map<String, Pattern>)getGroup(groupPosition)).get("group"); // 获取当前项的Pattern实例
		View view = LayoutInflater.from(this.context).inflate(this.groupLayoutId, null);
		TextView textView = (TextView) view.findViewById(R.id.patternGroupTo);
		textView.setText("模板名称:" + p.getPatternName());
		return view;
	}

	
	
	

}
