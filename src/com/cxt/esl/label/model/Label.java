package com.cxt.esl.label.model;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class Label implements Serializable, Parcelable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long eslId;
	private String goodName;
	private String patternName;
	private int workStatus;
	private long mycode;
	private String modelName;
	
	public Label(long eslId, String goodName, String patternName,
			int workStatus, long mycode, String modelName) {
		super();
		this.eslId = eslId;
		this.goodName = goodName;
		this.patternName = patternName;
		this.workStatus = workStatus;
		this.mycode = mycode;
		this.modelName = modelName;
	}
	public long getEslId() {
		return eslId;
	}
	public void setEslId(long eslId) {
		this.eslId = eslId;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public String getPatternName() {
		return patternName;
	}
	public void setPatternName(String patternName) {
		this.patternName = patternName;
	}
	public int getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(int workStatus) {
		this.workStatus = workStatus;
	}
	public long getMycode() {
		return mycode;
	}
	public void setMycode(long mycode) {
		this.mycode = mycode;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	@Override
	public int describeContents() {
        return 0;
    }
	@Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(eslId);
        out.writeString(goodName);
        out.writeString(patternName);
        out.writeInt(workStatus);
        out.writeLong(mycode);
        out.writeString(modelName);
    }

    public static final Parcelable.Creator<Label> CREATOR
            = new Parcelable.Creator<Label>() {
        public Label createFromParcel(Parcel in) {
        	
            return new Label(in.readLong(),in.readString(),in.readString(),in.readInt(),in.readLong(),in.readString());
        }

        public Label[] newArray(int size) {
            return new Label[size];
        }
    };
    


}
