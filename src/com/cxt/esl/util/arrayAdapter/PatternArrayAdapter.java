package com.cxt.esl.util.arrayAdapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cxt.esl.pattern.domain.Pattern;


public class PatternArrayAdapter extends BaseAdapter {
	private List<Pattern> mList;
	private Context mContext;

	public PatternArrayAdapter(Context pContext, List<Pattern> pList) {
		this.mContext = pContext;
		this.mList = pList;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
    /**
     * 下面是重要代码
     */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater _LayoutInflater=LayoutInflater.from(mContext);
		convertView=_LayoutInflater.inflate(android.R.layout.simple_spinner_item, null);
		if(convertView!=null && mList != null)
		{
			TextView _TextView1=(TextView)convertView.findViewById(android.R.id.text1);
			_TextView1.setText(mList.get(position).getPatternName());
		}
		return convertView;
	}
}
