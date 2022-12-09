package com.techelevator.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ComicDto {

    private Integer id;
    private Integer collectionId;
    @NotNull
    @NotBlank(message = "The title field must not be blank")
    private String title;
    @NotNull
    @NotBlank(message = "The issueNumber field must not be blank")
    private String issueNumber;
    private String description;
    private String thumbnailUrl;
    private List<CharacterDto> characters;
    private List<CreatorDto> creators;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public List<CharacterDto> getCharacters() {
        return characters;
    }

    public void setCharacters(List<CharacterDto> characters) {
        this.characters = characters;
    }

    public List<CreatorDto> getCreators() {
        return creators;
    }

    public void setCreators(List<CreatorDto> creators) {
        this.creators = creators;
    }
}
