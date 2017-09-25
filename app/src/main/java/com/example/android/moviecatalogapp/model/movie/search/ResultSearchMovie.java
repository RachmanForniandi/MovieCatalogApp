package com.example.android.moviecatalogapp.model.movie.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Lenovo on 9/19/2017.
 */
@SuppressWarnings("unused")

public class ResultSearchMovie {

    @SerializedName("vote_count")
    private long mVoteCount;

    @SerializedName("id")
    private long mId;

    @SerializedName("video")
    private Boolean mVideo;

    @SerializedName("vote_average")
    private Long mVoteAverage;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("popularity")
    private Double mPopularity;

    @SerializedName("poster_path")
    private String mPosterPath;

    @SerializedName("original_language")
    private String mOriginalLanguage;

    @SerializedName("original_title")
    private String mOriginalTitle;

    @SerializedName("genre_ids")
    private List<Long> mGenreIds;

    @SerializedName("backdrop_path")
    private String mBackdropPath;

    @SerializedName("adult")
    private Boolean mAdult;

    @SerializedName("overview")
    private String mOverview;

    @SerializedName("release_date")
    private String mReleaseDate;


    public long getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(long voteCount) {
        mVoteCount = voteCount;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public Boolean getVideo() {
        return mVideo;
    }

    public void setVideo(Boolean video) {
       mVideo = video;
    }

    public Long getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(Long voteAverage) {
        mVoteAverage = voteAverage;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(Double popularity) {
        mPopularity = popularity;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        mOriginalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    public List<Long> getGenreIds() {
        return mGenreIds;
    }

    public void setGenreIds(List<Long> genreIds) {
        mGenreIds = genreIds;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public Boolean getAdult() {
        return mAdult;
    }

    public void setAdult(Boolean adult) {
        mAdult = adult;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }
}
