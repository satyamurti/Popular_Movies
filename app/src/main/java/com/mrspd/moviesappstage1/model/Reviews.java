package com.mrspd.moviesappstage1.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
///////////////////////////////////////////////////////////////////////////
// Created by Satyamurti Doddini with ❤  only for udacity
///////////////////////////////////////////////////////////////////////////
public  class Reviews {
    @SerializedName("results")
    @Expose
    private List<Review> reviews = null;
    @SerializedName("total_pages")
    @Expose
    private Integer TotalPages;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("page")
    @Expose
    private Integer page;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Integer getTotalPages() {
        return TotalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.TotalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }
}
