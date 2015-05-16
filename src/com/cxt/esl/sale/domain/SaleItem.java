package com.cxt.esl.sale.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.cxt.esl.good.domain.Good;

public class SaleItem implements Parcelable{
	private Good good;
	private int count;
	public Good getGood() {
		return good;
	}
	public void setGood(Good good) {
		this.good = good;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public SaleItem(Good good, int count) {
		super();
		this.good = good;
		this.count = count;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeSerializable(good);
		dest.writeInt(count);
		
	}
	
    //3、自定义类型中必须含有一个名称为CREATOR的静态成员，该成员对象要求实现Parcelable.Creator接口及其方法
    public static final Parcelable.Creator<SaleItem> CREATOR = new Parcelable.Creator<SaleItem>() { 
        @Override 
        public SaleItem createFromParcel(Parcel source) { 
            //从Parcel中读取数据
            //此处read顺序依据write顺序
            return new SaleItem((Good) source.readSerializable(),source.readInt()); 
        } 
        @Override 
        public SaleItem[] newArray(int size) { 
                         
            return new SaleItem[size]; 
        } 
                     
    }; 
	
}
