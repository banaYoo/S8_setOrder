package com.example.yoo.s1_drawernavi_lib;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Drink implements Parcelable {
    @SerializedName("name")
    String name;
    @SerializedName("img")
    String imgsource;
    String option;


    public Drink(String name, String imgsource, String option) {
        this.name = name;
        this.imgsource = imgsource;
        this.option = option;
    }

    public String getName(){  return name;  }
    public String getImgsource(){   return imgsource;   }
    public String getOption(){  return option;  }


    private Drink(Parcel source){
        name = source.readString();
        imgsource = source.readString();
        option = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
         dest.writeString(name);
         dest.writeString(imgsource);
         dest.writeString(option);
    }

    public static final Creator<Drink> CREATOR = new Creator<Drink>() {
        @Override
        public Drink createFromParcel(Parcel source) {
            return new Drink(source);
        }

        @Override
        public Drink[] newArray(int size) {
            return new Drink[size];
        }
    };
}
