package com.techelevator.model;


import java.util.List;

public class Collection {

    private Long collectionId;
    private String collectionName;
    private long userId;
    private List<Comic> comicList;


    public Collection() {}

    public Collection(long collectionId, String collectionName, long userId) {
        this.collectionId = collectionId;
        this.collectionName = collectionName;
        this.userId = userId;
    }


    public long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(long collectionId) {
        this.collectionId = collectionId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

    public List<Comic> getComicList() {
        return comicList;
    }

    public void setComicList(List<Comic> comicList) {
        this.comicList = comicList;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "collectionId=" + collectionId +
                ", collectionName='" + collectionName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
