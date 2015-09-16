package com.example.yoo.s1_drawernavi_lib;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Yoo on 2015. 9. 15..
 */
public class Drinks {
    @SerializedName("category")
    String category;
    @SerializedName("product")
    ArrayList<Drink> drinks = new ArrayList<>();

    public Drinks(String category, ArrayList<Drink> drinks) {
        this.category = category;
        this.drinks = drinks;
    }

    public ArrayList<Drink> getDrinkList(){ return drinks; }

    public String getCategory(){ return category; }
}
