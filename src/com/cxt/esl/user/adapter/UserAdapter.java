package com.cxt.esl.user.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cxt.esl.R;
import com.cxt.esl.user.domain.User;

public class UserAdapter extends ArrayAdapter<User> {

	private int resourceId;
	private static final String STR_USER_CODE = "用户编号:";
	private static final String STR_USER_NAME = "用户名称:";
	private static final String STR_PASSWORD = "用户密码:*****************";
	private static final String STR_STATUS = "状态:";
	private static final String STR_ROLE_NAME = "角色:";
	private static final String STR_CREATE_DATE = "创建时间:";

	public UserAdapter(Context context, int textViewResourceId,
			List<User> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		User u = getItem(position);
		View view;
		ViewHolder viewHolder;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.userCode = (TextView) view.findViewById(R.id.user_code);
			viewHolder.userName = (TextView) view.findViewById(R.id.user_name);
			viewHolder.password = (TextView) view.findViewById(R.id.password);
			viewHolder.status = (TextView) view.findViewById(R.id.status);
			viewHolder.roleName = (TextView) view.findViewById(R.id.role_name);
			viewHolder.createDate = (TextView) view
					.findViewById(R.id.create_date);
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.userCode.setText(STR_USER_CODE + u.getUserCode());
		viewHolder.userName.setText(STR_USER_NAME + u.getUserName());
		viewHolder.password.setText(STR_PASSWORD);
		viewHolder.createDate.setText(STR_CREATE_DATE + u.getCreateDate());
		viewHolder.status.setText(STR_STATUS
				+ (u.getStatus() == 0 ? "Y-启用" : "N冻结"));
		if (u.getRoleName() == 0) {
			viewHolder.roleName.setText(STR_ROLE_NAME + "ADMIN-系统管理员");
		} else if (u.getRoleName() == 1) {
			viewHolder.roleName.setText(STR_ROLE_NAME + "SHOP_MASTER-超市经理");
		} else if (u.getRoleName() == 2) {
			viewHolder.roleName.setText(STR_ROLE_NAME + "SHOP_SALES-超市营业员");
		}
		return view;
	}

	class ViewHolder {

		TextView userCode;
		TextView userName;
		TextView password;
		TextView roleName;
		TextView status;
		TextView createDate;

	}

}
