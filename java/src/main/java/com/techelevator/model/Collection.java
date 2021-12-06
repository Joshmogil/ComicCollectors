package com.techelevator.model;

public class Collection {

    private long collectionId;
    private String collectionName;

    public Collection(long collectionId, String collectionName) {
        this.collectionId = collectionId;
        this.collectionName = collectionName;
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
}
