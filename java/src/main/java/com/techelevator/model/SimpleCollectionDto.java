package com.techelevator.model;

public class SimpleCollectionDto {
    private Integer userId;
    private String collectionName;
    private String collectionDescription;
    private String collectionCoverUrl;

    private Boolean collectionPublic;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getCollectionDescription() {
        return collectionDescription;
    }

    public void setCollectionDescription(String collectionDescription) {
        this.collectionDescription = collectionDescription;
    }

    public String getCollectionCoverUrl() {
        return collectionCoverUrl;
    }

    public void setCollectionCoverUrl(String collectionCoverUrl) {
        this.collectionCoverUrl = collectionCoverUrl;
    }

    public Boolean getCollectionPublic() {
        return collectionPublic;
    }

    public void setCollectionPublic(Boolean collectionPublic) {
        this.collectionPublic = collectionPublic;
    }
}
