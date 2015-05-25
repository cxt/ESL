package com.cxt.esl.good.activity;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
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
import com.cxt.esl.good.dao.GoodDao;
import com.cxt.esl.good.dao.GoodUpdateHistoryDao;
import com.cxt.esl.good.domain.Good;
import com.cxt.esl.good.domain.GoodUpdateHistory;
import com.cxt.esl.kind.dao.KindDao;
import com.cxt.esl.kind.domain.Kind;
import com.cxt.esl.util.CallbackBundle;
import com.cxt.esl.util.DateTimePickerDialog;
import com.cxt.esl.util.OpenFileDialog;
import com.cxt.esl.util.arrayAdapter.KindArrayAdapter;
import com.cxt.esl.util.db.ESLDatebaseHelper;

public class GoodUpdateActivity extends Activity {

	static private int openfileDialogId = 0;

	private ESLDatebaseHelper helper;
	private GoodDao goodDao;
	private GoodUpdateHistoryDao guhDao;
	private int membOwerPos;
	private int priceDownFlagPos;
	SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private KindDao kindDao;
	private List<Kind> kindList;
	private Kind kind;
	
	private EditText etBarCode;
	private EditText etGoodBarCode;
	private EditText etPosName;
	private EditText etEslName;
	private EditText etOrigPrice;
	private EditText etPresPrice;
	private EditText etStock;
	private EditText etMembPrice;
	private EditText etMembRate;
	private EditText etModel;
	private EditText etPriceUnit;
	private EditText etSalable;
	private EditText etSaled;
	private EditText etGoodsDesc;
	private EditText etProdArea;
	private EditText etPromote1;
	private EditText etPromote2;
	private EditText etPromoteStart;
	private EditText etPromoteEnd;
	private EditText etRemarks;
	private EditText etImgSrc;

	private Spinner spinKind;
	private Spinner spinPriceDownFlag;
	private Spinner spinMembOwner;

	private Good good;

	private void init() {
		Intent intent = getIntent();
		good = (Good) intent.getSerializableExtra("good");
		try {
			helper = ESLDatebaseHelper.getHelper(this);
			goodDao = new GoodDao(helper.getGoodDao());
			guhDao = new GoodUpdateHistoryDao(helper.getGoodUpdateHistoryDao());
			kindDao = new KindDao(helper.getKindDao());
			kindList = kindDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		etBarCode = (EditText) findViewById(R.id.e_bar_code);
		etGoodBarCode = (EditText) findViewById(R.id.e_good_bar_code);
		etPosName = (EditText) findViewById(R.id.e_pos_name);
		etEslName = (EditText) findViewById(R.id.e_esl_name);
		etOrigPrice = (EditText) findViewById(R.id.e_orig_price);
		etPresPrice = (EditText) findViewById(R.id.e_pres_price);
		etStock = (EditText) findViewById(R.id.e_stock);
		etMembPrice = (EditText) findViewById(R.id.e_memb_price);
		etMembRate = (EditText) findViewById(R.id.e_memb_rate);
		etModel = (EditText) findViewById(R.id.e_model);
		etPriceUnit = (EditText) findViewById(R.id.e_price_unit);
		etSalable = (EditText) findViewById(R.id.e_salable);
		etSaled = (EditText) findViewById(R.id.e_saled);
		etGoodsDesc = (EditText) findViewById(R.id.e_goods_desc);
		etProdArea = (EditText) findViewById(R.id.e_prod_area);
		etPromote1 = (EditText) findViewById(R.id.e_promote1);
		etPromote2 = (EditText) findViewById(R.id.e_promote2);
		etPromoteStart = (EditText) findViewById(R.id.e_promote_start_time);
		etPromoteEnd = (EditText) findViewById(R.id.e_promote_end_time);
		etRemarks = (EditText) findViewById(R.id.e_remarks);
		etImgSrc = (EditText) findViewById(R.id.e_img_src);

		spinKind = (Spinner) findViewById(R.id.spin_kind);
		spinPriceDownFlag = (Spinner) findViewById(R.id.spin_price_down_flag);
		spinMembOwner = (Spinner) findViewById(R.id.spin_memb_owner);

		etBarCode.setText(good.getBarCode());
		etGoodBarCode.setText(good.getGoodBarCode());
		etPosName.setText(good.getPosName());
		etEslName.setText(good.getEslName());
		etOrigPrice.setText(good.getOrigPrice()+"");
		etPresPrice.setText(good.getPresPrice()+"");
		etStock.setText(good.getStock()+"");
		etMembPrice.setText(good.getMembPrice()+"");
		etMembRate.setText(good.getMembRate()+"");
		etModel.setText(good.getModel());
		etPriceUnit.setText(good.getPriceUnit()+"");
		etSalable.setText(good.getSalable()+"");
		etSaled.setText(good.getSaled()+"");
		etGoodsDesc.setText(good.getGoodsDesc());
		etProdArea.setText(good.getProdArea());
		etPromote1.setText(good.getPromote1());
		etPromote2.setText(good.getPromote2());
		etPromoteStart.setText(good.getPromoteStartTime()==null?"": sdf.format(good.getPromoteStartTime()));
		etPromoteEnd.setText(good.getPromoteEndTime()==null?"":sdf.format(good.getPromoteEndTime()));
		etRemarks.setText(good.getRemarks());
		etImgSrc.setText(good.getImgUrl());
		
		spinKind.setAdapter(new KindArrayAdapter(this, kindList));
		spinKind.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				kind = kindList.get(position);
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
			
		});
		
