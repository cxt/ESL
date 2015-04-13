package com.cxt.esl.kind.domain;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

import com.cxt.esl.pattern.domain.Pattern;

public class Kind implements Serializable, Parcelable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long kindId;
	private String kindName;
	private String remarks;
	
	
	
	public Kind(long kindId, String kindname, String remarks) {
		super();
		this.kindId = kindId;
		this.kindName = kindname;
		this.remarks = remarks;
	}

	public long getKindId() {
		return kindId;
	}

	public void setKindId(long kindId) {
		this.kindId = kindId;
	}

	public String getKindname() {
		return kindName;
	}

	public void setKindname(String kindname) {
		this.kindName = kindname;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
    	out.writeLong(kindId);
    	out.writeString(kindName);
    	out.writeString(remarks);
    }

	public static final Parcelable.Creator<Kind> CREATOR
            = new Parcelable.Creator<Kind>() {
        public Kind createFromParcel(Parcel in) {
        	
            return new Kind(in.readLong(),in.readString(), in.readString());
        }

        public Kind[] newArray(int size) {
            return new Kind[size];
        }
    };
    


}
