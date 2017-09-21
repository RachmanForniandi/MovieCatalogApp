package com.example.android.moviecatalogapp.model.movie.upcoming;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;


@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")

public class UpComingMovie {

    @SerializedName("dates")
    private DatesUpComingMovie mDatesUpComingMovie;

    @SerializedName("page")
    private Long mPage;

    @SerializedName("results")
    private List<ResultUpComingMovie> mResultUpComingMovies;

    @SerializedName("total_pages")
    private Long mTotalPages;

    @SerializedName("total_results")
    private Long mTotalResults;



    public DatesUpComingMovie getDates() {
        return mDatesUpComingMovie;
    }

    public void setDates(DatesUpComingMovie datesUpComingMovie) {
        mDatesUpComingMovie = datesUpComingMovie;
    }

    public Long getPage() {
        return mPage;
    }

    public void setPage(Long page) {
        mPage = page;
    }

    public List<ResultUpComingMovie> getResults() {
        return mResultUpComingMovies;
    }

    public void setResults(List<ResultUpComingMovie> resultUpComingMovies) {
        mResultUpComingMovies = resultUpComingMovies;
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
