package com.techelevator.dao;

import com.techelevator.model.friends.FriendDto;
import com.techelevator.model.friends.FriendRequestDto;
import com.techelevator.model.friends.FriendsPageDto;

import java.util.List;

public interface FriendDao {
    List<FriendDto> getFriendsById(Integer userId);
    FriendDto getFriendById(Integer userId, Integer friendId);

    void createFriendRequest(Integer userId, Integer requestId);

    List<FriendRequestDto> getToRequestsByUserId(Integer userId);
    List<FriendRequestDto> getFromRequestsByUserId(Integer userId);

    FriendsPageDto getFriendPageByUserId(Integer userId);

    void acceptFriendRequest(Integer requestId);

}
