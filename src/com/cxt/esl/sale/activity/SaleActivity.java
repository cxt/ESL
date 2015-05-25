package com.cxt.esl.sale.activity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.cxt.esl.R;
import com.cxt.esl.good.adapter.GoodAdapter;
import com.cxt.esl.good.dao.GoodDao;
import com.cxt.esl.good.domain.Good;
import com.cxt.esl.kind.dao.KindDao;
import com.cxt.esl.kind.domain.Kind;
import com.cxt.esl.sale.domain.SaleItem;
import com.cxt.esl.util.arrayAdapter.KindArrayAdapter;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class SaleActivity extends Activity{
	
	private List<Good> goodList;
	private ESLDatebaseHelper helper;
	private GoodDao goodDao;
	
	private KindDao kindDao;
	private List<Kind> kindList;
	private Kind kind;
	
	private GoodAdapter adapter;
	
	private List<SaleItem> saleList;
	
	private void init(){
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			goodDao = new GoodDao ( helper.getGoodDao());
			goodList = goodDao.queryAll();
			
			kindDao = new KindDao(helper.getKindDao());
			kindList = kindDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		saleList = new ArrayList<SaleItem>();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		setContentView(R.layout.sale);
		
		Button searchBtn = (Button) findViewById(R.id.good_search_btn);
		Button resetBtn = (Button) findViewById(R.id.good_reset_btn);
		Button addBtn = (Button) findViewById(R.id.shopping_cart_btn);
		
		final Spinner kindSpin = (Spinner) findViewById(R.id.spin_kind);
		kindSpin.setAdapter(new KindArrayAdapter(this, kindList));
		kindSpin.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				kind = kindList.get(position);
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
			
		});
		
		final EditText barCodeView = (EditText) findViewById(R.id.e_bar_code);
		final EditText posNameView = (EditText) findViewById(R.id.e_pos_name);
		
		searchBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					String barCode = barCodeView.getText().toString().trim();
					String posName = posNameView.getText().toString().trim();
					goodList = goodDao.queryForBarCodeOrPosNameOrStatusOrKindId(barCode, posName, 0, kind==null?-1:kind.getKindId());;
					adapter = new GoodAdapter(SaleActivity.this,
							R.layout.good_item, goodList);
					ListView listView = (ListView) findViewById(R.id.good_list);
					listView.setAdapter(adapter);
					listView.setOnItemClickListener(new SaleItemClickListener(R.layout.good_item));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		resetBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				barCodeView.setText("");
				posNameView.setText("");
				kindSpin.setSelection(0);
			}
		});
		
		addBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SaleActivity.this, ShoppingCartActivity.class);
				intent.putParcelableArrayListExtra("saleList", (ArrayList<? extends Parcelable>) saleList);
				startActivity(intent);
			}
		});
		
		adapter = new GoodAdapter(SaleActivity.this,
				R.layout.good_item, goodList);
		ListView listView = (ListView) findViewById(R.id.good_list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new SaleItemClickListener(R.layout.good_item));
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		try {
			goodList = goodDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		adapter = new GoodAdapter(SaleActivity.this,
				R.layout.good_item, goodList);
		ListView listView = (ListView) findViewById(R.id.good_list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new SaleItemClickListener(R.layout.good_item));
	}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		helper.close();
	}
	
	private class SaleItemClickListener implements OnItemClickListener{
		private int resourceId;
		SaleItemClickListener(int resourceId){
			this.resourceId = resourceId;
		}
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			view = LayoutInflater.from(SaleActivity.this).inflate(resourceId, null);
			final Good g = goodList.get(position);
			
			AlertDialog.Builder d = new AlertDialog.Builder(
					SaleActivity.this);
			d.setTitle("添加该商品到购物车");
			d.setMessage("商品名为："+g.getPosName()+"\n商品现价为："+g.getPresPrice()+"元，\n请输入购买数量:");
			d.setCancelable(false);
			d.setNegativeButton("否",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(
								DialogInterface dialog,
								int which) {
						}
					});
			final EditText etCount = new EditText(SaleActivity.this);
			etCount.setTextSize(15);
			d.setView(etCount);
			d.setPositiveButton("是",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(
								DialogInterface dialog,
								int which) {
							String strCount = etCount.getText().toString();
							if(strCount.length() <= 0){
								Toast.makeText(SaleActivity.this, "输入购买数量", Toast.LENGTH_SHORT).show();
								return;
							}
							if(!strCount.matches("^[0-9]*$")){
								Toast.makeText(SaleActivity.this, "购买数量必须是数字", Toast.LENGTH_SHORT).show();
								return;
							}
							SaleItem item = new SaleItem(g, Integer.valueOf(strCount));
							for(SaleItem e : saleList){
								if(e.getGood() == item.getGood()){
									Toast.makeText(SaleActivity.this, "该商品已经添加", Toast.LENGTH_SHORT).show();
									return;
								}
									
							}
							saleList.add(item);
							Toast.makeText(SaleActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
						}
					});
			d.show();
			
		}
		
	}
	
}
