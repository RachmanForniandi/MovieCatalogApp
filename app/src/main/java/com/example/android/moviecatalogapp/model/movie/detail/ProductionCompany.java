package com.example.android.moviecatalogapp.model.movie.detail;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

/**
 * Created by Lenovo on 9/21/2017.
 */

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ProductionCompany {

    @SerializedName("id")
    private Long mId;

    @SerializedName("name")
    private String mName;

    public Long getId() {
        return mId;
    }

    public void setmId(Long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
