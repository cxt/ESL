package com.cxt.esl.model.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.cxt.esl.R;
import com.cxt.esl.model.domain.Model;
import com.cxt.esl.model.listener.ModelGroupLongClickListener;
public class ModelListAdapter extends SimpleExpandableListAdapter{

	private Context context;
	private int groupLayoutId;
	private int childLayoutId;
	
	
	public ModelListAdapter(Context context,
			List<? extends Map<String, Model>> groupData, int expandedGroupLayout,
			int collapsedGroupLayout, String[] groupFrom, int[] groupTo,
			List<? extends List<? extends Map<String, Model>>> childData,
			int childLayout, int lastChildLayout, String[] childFrom,
			int[] childTo) {
		super(context, groupData, expandedGroupLayout, collapsedGroupLayout, groupFrom,
				groupTo, childData, childLayout, lastChildLayout, childFrom, childTo);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public ModelListAdapter(Context context,
			List<? extends Map<String, Model>> groupData, int expandedGroupLayout,
			int collapsedGroupLayout, String[] groupFrom, int[] groupTo,
			List<? extends List<? extends Map<String, Model>>> childData,
			int childLayout, String[] childFrom, int[] childTo) {
		super(context, groupData, expandedGroupLayout, collapsedGroupLayout, groupFrom,
				groupTo, childData, childLayout, childFrom, childTo);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public ModelListAdapter(Context context,
			List<? extends Map<String, Model>> groupData, int groupLayout,
			String[] groupFrom, int[] groupTo,
			List<? extends List<? extends Map<String, Model>>> childData,
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
		Model m = ((Map<String, Model>) getChild(groupPosition, childPosition)).get("child"); // 获取当前项的Pattern实例
		View view = LayoutInflater.from(this.context).inflate(this.childLayoutId, null);
		TextView textView = (TextView) view.findViewById(R.id.modelChildTo);
		switch (childPosition) {
		case 0:
			textView.setText("规格型号:" + m.getModelName());
			break;
		case 1:
			textView.setText("屏幕尺寸:" + m.getInch());
			break;
		case 2:
			textView.setText("型号特征:" + m.getModelNote());
			break;
		default:
			break;
		}
		view.setOnLongClickListener(new ModelGroupLongClickListener(groupPosition, this, this.context));
		return view;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		Model m = ((Map<String, Model>)getGroup(groupPosition)).get("group"); // 获取当前项的Model实例
		View view = LayoutInflater.from(this.context).inflate(this.groupLayoutId, null);
		TextView textView = (TextView) view.findViewById(R.id.modelGroupTo);
		textView.setText("型号特征:" + m.getModelNote());
		return view;
	}

	
	
	

}
