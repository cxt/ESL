package com.cxt.esl.pattern.domain;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class Pattern implements Serializable, Parcelable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int orderNum;
	private String patternName;
	private String readme;
	private String modelName;
	private String code;

	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getPatternName() {
		return patternName;
	}
	public void setPatternName(String patternName) {
		this.patternName = patternName;
	}
	public String getReadme() {
		return readme;
	}
	public void setReadme(String readme) {
		this.readme = readme;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public int describeContents() {
        return 0;
    }

    public Pattern(int orderNum, String patternName, String readme,
			String modelName, String code) {
		super();
		this.orderNum = orderNum;
		this.patternName = patternName;
		this.readme = readme;
		this.modelName = modelName;
		this.code = code;
	}
    @Override
    public void writeToParcel(Parcel out, int flags) {
    	out.writeInt(orderNum);
    	out.writeString(patternName);
    	out.writeString(readme);
    	out.writeString(modelName);
    	out.writeString(code);
    }

	public static final Parcelable.Creator<Pattern> CREATOR
            = new Parcelable.Creator<Pattern>() {
        public Pattern createFromParcel(Parcel in) {
        	
            return new Pattern(in.readInt(), in.readString(), in.readString(), in.readString(), in.readString());
        }

        public Pattern[] newArray(int size) {
            return new Pattern[size];
        }
    };
    


}
