package com.techelevator.marvelmodel;

public class MarvelComicResults {

    private Integer id;
    private String title;
    private MarvelComicThumbnail thumbnail;

    public MarvelComicResults() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MarvelComicThumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(MarvelComicThumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }
}
