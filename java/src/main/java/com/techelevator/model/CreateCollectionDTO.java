package com.techelevator.model;

public class CreateCollectionDTO {

    private Long user_id;
    private String collection_name;

    public CreateCollectionDTO() {
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getCollection_name() {
        return collection_name;
    }

    public void setCollection_name(String collection_name) {
        this.collection_name = collection_name;
    }

    @Override
    public String toString() {
        return "CreateCollectionDTO{" +
                "collection_name='" + collection_name + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
