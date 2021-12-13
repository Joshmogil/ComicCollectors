package com.techelevator.model;

public class CreateCollectionDTO {

    private Long userId;
    private String collectionName;

    public CreateCollectionDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    @Override
    public String toString() {
        return "CreateCollectionDTO{" +
                "collection_name='" + collectionName + '\'' +
                ", user_id='" + userId + '\'' +
                '}';
    }
}
