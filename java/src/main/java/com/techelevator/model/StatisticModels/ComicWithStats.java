package com.techelevator.model.StatisticModels;

public class ComicWithStats {

    private String title;
    private Long marvelId;
    private String imgUrl;
    private String description;
    private Long count;

    public ComicWithStats() {
    }

    public ComicWithStats(String title, Long marvelId, String imgUrl, String description, Long count) {
        this.title = title;
        this.marvelId = marvelId;
        this.imgUrl = imgUrl;
        this.description = description;
        this.count = count;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
