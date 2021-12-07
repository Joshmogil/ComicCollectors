package com.techelevator.model;


public class Comic {

    private long comicId;
    private String comicTitle;
    private String imgUrl;

    public Comic(){}

    public Comic(long comicId, String comicTitle, String imgUrl) {
        this.comicId = comicId;
        this.comicTitle = comicTitle;
        this.imgUrl = imgUrl;


    }

    public long getComicId() {
        return comicId;
    }

    public void setComicId(long comicId) {
        this.comicId = comicId;
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

    @Override
    public String toString() {
        return "Comic{" +
                "comicId=" + comicId +
                ", comicTitle='" + comicTitle + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
