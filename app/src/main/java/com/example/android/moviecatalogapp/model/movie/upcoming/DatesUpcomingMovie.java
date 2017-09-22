package com.example.android.moviecatalogapp.model.movie.upcoming;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;


@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class DatesUpcomingMovie {

    @SerializedName("maximum")
    private String mMaximum;

    @SerializedName("minimum")
    private String mMinimum;


    public String getMaximum() {
        return mMaximum;
    }

    public void setMaximum(String maximum) {
        mMaximum = maximum;
    }

    public String getMinimum() {
        return mMinimum;
    }

    public void setMinimum(String minimum) {
        mMinimum = minimum;
    }
}