		spinPriceDownFlag.setSelection(good.getPriceDownFlag());
		spinMembOwner.setSelection(good.getMembOwner());
		spinKind.setSelection(this.findPosInKindList(kindList, good.getKindId()));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.good_update);
		init();

		etImgSrc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(openfileDialogId);
			}
		});

		String[] str = { "不点亮", "点亮" };
		ArrayAdapter<String> priceDFAda = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str);
		spinPriceDownFlag.setAdapter(priceDFAda);
		spinPriceDownFlag
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						priceDownFlagPos = position;
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
					}

				});

		ArrayAdapter<String> membOAda = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, str);
		spinMembOwner.setAdapter(membOAda);
		spinMembOwner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				membOwerPos = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}

		});

		Button btnUpd = (Button) findViewById(R.id.good_update_sure_btn);
		Button btnCancel = (Button) findViewById(R.id.good_update_cancel_btn);

		etPromoteStart.setOnClickListener(new DateTimeOnClick(etPromoteStart));
		etPromoteEnd.setOnClickListener(new DateTimeOnClick(etPromoteEnd));

		btnUpd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String strBarCode = etBarCode.getText().toString().trim();
				String strEslName = etEslName.getText().toString().trim();
				String strGoodBarCode = etGoodBarCode.getText().toString()
						.trim();
				String strGoodDesc = etGoodsDesc.getText().toString().trim();
				String strMembPrice = etMembPrice.getText().toString().trim();
				String strMembRate = etMembRate.getText().toString().trim();
				String strModel = etModel.getText().toString().trim();
				String strOrigPrice = etOrigPrice.getText().toString().trim();
				String strPosName = etPosName.getText().toString().trim();
				String strPresPrice = etPresPrice.getText().toString().trim();
				String strPriceUnit = etPriceUnit.getText().toString().trim();
				String strProdArea = etProdArea.getText().toString().trim();
				String strPromote1 = etPromote1.getText().toString().trim();
				String strPromote2 = etPromote2.getText().toString().trim();
				String strPromoteEnd = etPromoteEnd.getText().toString().trim();
				String strPromoteStart = etPromoteStart.getText().toString()
						.trim();
				String strRemarks = etRemarks.getText().toString().trim();
				String strSalable = etSalable.getText().toString().trim();
				String strSaled = etSaled.getText().toString().trim();
				String strStock = etStock.getText().toString().trim();
				String strImgSrc = etImgSrc.getText().toString().trim();

				if(strBarCode.length() == 0 || !strBarCode.matches("^[0-9]*$")){
					Toast.makeText(GoodUpdateActivity.this, "商品条码填写有误！", Toast.LENGTH_SHORT).show();
					return;
				}
				if(strGoodBarCode.length() == 0 && !strGoodBarCode.matches("^\\d{12}$")){
					Toast.makeText(GoodUpdateActivity.this, "显示条码填写有误！", Toast.LENGTH_SHORT).show();
					return;
				}
				if(strPosName.length() == 0){
					Toast.makeText(GoodUpdateActivity.this, "商品名称填写有误！", Toast.LENGTH_SHORT).show();
					return;
				}
				if(strEslName.length() == 0){
					Toast.makeText(GoodUpdateActivity.this, "显示名称填写有误！", Toast.LENGTH_SHORT).show();
					return;
				}
				
				if(strOrigPrice.length() > 0 && !strOrigPrice.matches("^\\d+(\\.\\d+)?$")){
					Toast.makeText(GoodUpdateActivity.this, "原价填写有误！", Toast.LENGTH_SHORT).show();
					return;
				}
				
				if(strMembPrice.length() > 0 && !strMembPrice.matches("^\\d+(\\.\\d+)?$")){
					Toast.makeText(GoodUpdateActivity.this, "会员价填写有误！", Toast.LENGTH_SHORT).show();
					return;
				}
				if(strOrigPrice.length() > 0 && !strOrigPrice.matches("^\\d+(\\.\\d+)?$")){
					Toast.makeText(GoodUpdateActivity.this, "原价填写有误！", Toast.LENGTH_SHORT).show();
					return;
				}
				if(strMembRate.length() > 0 && !strMembRate.matches("^[0-9]*$")){
					Toast.makeText(GoodUpdateActivity.this, "折扣填写有误！", Toast.LENGTH_SHORT).show();
					return;
				}
				if(strStock.length() > 0 && !strStock.matches("^[0-9]*$")){
					Toast.makeText(GoodUpdateActivity.this, "库存填写有误！", Toast.LENGTH_SHORT).show();
					return;
				}
				if(strSalable.length() > 0 && !strSalable.matches("^[0-9]*$")){
					Toast.makeText(GoodUpdateActivity.this, "上架数量填写有误！", Toast.LENGTH_SHORT).show();
					return;
				}
				if(strSaled.length() > 0 && !strSaled.matches("^[0-9]*$")){
					Toast.makeText(GoodUpdateActivity.this, "已售数量填写有误！", Toast.LENGTH_SHORT).show();
					return;
				}
				float origPrice = strOrigPrice.length() > 0 ? Float
						.valueOf(strOrigPrice) : 0.00f;
				float presPrice = strPresPrice.length() > 0 ? Float
						.valueOf(strPresPrice) : 0.00f;
				float membPrice = strMembPrice.length() > 0 ? Float
						.valueOf(strMembPrice) : 0.00f;
				int stock = strStock.length() > 0 ? Integer.valueOf(strStock)
						: 0;
				int membRate = strMembRate.length() > 0 ? Integer
						.valueOf(strMembRate) : 0;
				int salable = strSalable.length() > 0 ? Integer
						.valueOf(strSalable) : 0;
				int saled = strSaled.length() > 0 ? Integer.valueOf(strSaled)
						: 0;
				int membOwner = membOwerPos;
				int priceDownFlag = priceDownFlagPos;

				Good g = new Good();
				GoodUpdateHistory guh = new GoodUpdateHistory();
				g.setGoodsId(good.getGoodsId());
				guh.setGoodsId(good.getGoodsId());
				if (strPromoteStart.length() > 0) {
					try {
						Date start = sdf.parse(strPromoteStart);
						g.setPromoteStartTime(start);
						guh.setPromoteStartTime(start);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if (strPromoteEnd.length() > 0) {
					try {
						Date end = sdf.parse(strPromoteEnd);
						g.setPromoteEndTime(end);
						guh.setPromoteEndTime(end);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if(kind != null){
					g.setKindId(kind.getKindId());
					guh.setKindId(kind.getKindId());
				}
				g.setBarCode(strBarCode);
				g.setEslName(strEslName);
				g.setGoodBarCode(strGoodBarCode);
				g.setGoodsDesc(strGoodDesc);
				g.setMembPrice(membPrice);
				g.setMembRate(membRate);
				g.setModel(strModel);
				g.setOrigPrice(origPrice);
				g.setPosName(strPosName);
				g.setPresPrice(presPrice);
				g.setPriceUnit(strPriceUnit);
				g.setProdArea(strProdArea);
				g.setPromote1(strPromote1);
				g.setPromote2(strPromote2);
				g.setRemarks(strRemarks);
				g.setSalable(salable);
				g.setSaled(saled);
				g.setStock(stock);
				g.setMembOwner(membOwner);
				g.setPriceDownFlag(priceDownFlag);
				g.setImgUrl(strImgSrc);

				guh.setBarCode(strBarCode);
				guh.setEslName(strEslName);
				guh.setGoodBarCode(strGoodBarCode);
				guh.setGoodsDesc(strGoodDesc);
				guh.setMembPrice(membPrice);
				guh.setMembRate(membRate);
				guh.setModel(strModel);
				guh.setOrigPrice(origPrice);
				guh.setPosName(strPosName);
				guh.setPresPrice(presPrice);
				guh.setPriceUnit(strPriceUnit);
				guh.setProdArea(strProdArea);
				guh.setPromote1(strPromote1);
				guh.setPromote2(strPromote2);
				guh.setRemarks(strRemarks);
				guh.setSalable(salable);
				guh.setSaled(saled);
				guh.setStock(stock);
				guh.setMembOwner(membOwner);
				guh.setPriceDownFlag(priceDownFlag);
				guh.setImgUrl(strImgSrc);
				guh.setStatus(1);
				guh.setUpdTime(new Date());
				guh.setReason("esl和商品关联操作");
				try {
					goodDao.update(g);
					guhDao.insert(guh);
				} catch (SQLException e1) {
					Log.e(GoodUpdateActivity.class.getSimpleName(), e1.getMessage(),e1);
				}
				
				Toast.makeText(GoodUpdateActivity.this, "更新成功!",
						Toast.LENGTH_SHORT).show();
				
				// 返回上一个Activity
				Runtime runtime = Runtime.getRuntime();
				try {
					runtime.exec("input keyevent " + KeyEvent.KEYCODE_BACK);
				} catch (IOException e) {
					e.printStackTrace();
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

	private final class DateTimeOnClick implements OnClickListener {
		EditText datetime;

		DateTimeOnClick(EditText datetime) {
			this.datetime = datetime;
		}

		@Override
		public void onClick(View v) {
			DateTimePickerDialog dateTimePicKDialog = new DateTimePickerDialog(
					GoodUpdateActivity.this);
			dateTimePicKDialog.dateTimePicKDialog(datetime, 0);
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		if (id == openfileDialogId) {
			Map<String, Integer> images = new HashMap<String, Integer>();
			// 下面几句设置各文件类型的图标， 需要你先把图标添加到资源文件夹
			images.put(OpenFileDialog.sRoot, R.drawable.filedialog_root); // 根目录图标
			images.put(OpenFileDialog.sParent, R.drawable.filedialog_folder_up); // 返回上一层的图标
			images.put(OpenFileDialog.sFolder, R.drawable.filedialog_folder); // 文件夹图标
			images.put("wav", R.drawable.filedialog_wavfile); // wav文件图标
			images.put(OpenFileDialog.sEmpty, R.drawable.filedialog_root);
			Dialog dialog = OpenFileDialog.createDialog(id, this, "打开文件",
					new CallbackBundle() {
						@Override
						public void callback(Bundle bundle) {
							String filepath = bundle.getString("path");
							etImgSrc.setText(filepath); // 把文件路径显示在标题上
						}
					}, "", images);
			return dialog;
		}
		return null;
	}
	
	private int findPosInKindList(List<Kind> list,int id){
		int size = 0;
		for (Kind kind : list) {
			if(kind.getKindId() == id)
				return size;
			size++;
		}
		return 0;
	}

}
