package com.cxt.esl.user.activity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cxt.esl.R;
import com.cxt.esl.user.dao.UserDao;
import com.cxt.esl.user.domain.User;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class UserAddActivity  extends Activity{

	private ESLDatebaseHelper helper;
	private UserDao userDao;
	
	private EditText etUserCode ;
	private EditText etUserName;
	private EditText etPassword;
	private EditText etPasswordSure;
	
	private Spinner spinStatus;
	private Spinner spinRoleName;
	
	private int statusPos;
	private int roleNamePos;
	
	
	private void init(){
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			userDao = new UserDao ( helper.getUserDao());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		etUserCode = (EditText) findViewById(R.id.e_user_code);
		etUserName = (EditText) findViewById(R.id.e_user_name);
		etPassword = (EditText) findViewById(R.id.e_paasword);
		etPasswordSure = (EditText) findViewById(R.id.e_paasword_sure);

		spinStatus = (Spinner) findViewById(R.id.spin_status);
		spinRoleName = (Spinner) findViewById(R.id.spin_role_name);
		
		String[] staArr = {"Y-启用","N-冻结"};
		ArrayAdapter<String> workStaAda = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, staArr);
		spinStatus.setAdapter(workStaAda);
		spinStatus.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				statusPos = position;
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		
		});
		
		String[]  roleNameArr = {"ADMIN-系统管理员","SHOP_MASTER-超市经理","SHOP_SALES-超市营业员"};
		ArrayAdapter<String> roleNameAda = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, roleNameArr);
		spinRoleName.setAdapter(roleNameAda);
		spinRoleName.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				roleNamePos = position;
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		
		});
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_add);
		init();
		
		
		Button btnAdd = (Button) findViewById(R.id.user_add_sure_btn);
		Button btnCancel = (Button) findViewById(R.id.user_add_cancel_btn);
		
		btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String userCode = etUserCode.getText().toString().trim();
				String userName = etUserName.getText().toString().trim();
				String password = etPassword.getText().toString().trim();
				String passwordSure = etPasswordSure.getText().toString().trim();
				
				if(userCode.length() <= 0){
					Toast.makeText(UserAddActivity.this, "用户编号不能为空!", Toast.LENGTH_SHORT).show();
					return ;
				}
				try {
					if(userDao.findUser(userCode)){
						Toast.makeText(UserAddActivity.this, "用户编号存在!", Toast.LENGTH_SHORT).show();
						return ;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(userName.length() <= 0){
					Toast.makeText(UserAddActivity.this, "用户名不能为空!", Toast.LENGTH_SHORT).show();
					return ;
				}
				if(password.length() <= 0){
					Toast.makeText(UserAddActivity.this, "密码不能为空!", Toast.LENGTH_SHORT).show();
					return ;
				}
				if(!password.equals(passwordSure)){
					Toast.makeText(UserAddActivity.this, "密码两次输入不一致", Toast.LENGTH_SHORT).show();
					return ;
				}
				
				
				
				User u = new User();
				u.setCreateBy(0);
				u.setCreateDate(new Date());
				u.setLastUpdateBy(0);
				u.setLastUpdateDate(new Date());
				u.setPassword(password);
				u.setRoleName(roleNamePos);
				u.setStatus(statusPos);
				u.setUserCode(userCode);
				u.setUserName(userName);
				try {
					userDao.insert(u);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				Toast.makeText(UserAddActivity.this, "添加成功!", Toast.LENGTH_SHORT).show();
				
				
				// 返回上一个Activity
				Runtime runtime = Runtime.getRuntime();
				try {
					runtime.exec("input keyevent " + KeyEvent.KEYCODE_BACK);
				} catch (IOException e) {
					Log.e(UserAddActivity.class.getSimpleName(), e.getMessage(), e);
				}
				
			}
		});
		
		btnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 返回上一个Activity
				Runtime runtime = Runtime.getRuntime();
				try {
					runtime.exec("input keyevent " + KeyEvent.KEYCODE_BACK);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		helper.close();
	}

}


