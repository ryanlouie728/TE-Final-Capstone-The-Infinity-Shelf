package com.techelevator.model.friends;

import java.util.List;

public class FriendsPageDto {
    List<FriendDto> friends;
    List<FriendRequestDto> requests;
    List<FriendRequestDto> pending;

    public List<FriendDto> getFriends() {
        return friends;
    }

    public void setFriends(List<FriendDto> friends) {
        this.friends = friends;
    }

    public List<FriendRequestDto> getRequests() {
        return requests;
    }

    public void setRequests(List<FriendRequestDto> requests) {
        this.requests = requests;
    }

    public List<FriendRequestDto> getPending() {
        return pending;
    }

    public void setPending(List<FriendRequestDto> pending) {
        this.pending = pending;
    }
}
