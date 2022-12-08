package com.techelevator.dao;

import com.techelevator.model.FriendDto;

import java.util.List;

public interface FriendDao {
    List<FriendDto> getFriendsById(Integer userId);



}
