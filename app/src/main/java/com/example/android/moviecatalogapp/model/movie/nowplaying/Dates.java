package com.example.android.moviecatalogapp.model.movie.nowplaying;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;


@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")

public class Dates {
    @SerializedName("maximum")
    private String mMaximum;

    @SerializedName("minimum")
    private String mMinimum;


    public String getMaximum() {
        return mMaximum;
    }

    public void setmMaximum(String maximum) {
        mMaximum = maximum;
    }

    public String getMinimum() {
        return mMinimum;
    }

    public void setMinimum(String minimum) {
        mMinimum = minimum;
    }
}
