package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.web.JsonPath;

import java.util.List;
import java.util.Map;

public class MarvelComic {

    private String title;
    private Long marvel_id;
    private String img_url;
    private String extension;

    public MarvelComic() {
    }

    public MarvelComic(String title, Long marvel_id, String img_url, String extension) {
        this.title = title;
        this.marvel_id = marvel_id;
        this.img_url = img_url;
        this.extension = extension;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getMarvel_id() {
        return marvel_id;
    }

    public void setMarvel_id(Long marvel_id) {
        this.marvel_id = marvel_id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}