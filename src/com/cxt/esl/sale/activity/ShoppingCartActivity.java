package com.cxt.esl.sale.activity;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cxt.esl.R;
import com.cxt.esl.sale.adapter.SaleAdapter;
import com.cxt.esl.sale.dao.OrderDao;
import com.cxt.esl.sale.domain.Order;
import com.cxt.esl.sale.domain.SaleItem;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class ShoppingCartActivity extends Activity{

	private List<SaleItem> saleList;
	private SaleAdapter adapter;
	private TextView tvGoodsPrice;
	private EditText etUserPay;
	private EditText etChange;
	private Button checkOutBtn;
	
	private ESLDatebaseHelper helper;
	private OrderDao orderDao;
	
	private float allPrice;
	
	private void init(){
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			orderDao = new OrderDao(helper.getOrderDao());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		saleList = getIntent().getParcelableArrayListExtra("saleList");
		
		tvGoodsPrice = (TextView) findViewById(R.id.t_goods_price);
		etUserPay = (EditText) findViewById(R.id.e_user_pay);
		etChange = (EditText) findViewById(R.id.e_change);
		checkOutBtn = (Button) findViewById(R.id.check_out_btn);
		for(SaleItem s : saleList){
			allPrice += s.getGood().getPresPrice()*s.getCount();
		}
		tvGoodsPrice.setText("商品总价:"+allPrice);
		
		checkOutBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String strPay = etUserPay.getText().toString().trim();
				if(strPay.length() <= 0){
					Toast.makeText(ShoppingCartActivity.this, "输入支付金额", Toast.LENGTH_SHORT).show();
					return;
				}
				if(!strPay.matches("^[0-9]*$")){
					Toast.makeText(ShoppingCartActivity.this, "支付金额必须是数字", Toast.LENGTH_SHORT).show();
					return;
				}
				float pay = Float.valueOf(strPay);
				if(pay < allPrice){
					Toast.makeText(ShoppingCartActivity.this, "支付金额不足", Toast.LENGTH_SHORT).show();
					return;
				}
				etChange.setText(""+(pay - allPrice));
				Order o = new Order();
				o.setAllPrice(allPrice);
				o.setChange(pay - allPrice);
				o.setCreateDate(new Date());
				o.setOperator("超市销售员");
				o.setUserPay(pay);
				String goods = "";
				for(SaleItem s : saleList){
					goods += s.getGood().getPosName() + "__" +  s.getGood().getPresPrice() + "__" + s.getCount()+";";
				}
				o.setGoods(goods);
				try {
					orderDao.insert(o);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				Toast.makeText(ShoppingCartActivity.this, "完成购买！", Toast.LENGTH_SHORT).show();
			}
		});
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shopping_cart);
		init();
		
		adapter = new SaleAdapter(ShoppingCartActivity.this,
				R.layout.sale_item, saleList);
		ListView listView = (ListView) findViewById(R.id.sale_list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new SaleItemClickListener(R.layout.sale_item));
	
	}

	
	private class SaleItemClickListener implements OnItemClickListener{
		private int resourceId;
		SaleItemClickListener(int resourceId){
			this.resourceId = resourceId;
		}
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			view = LayoutInflater.from(ShoppingCartActivity.this).inflate(resourceId, null);
			final SaleItem s = saleList.get(position);
			
			AlertDialog.Builder d = new AlertDialog.Builder(
					ShoppingCartActivity.this);
			d.setTitle("修改购买数量:");
			d.setMessage("输入购买数量:0代表删除该商品");
			d.setCancelable(false);
			d.setNegativeButton("否",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(
								DialogInterface dialog,
								int which) {
						}
					});
			final EditText etCount = new EditText(ShoppingCartActivity.this);
			etCount.setText(s.getCount()+"");
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
								Toast.makeText(ShoppingCartActivity.this, "输入购买数量", Toast.LENGTH_SHORT).show();
								return;
							}
							if(!strCount.matches("^[0-9]*$")){
								Toast.makeText(ShoppingCartActivity.this, "购买数量必须是数字", Toast.LENGTH_SHORT).show();
								return;
							}
							int count = Integer.valueOf(strCount);
							if(count == 0){
								saleList.remove(s);
								Toast.makeText(ShoppingCartActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
							}else{
								saleList.remove(s);
								s.setCount(count);
								saleList.add(s);
								Toast.makeText(ShoppingCartActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
							}
							
							adapter = new SaleAdapter(ShoppingCartActivity.this,
									R.layout.sale_item, saleList);
							ListView listView = (ListView) findViewById(R.id.sale_list);
							listView.setAdapter(adapter);
							for(SaleItem s : saleList){
								allPrice += s.getGood().getPresPrice();
							}
							tvGoodsPrice.setText("商品总价:"+allPrice);
						}
					});
			d.show();
			
		}
		
	}
}
