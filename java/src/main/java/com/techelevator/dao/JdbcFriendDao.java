package com.techelevator.dao;

import com.techelevator.model.FriendDto;
import com.techelevator.model.login.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcFriendDao implements FriendDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;

    public JdbcFriendDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }

    @Override
    public List<FriendDto> getFriendsById(Integer userId) {
        String sql =
                "SELECT user_id, friend_id " +
                "FROM user_friend " +
                "WHERE user_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
        return friendListMapper(rowSet);
    }

    private List<FriendDto> friendListMapper(SqlRowSet rowSet) {
        List<FriendDto> friends = new ArrayList<>();
        while(rowSet.next()) {
            friends.add(friendMapper(rowSet));
        }
        return friends;
    }

    private FriendDto friendMapper(SqlRowSet rowSet) {
        try {
            FriendDto friend = new FriendDto();
            friend.setFriendId(rowSet.getInt("friend_id"));
            friend.setFriendName(userDao.getUserById(friend.getFriendId()).getUsername());
            return friend;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
