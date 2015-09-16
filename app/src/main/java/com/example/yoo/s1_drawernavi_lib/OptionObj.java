package com.example.yoo.s1_drawernavi_lib;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Yoo on 2015. 8. 18..
 */
public class OptionObj {
    @SerializedName("category")
    String c_name;
    @SerializedName("detail")
    ArrayList<String> o_name = new ArrayList<>();

    public OptionObj(String c, ArrayList<String> o){
        c_name = c;
        o_name = o;
    }

    public String getC_name(){  return c_name;  }

    public ArrayList<String> getO_name(){   return o_name;  }

}
