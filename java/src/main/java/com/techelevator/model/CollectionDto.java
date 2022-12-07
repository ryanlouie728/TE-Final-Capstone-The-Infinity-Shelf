package com.techelevator.model;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private Map<String, Integer> characterCounts = new HashMap<>();
    private Map<String, Integer> creatorCounts = new HashMap<>();

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

    public Map<String, Integer> getCharacterCounts() {
        return characterCounts;
    }

    public void setCharacterCounts(Map<String, Integer> characterCounts) {
        this.characterCounts = characterCounts;
    }

    public Map<String, Integer> getCreatorCounts() {
        return creatorCounts;
    }

    public void setCreatorCounts(Map<String, Integer> creatorCounts) {
        this.creatorCounts = creatorCounts;
    }

    public void setComics(List<ComicDto> comics) {
        this.comics = comics;
    }

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

    public void count() {
        countCharacters();
        countCreators();
    }

    private void countCharacters() {
        for (ComicDto comic : comics) {
            for (CharacterDto character : comic.getCharacters()) {
                if (!characterCounts.containsKey(character.getName())) {
                    characterCounts.put(character.getName(), 1);
                } else {
                    characterCounts.put(character.getName(), characterCounts.get(character.getName()) + 1);
                }
            }
        }
    }

    private void countCreators() {
        for (ComicDto comic : comics) {
            for (CreatorDto creator : comic.getCreators()) {
                if (!creatorCounts.containsKey(creator.getName())) {
                    creatorCounts.put(creator.getName(), 1);
                } else {
                    creatorCounts.put(creator.getName(), creatorCounts.get(creator.getName()) + 1);
                }
            }
        }
    }
}
