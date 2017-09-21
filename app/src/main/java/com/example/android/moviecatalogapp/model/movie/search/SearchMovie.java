package com.example.android.moviecatalogapp.model.movie.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

/**
 * Created by Lenovo on 9/19/2017.
 */

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class SearchMovie {


    @SerializedName("page")
    private long mPage;

    @SerializedName("total_results")
    private long mTotalResults;

    @SerializedName("total_pages")
    private long mTotalPages;

    @SerializedName("results")
    private List<ResultSearchMovie> mResults;


    public long getPage() {
        return mPage;
    }

    public void setPage(long page) {
        mPage = page;
    }

    public long getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(long totalResults) {
        mTotalResults = totalResults;
    }

    public long getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(long totalPages) {
        mTotalPages = totalPages;
    }

    public List<ResultSearchMovie> getResults() {
        return mResults;
    }

    public void setResults(List<ResultSearchMovie> results) {
        mResults = results;
    }
}
