package com.example.android.moviecatalogapp.model.movie.nowplaying;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;


@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")

public class NowPlaying {

    @SerializedName("dates")
    private Dates mDates;

    @SerializedName("page")
    private Long mPage;

    @SerializedName("results")
    private List<ResultNowPlaying> mResultNowPlaying;

    @SerializedName("total_pages")
    private Long mTotalPages;

    @SerializedName("total_results")
    private Long mTotalResults;



    public Dates getDates() {
        return mDates;
    }

    public void setDates(Dates dates) {
        mDates = dates;
    }

    public Long getPage() {
        return mPage;
    }

    public void setPage(Long page) {
        mPage = page;
    }

    public List<ResultNowPlaying> getResultNowPlaying() {
        return mResultNowPlaying;
    }

    public void setResultNowPlaying(List<ResultNowPlaying> resultNowPlaying) {
        mResultNowPlaying = resultNowPlaying;
    }

    public Long getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(Long totalPages) {
        mTotalPages = totalPages;
    }

    public Long getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(Long totalResults) {
        mTotalResults = totalResults;
    }
}
