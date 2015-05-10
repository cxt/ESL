package com.cxt.esl.label.adapter;

import java.util.List;

import com.cxt.esl.R;
import com.cxt.esl.label.domain.Label;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class LabelAdapter extends ArrayAdapter<Label> {

	private int resourceId;
	private static final String STR_ESL_ID = "标签编号:";
	private static final String STR_GOODS__NAME = "绑定商品:";
	private static final String STR_PATTERN__NAME = "模板:";
	private static final String STR_WORK__STATUS = "工作状态:";
	private static final String STR_MYCODE = "用户自定义编号:";
	private static final String STR_MODEL_NAME = "型号:";
	
	public LabelAdapter(Context context, int textViewResourceId,
			List<Label> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Label label = getItem(position);
		View view;
		ViewHolder viewHolder;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.eslId = (TextView) view.findViewById(R.id.esl_id);
			viewHolder.goodsName = (TextView) view.findViewById(R.id.goods_name);
			viewHolder.modelName = (TextView) view.findViewById(R.id.model_name);
			viewHolder.mycode = (TextView) view.findViewById(R.id.mycode);
			viewHolder.patternName = (TextView) view.findViewById(R.id.pattern_name);
			viewHolder.workStatus = (TextView) view.findViewById(R.id.work_status);
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.eslId.setText(STR_ESL_ID+label.getEslId());
		viewHolder.goodsName.setText(STR_GOODS__NAME+label.getGoodsId());
		viewHolder.modelName.setText(STR_MODEL_NAME+label.getModelId());
		viewHolder.mycode.setText(STR_MYCODE+label.getMycode());
		viewHolder.patternName.setText(STR_PATTERN__NAME+label.getPatternId());
		viewHolder.workStatus.setText(STR_WORK__STATUS+(label.getWorkStatus()==0?"工作中":"停用"));
		
		return view;
	}
	
	class ViewHolder {
		
		TextView eslId;
		TextView goodsName;
		TextView patternName;
		TextView workStatus;
		TextView mycode;
		TextView modelName;
		
	}

}
