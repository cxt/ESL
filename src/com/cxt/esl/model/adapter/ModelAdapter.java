package com.cxt.esl.model.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.cxt.esl.R;
import com.cxt.esl.model.domain.Model;

public class ModelAdapter extends ArrayAdapter<Model>{

	
	private int resourceId;
	private static final String STR_MODEL_ID = "编号:";
	private static final String STR_MODEL_NAME = "规格型号:";
	private static final String STR_INCH = "屏幕尺寸:";
	private static final String STR_ESL_WIDTH = "宽度:";
	private static final String STR_ESL_HEIGHT = "高度:";
	private static final String STR_RORATE = "翻转角度:";
	private static final String STR_BPP = "每个像素所占位:";
	private static final String STR_MODEL_NOTE = "每个像素所占位:";
	
	public ModelAdapter(Context context, int textViewResourceId,
			List<Model> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Model m = getItem(position);
		View view;
		ViewHolder viewHolder;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.modelId = (TextView) view.findViewById(R.id.model_id);
			viewHolder.modelName = (TextView) view.findViewById(R.id.model_name);
			viewHolder.bpp = (TextView) view.findViewById(R.id.bpp);
			viewHolder.eslHeight = (TextView) view.findViewById(R.id.esl_height);
			viewHolder.eslWidth = (TextView) view.findViewById(R.id.esl_width);
			viewHolder.inch = (TextView) view.findViewById(R.id.inch);
			viewHolder.rorate = (TextView) view.findViewById(R.id.rotate);
			viewHolder.modelNote = (TextView) view.findViewById(R.id.model_note);
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.bpp.setText(STR_BPP+m.getBpp());
		viewHolder.eslHeight.setText(STR_ESL_HEIGHT+m.getEslHeight());
		viewHolder.eslWidth.setText(STR_ESL_WIDTH+m.getEslWidth());
		viewHolder.inch.setText(STR_INCH+m.getInch());
		viewHolder.modelId.setText(STR_MODEL_ID+m.getModelId());
		viewHolder.modelName.setText(STR_MODEL_NAME+m.getModelName());
		viewHolder.modelNote.setText(STR_MODEL_NOTE+m.getModelNote());
		viewHolder.rorate.setText(STR_RORATE+m.getRotate());
		
		return view;
	}
	
	class ViewHolder {
		
		TextView modelId;
		TextView modelName;
		TextView inch;
		TextView eslWidth;
		TextView eslHeight;
		TextView rorate;
		TextView bpp;
		TextView modelNote;
		
	}
	

}
