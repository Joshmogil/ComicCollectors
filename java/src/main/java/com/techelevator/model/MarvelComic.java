package com.techelevator.model;

import java.util.List;
import java.util.Map;

public class MarvelComic {

    private String copyright;
    private Map<String,String> data;
    private Long id;
    private String title;
    private String series;
    private String description;
    private String thumbnail;


    public MarvelComic() {
    }

    public MarvelComic(String copyright, Map<String,String> data, Long id, String title, String series, String description, String thumbnail) {
        this.copyright = copyright;
        this.data = data;
        this.id = id;
        this.title = title;
        this.series = series;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                "data=" + data +
                "copyright=" + copyright +
                "title=" + title +
                "description=" + description +
                "series=" + series +
                "thumbnail=" + thumbnail +
                '}';
    }
}
