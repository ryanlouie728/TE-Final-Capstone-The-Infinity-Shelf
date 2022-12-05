package com.techelevator.dao;

import com.techelevator.model.SimpleCollectionDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCollectionDao implements CollectionDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcCollectionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<SimpleCollectionDto> listCollectionsByUserId(int userId) {
        String sql =
                "SELECT coll_id, user_id, coll_name, coll_description, coll_cover, coll_public " +
                "FROM collection " +
                "WHERE user_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
        return simpleCollectionDtoListMapper(rowSet);
    }

    @Override
    public void createCollection(SimpleCollectionDto collection) {
        String sql =
                "INSERT INTO collection (user_id, coll_name) " +
                "VALUES (?, ?);";
        jdbcTemplate.update(sql, collection.getUserId(), collection.getCollectionName());
    }

    @Override
    public void updateCollection(SimpleCollectionDto collection) {
        String sql =
                "UPDATE collection " +
                "SET coll_name = ?, coll_description = ?, coll_cover = ?, coll_public = ? " +
                "WHERE coll_id = ?;";
        jdbcTemplate.update(sql, collection.getCollectionName(), collection.getCollectionDescription(), collection.getCollectionCoverUrl(), collection.getCollectionPublic(), collection.getCollectionId());
    }

    private List<SimpleCollectionDto> simpleCollectionDtoListMapper(SqlRowSet rowSet) {
        List<SimpleCollectionDto> collections = new ArrayList<>();
        while (rowSet.next()) {
            collections.add(simpleCollectionDtoMapper(rowSet));
        }
        return collections;
    }

    private  SimpleCollectionDto simpleCollectionDtoMapper(SqlRowSet rowSet) {
        try {
            SimpleCollectionDto collection = new SimpleCollectionDto();
            collection.setCollectionId(rowSet.getInt("coll_id"));
            collection.setUserId(rowSet.getInt("user_id"));
            collection.setCollectionName(rowSet.getString("coll_name"));
            collection.setCollectionDescription(rowSet.getString("coll_description"));
            collection.setCollectionCoverUrl(rowSet.getString("coll_cover"));
            collection.setCollectionPublic(rowSet.getBoolean("coll_public"));
            return collection;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
