package com.techelevator.model;

public class CollectionComic {

    private long collectionId;
    private long comic_id;

    public CollectionComic() {}

    public CollectionComic(long collectionId, long comic_id) {
        this.collectionId = collectionId;
        this.comic_id = comic_id;
    }

    public long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(long collectionId) {
        this.collectionId = collectionId;
    }

    public long getComic_id() {
        return comic_id;
    }

    public void setComic_id(long comic_id) {
        this.comic_id = comic_id;
    }

    @Override
    public String toString() {
        return "CollectionComic{" +
                "collectionId=" + collectionId +
                ", comic_id=" + comic_id +
                '}';
    }
}
