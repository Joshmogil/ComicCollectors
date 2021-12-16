package com.techelevator.model;

public class MarvelComic {

    private Long comicId;
    private String title;
    private Long marvelId;
    private String imgUrl;
    private String extension;
    private String description;
    private Boolean selected = false;

    public MarvelComic() {
    }

    public MarvelComic(Long comicId, String title, Long marvelId, String imgUrl, String extension, String description, Boolean selected) {
        this.comicId = comicId;
        this.title = title;
        this.marvelId = marvelId;
        this.imgUrl = imgUrl;
        this.extension = extension;
        this.description = description;
        this.selected = selected;
    }

    public Long getComicId() {
        return comicId;
    }

    public void setComicId(Long comicId) {
        this.comicId = comicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getMarvelId() {
        return marvelId;
    }

    public void setMarvelId(Long marvelId) {
        this.marvelId = marvelId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "MarvelComic{" +
                "comicId=" + comicId +
                ", title='" + title + '\'' +
                ", marvelId=" + marvelId +
                ", imgUrl='" + imgUrl + '\'' +
                ", extension='" + extension + '\'' +
                ", description='" + description + '\'' +
                ", selected=" + selected +
                '}';
    }
}