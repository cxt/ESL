package com.cxt.esl.user.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cxt.esl.R;
import com.cxt.esl.main.activity.MainActivity;
import com.cxt.esl.user.dao.UserDao;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class LoginActivity extends Activity {
	
	private ESLDatebaseHelper helper;
	private UserDao userDao;
	
	private void init(){
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			userDao = new UserDao ( helper.getUserDao());
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
		setContentView(R.layout.login);
		
		final EditText userCodeView = (EditText) findViewById(R.id.e_user_code);
		final EditText passwordView = (EditText) findViewById(R.id.e_password);
		Button loginBtn = (Button) findViewById(R.id.login_btn);
		Button resetBtn = (Button) findViewById(R.id.login_reset_btn);
		
		loginBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					String userCode = userCodeView.getText().toString().trim();
					String password = passwordView.getText().toString().trim();
					if(!userDao.findUser(userCode)){
						Toast.makeText(LoginActivity.this, "”√ªß’À∫≈≤ª¥Ê‘⁄", Toast.LENGTH_SHORT).show();
					}else if(!userDao.findUser(userCode, password)){
						Toast.makeText(LoginActivity.this, "√‹¬Î¥ÌŒÛ", Toast.LENGTH_SHORT).show();
					}else if(userDao.findUser(userCode, password)){
						Intent intent = new Intent(LoginActivity.this, MainActivity.class);
						startActivity(intent);
					}
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
				passwordView.setText("");
				
			}
		});
		
		
	}
	

	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		helper.close();
	}

	
}