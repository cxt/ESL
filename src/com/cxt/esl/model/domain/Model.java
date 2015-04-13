package com.cxt.esl.model.domain;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;


public class Model implements Serializable, Parcelable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String modelName;
	private String inch;
	private int width;
	private int height;
	private int rotate;
	private int bpp;
	private String modelNote;
	public Model(String modelName, String inch, int width, int height,
			int rotate, int bpp, String modelNote) {
		super();
		this.modelName = modelName;
		this.inch = inch;
		this.width = width;
		this.height = height;
		this.rotate = rotate;
		this.bpp = bpp;
		this.modelNote = modelNote;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getInch() {
		return inch;
	}

	public void setInch(String inch) {
		this.inch = inch;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getRotate() {
		return rotate;
	}

	public void setRotate(int rotate) {
		this.rotate = rotate;
	}

	public int getBpp() {
		return bpp;
	}

	public void setBpp(int bpp) {
		this.bpp = bpp;
	}

	public String getModelNote() {
		return modelNote;
	}

	public void setModelNote(String modelNote) {
		this.modelNote = modelNote;
	}

	@Override
	public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
    	out.writeString(modelName);
    	out.writeString(inch);
    	out.writeInt(width);
    	out.writeInt(height);
    	out.writeInt(rotate);
    	out.writeInt(bpp);
    	out.writeString(modelNote);
    }

	public static final Parcelable.Creator<Model> CREATOR
            = new Parcelable.Creator<Model>() {
        public Model createFromParcel(Parcel in) {
        	
            return new Model(in.readString(),in.readString(),in.readInt(),in.readInt(),in.readInt(),in.readInt(),in.readString());
        }

        public Model[] newArray(int size) {
            return new Model[size];
        }
    };
    


}

