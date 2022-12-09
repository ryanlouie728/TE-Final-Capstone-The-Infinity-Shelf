package com.techelevator.model;

import com.techelevator.model.friends.FriendDto;

import java.util.List;

public class UserDto {

    private int id;
    private String username;

    private CollectionDto base;
    private List<FriendDto> friends;
    private List<SimpleCollectionDto> collections;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CollectionDto getBase() {
        return base;
    }

    public void setBase(CollectionDto base) {
        this.base = base;
    }

    public List<FriendDto> getFriends() {
        return friends;
    }

    public void setFriends(List<FriendDto> friends) {
        this.friends = friends;
    }

    public List<SimpleCollectionDto> getCollections() {
        return collections;
    }

    public void setCollections(List<SimpleCollectionDto> collections) {
        this.collections = collections;
    }
}
