package com.techelevator.model;

import javax.validation.constraints.NotNull;
import java.util.*;

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

    private List<Count> characterCounts = new ArrayList<>();
    private List<Count> creatorCounts = new ArrayList<>();

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

    public List<Count> getCharacterCounts() {
        return characterCounts;
    }

    public void setCharacterCounts(List<Count> characterCounts) {
        this.characterCounts = characterCounts;
    }

    public List<Count> getCreatorCounts() {
        return creatorCounts;
    }

    public void setCreatorCounts(List<Count> creatorCounts) {
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
        Map<String, Integer> characters = new HashMap<>();
        for (ComicDto comic : comics) {
            for (CharacterDto character : comic.getCharacters()) {
                if (!characters.containsKey(character.getName())) {
                    characters.put(character.getName(), 1);
                } else {
                    characters.put(character.getName(), characters.get(character.getName()) + 1);
                }
            }
        }
        var sorted = entriesSortedByValues(characters);
        for (Map.Entry<String, Integer> character : sorted) {
            characterCounts.add(new Count(character.getKey(), character.getValue()));
        }
    }

    private void countCreators() {
        Map<String, Integer> creators = new HashMap<>();
        for (ComicDto comic : comics) {
            for (CreatorDto creator : comic.getCreators()) {
                if (!creators.containsKey(creator.getName())) {
                    creators.put(creator.getName(), 1);
                } else {
                    creators.put(creator.getName(), creators.get(creator.getName()) + 1);
                }
            }
        }
        for (Map.Entry<String, Integer> character : entriesSortedByValues(creators)) {
            creatorCounts.add(new Count(character.getKey(), character.getValue()));
        }
    }

    <K,V extends Comparable<? super V>> SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
                new Comparator<Map.Entry<K,V>>() {
                    @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                        int res= e2.getValue().compareTo(e1.getValue());
                        return res != 0 ? res : 1; // Special fix to preserve items with equal values
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }
}
