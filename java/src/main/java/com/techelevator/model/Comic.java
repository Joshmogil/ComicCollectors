package com.techelevator.model;


public class Comic {

    private long comicId;
    private long marvelId;
    private String comicTitle;
    private String imgUrl;
    private String description;

    public Comic(){}

    public Comic(long comicId, long marvelId, String comicTitle, String imgUrl, String description) {
        this.comicId = comicId;
        this.marvelId = marvelId;
        this.comicTitle = comicTitle;
        this.imgUrl = imgUrl;
        this.description = description;


    }

    public long getComicId() {
        return comicId;
    }

    public void setComicId(long comicId) {
        this.comicId = comicId;
    }

    public long getMarvelId() {
        return marvelId;
    }

    public void setMarvelId(long marvelId) {
        this.marvelId = marvelId;
    }

    public String getComicTitle() {
        return comicTitle;
    }

    public void setComicTitle(String comicTitle) {
        this.comicTitle = comicTitle;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Comic{" +
                "comicId=" + comicId +
                ", marvelId=" + marvelId +
                ", comicTitle='" + comicTitle + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
