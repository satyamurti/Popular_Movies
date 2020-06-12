package com.mrspd.moviesappstage1.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
///////////////////////////////////////////////////////////////////////////
// Created by Satyamurti Doddini with ❤  only for udacity
///////////////////////////////////////////////////////////////////////////
public class Review {

    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("content")
    @Expose
    private String Content;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
