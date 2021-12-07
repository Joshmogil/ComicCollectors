package com.techelevator.model;

public class CollectionComic {

    private long collection_id;
    private long comic_id;
    private long user_id;

    public CollectionComic() {

    }

    public CollectionComic(long collection_id, long comic_id, long user_id) {
        this.collection_id = collection_id;
        this.comic_id = comic_id;
        this.user_id = user_id;
    }

    public long getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(long collection_id) {
        this.collection_id = collection_id;
    }

    public long getComic_id() {
        return comic_id;
    }

    public void setComic_id(long comic_id) {
        this.comic_id = comic_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString(){
        return "CollectionComic{"+
                "collection_id="+ collection_id+
                "comic_id="+ comic_id +
                "user_id="+ user_id +
                '}';

    }
}
