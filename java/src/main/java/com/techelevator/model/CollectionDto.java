package com.techelevator.model;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CollectionDto {


    private Integer collectionId;
    @NotNull
    private Integer userId;
    @NotNull
    private String collectionName;

    private String collectionDescription;
    private String collectionCoverUrl;

    private Boolean collectionPublic;
    private List<ComicDto> comics;

    public CollectionDto() {
    }
    public CollectionDto(SimpleCollectionDto simple) {
        collectionId = simple.getCollectionId();
        userId = simple.getUserId();
        collectionName = simple.getCollectionName();
        collectionDescription = simple.getCollectionDescription();
        collectionCoverUrl = simple.getCollectionCoverUrl();
        collectionPublic = simple.getCollectionPublic();
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

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

    public List<ComicDto> getComics() {
        return comics;
    }

    public void setComics(List<ComicDto> comics) {
        this.comics = comics;
    }
}
