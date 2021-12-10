package com.techelevator.model;


import java.util.List;

public class Collection {

    private Long collectionId;
    private String collectionName;
    private long userId;
    private List<Comic> listOfComics;

    public Collection() {}

    public Collection(long collectionId, String collectionName, long userId, List<Comic> listOfComics) {
        this.collectionId = collectionId;
        this.collectionName = collectionName;
        this.userId = userId;
        this.listOfComics = listOfComics;
    }

    public List<Comic> getListOfComics() {
        return listOfComics;
    }

    public void setListOfComics(List<Comic> listOfComics) {
        this.listOfComics = listOfComics;
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

    @Override
    public String toString() {
        return "Collection{" +
                "collectionId=" + collectionId +
                ", collectionName='" + collectionName + '\'' +
                ", userId=" + userId +
                ", listOfComics=" + listOfComics +
                '}';
    }
}
