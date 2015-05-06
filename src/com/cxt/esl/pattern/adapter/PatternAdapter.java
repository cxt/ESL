package com.cxt.esl.pattern.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cxt.esl.R;
import com.cxt.esl.pattern.domain.Pattern;


public class PatternAdapter extends ArrayAdapter<Pattern>{

	
	private int resourceId;
	private static final String STR_PATTERN_ID = "±‡∫≈:";
	private static final String STR_ORDER__NUM = "≈≈¡–∫≈:";
	private static final String STR_PATTERN__NAME = "ƒ£∞Â√˚≥∆:";
	private static final String STR_MODEL = "  ”√–Õ∫≈:";
	private static final String STR_README = "README:";
	
	public PatternAdapter(Context context, int textViewResourceId,
			List<Pattern> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Pattern p = getItem(position);
		View view;
		ViewHolder viewHolder;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.patternId = (TextView) view.findViewById(R.id.pattern_id);
			viewHolder.patternName = (TextView) view.findViewById(R.id.pattern_name);
			viewHolder.orderNum = (TextView) view.findViewById(R.id.order_num);
			viewHolder.model = (TextView) view.findViewById(R.id.model_name);
			viewHolder.readme = (TextView) view.findViewById(R.id.readme);
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.patternId.setText(STR_PATTERN_ID+p.getPatternId());
		viewHolder.patternName.setText(STR_PATTERN__NAME+p.getPatternName());
		viewHolder.orderNum.setText(STR_ORDER__NUM+p.getOrderNum());
		viewHolder.model.setText(STR_MODEL+p.getModel());
		viewHolder.readme.setText(STR_README+p.getReadme());
		
		return view;
	}
	
	class ViewHolder {
		
		TextView patternId;
		TextView orderNum;
		TextView patternName;
		TextView model;
		TextView readme;
		
	}
	

}
