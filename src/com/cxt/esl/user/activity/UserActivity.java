package com.cxt.esl.user.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.cxt.esl.R;
import com.cxt.esl.user.adapter.UserAdapter;
import com.cxt.esl.user.dao.UserDao;
import com.cxt.esl.user.domain.User;
import com.cxt.esl.user.listener.UserItemClickListener;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class UserActivity extends Activity {
	
	private List<User> userList;
	private ESLDatebaseHelper helper;
	private UserDao userDao;
	private UserAdapter adapter;
	
	private void init(){
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			userDao = new UserDao ( helper.getUserDao());
			userList = userDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		setContentView(R.layout.user);
		
		final EditText userCodeView = (EditText) findViewById(R.id.e_user_code);
		final EditText userNameView = (EditText) findViewById(R.id.e_user_name);
		Button searchBtn = (Button) findViewById(R.id.user_search_btn);
		Button resetBtn = (Button) findViewById(R.id.user_reset_btn);
		Button addBtn = (Button) findViewById(R.id.user_add_btn);
		
		searchBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					String userCode = userCodeView.getText().toString().trim();
					String userName = userNameView.getText().toString().trim();
					userList = userDao.queryForCodeOrName(userCode, userName);
					adapter = new UserAdapter(UserActivity.this,
							R.layout.user_item, userList);
					ListView listView = (ListView) findViewById(R.id.user_list);
					listView.setAdapter(adapter);
					listView.setOnItemClickListener(new UserItemClickListener(UserActivity.this, userList, userDao, adapter));
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (java.sql.SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		resetBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				userCodeView.setText("");
				userNameView.setText("");
				
			}
		});
		
		
		addBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(UserActivity.this, UserAddActivity.class);
				startActivity(intent);
			}
		});
		
		adapter = new UserAdapter(UserActivity.this,
				R.layout.user_item, userList);
		ListView listView = (ListView) findViewById(R.id.user_list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new UserItemClickListener(UserActivity.this, userList, userDao, adapter));
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
				try {
					userList = userDao.queryAll();
				} catch (java.sql.SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				adapter = new UserAdapter(UserActivity.this,
						R.layout.user_item, userList);
				ListView listView = (ListView) findViewById(R.id.user_list);
				listView.setAdapter(adapter);
				listView.setOnItemClickListener(new UserItemClickListener(UserActivity.this, userList, userDao, adapter));
			}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		helper.close();
	}

	
}