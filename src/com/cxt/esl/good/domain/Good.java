package com.cxt.esl.good.domain;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;


public class Good implements Serializable, Parcelable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long barCode;
	private String eslName;
	private String posName;
	private float origPrice;
	private float presPrice;
	private String priceUnit;
	private String modiTtime;
	public Good(Long barCode, String eslName, String posName, float origPrice,
			float presPrice, String priceUnit, String modiTtime) {
		super();
		this.barCode = barCode;
		this.eslName = eslName;
		this.posName = posName;
		this.origPrice = origPrice;
		this.presPrice = presPrice;
		this.priceUnit = priceUnit;
		this.modiTtime = modiTtime;
	}

	public Long getBarCode() {
		return barCode;
	}

	public void setBarCode(Long barCode) {
		this.barCode = barCode;
	}

	public String getEslName() {
		return eslName;
	}

	public void setEslName(String eslName) {
		this.eslName = eslName;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public float getOrigPrice() {
		return origPrice;
	}

	public void setOrigPrice(float origPrice) {
		this.origPrice = origPrice;
	}

	public float getPresPrice() {
		return presPrice;
	}

	public void setPresPrice(float presPrice) {
		this.presPrice = presPrice;
	}

	public String getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}

	public String getModiTtime() {
		return modiTtime;
	}

	public void setModiTtime(String modiTtime) {
		this.modiTtime = modiTtime;
	}


	@Override
	public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
    	
    	out.writeLong(barCode);
    	out.writeString(eslName);
    	out.writeString(posName);
    	out.writeFloat(origPrice);
    	out.writeFloat(presPrice);
    	out.writeString(priceUnit);
    	out.writeString(modiTtime);
    }

	public static final Parcelable.Creator<Good> CREATOR
            = new Parcelable.Creator<Good>() {
        public Good createFromParcel(Parcel in) {
        	
            return new Good(in.readLong(), in.readString(), in.readString(), in.readFloat(), in.readFloat(), in.readString(), in.readString());
        }

        public Good[] newArray(int size) {
            return new Good[size];
        }
    };
    


}

