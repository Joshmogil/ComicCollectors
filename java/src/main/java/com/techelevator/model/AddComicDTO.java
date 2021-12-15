package com.techelevator.model;

public class AddComicDTO {

    private Integer collectionId;
    private Integer comicId;

    public AddComicDTO() {
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getComicId() {
        return comicId;
    }

    public void setComicId(Integer comicId) {
        this.comicId = comicId;
    }

    @Override
    public String toString() {
        return "AddComicDTO{" +
                "collectionId='" + collectionId + '\'' +
                ", comicId='" + comicId + '\'' +
                '}';
    }
}
