package com.techelevator.model;

import java.util.ArrayList;

public class Collection {

    private long collectionId;
    private String collectionName;
    private ArrayList<Comic> comics;

    public Collection(long collectionId, String collectionName, ArrayList<Comic> comics) {
        this.collectionId = collectionId;
        this.collectionName = collectionName;
        this.comics = comics;
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

    public ArrayList<Comic> getComics() {
        return comics;
    }

    public void setComics(ArrayList<Comic> comics) {
        this.comics = comics;
    }
}
