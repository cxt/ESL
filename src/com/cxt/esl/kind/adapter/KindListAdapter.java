package com.cxt.esl.kind.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.cxt.esl.R;
import com.cxt.esl.kind.domain.Kind;
import com.cxt.esl.kind.listener.KindGroupLongClickListener;

public class KindListAdapter extends SimpleExpandableListAdapter{

	private Context context;
	private int groupLayoutId;
	private int childLayoutId;
	
	
	public KindListAdapter(Context context,
			List<? extends Map<String, Kind>> groupData, int expandedGroupLayout,
			int collapsedGroupLayout, String[] groupFrom, int[] groupTo,
			List<? extends List<? extends Map<String, Kind>>> childData,
			int childLayout, int lastChildLayout, String[] childFrom,
			int[] childTo) {
		super(context, groupData, expandedGroupLayout, collapsedGroupLayout, groupFrom,
				groupTo, childData, childLayout, lastChildLayout, childFrom, childTo);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public KindListAdapter(Context context,
			List<? extends Map<String, Kind>> groupData, int expandedGroupLayout,
			int collapsedGroupLayout, String[] groupFrom, int[] groupTo,
			List<? extends List<? extends Map<String, Kind>>> childData,
			int childLayout, String[] childFrom, int[] childTo) {
		super(context, groupData, expandedGroupLayout, collapsedGroupLayout, groupFrom,
				groupTo, childData, childLayout, childFrom, childTo);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public KindListAdapter(Context context,
			List<? extends Map<String, Kind>> groupData, int groupLayout,
			String[] groupFrom, int[] groupTo,
			List<? extends List<? extends Map<String, Kind>>> childData,
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
		Kind k = ((Map<String, Kind>) getChild(groupPosition, childPosition)).get("child"); // 获取当前项的Pattern实例
		View view = LayoutInflater.from(this.context).inflate(this.childLayoutId, null);
		TextView textView = (TextView) view.findViewById(R.id.kindChildTo);
		switch (childPosition) {
		case 0:
			textView.setText("类别编号:" + k.getKindId());
			break;
		case 1:
			textView.setText("商品类别:" + k.getKindname());
			break;
		case 2:
			textView.setText("备注:" + k.getRemarks());
			break;
		default:
			break;
		}
		view.setOnLongClickListener(new KindGroupLongClickListener(groupPosition, this, this.context));
		return view;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		Kind k = ((Map<String, Kind>)getGroup(groupPosition)).get("group"); // 获取当前项的Pattern实例
		View view = LayoutInflater.from(this.context).inflate(this.groupLayoutId, null);
		TextView textView = (TextView) view.findViewById(R.id.kindGroupTo);
		textView.setText("商品类别:" + k.getKindname());
		return view;
	}

	
	
	

}
