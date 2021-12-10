package com.techelevator.model;

public class AddComicDTO {

    private Integer collection_id;
    private Integer comic_id;

    public AddComicDTO() {
    }

    public Integer getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(Integer collection_id) {
        this.collection_id = collection_id;
    }

    public Integer getComic_id() {
        return comic_id;
    }

    public void setComic_id(Integer comic_id) {
        this.comic_id = comic_id;
    }

    @Override
    public String toString() {
        return "AddComicDTO{" +
                "collection_id='" + collection_id + '\'' +
                ", comic_id='" + comic_id + '\'' +
                '}';
    }
}
