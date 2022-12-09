package com.techelevator.dao;

import com.techelevator.model.CollectionDto;
import com.techelevator.model.SimpleCollectionDto;
import com.techelevator.model.login.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCollectionDao implements CollectionDao {
    private final JdbcTemplate jdbcTemplate;
    private final ComicDao comicDao;

    public JdbcCollectionDao(JdbcTemplate jdbcTemplate, ComicDao comicDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.comicDao = comicDao;
    }

    @Override
    public Integer getBaseCollectionIdByUserId(Integer userId) {
        String sql =
                "SELECT coll_id " +
                "FROM user_collection " +
                "WHERE user_id = ?;";
        return jdbcTemplate.queryForObject(sql, Integer.class, userId);
    }

    @Override
    public List<SimpleCollectionDto> listCollectionsByUserId(int userId) {
        Integer baseId = getBaseCollectionIdByUserId(userId);
        String sql =
                "SELECT coll_id, user_id, coll_name, coll_description, coll_cover, coll_public " +
                "FROM collection " +
                "WHERE user_id = ? " +
                "AND coll_id != ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId, baseId);
        return simpleCollectionDtoListMapper(rowSet);
    }

    @Override
    public void createCollection(SimpleCollectionDto collection) {
        String sql =
                "INSERT INTO collection (user_id, coll_name, coll_description) " +
                "VALUES (?, ?, ?);";
        jdbcTemplate.update(sql, collection.getUserId(), collection.getCollectionName(), collection.getCollectionDescription());
    }

    @Override
    public CollectionDto getBaseCollectionByUserId(Integer userId) {
        String sql =
                "SELECT collection.coll_id, collection.user_id, collection.coll_name, collection.coll_description, collection.coll_cover, collection.coll_public " +
                "FROM collection " +
                "JOIN user_collection ON collection.coll_id = user_collection.coll_id " +
                "WHERE user_collection.user_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
        if (rowSet.next()) {
            return collectionMapper(rowSet);
        }
        return null;
    }

    @Override
    public void updateCollection(SimpleCollectionDto collection) {
        String sql =
                "UPDATE collection " +
                "SET coll_name = ?, coll_description = ?, coll_cover = ?, coll_public = ? " +
                "WHERE coll_id = ?;";
        jdbcTemplate.update(sql, collection.getCollectionName(), collection.getCollectionDescription(), collection.getCollectionCoverUrl(), collection.getCollectionPublic(), collection.getCollectionId());
    }

    @Override
    public void deleteCollection(Integer collectionId) {
        String sql =
                "DELTE " +
                "FROM collection_comic " +
                "WHERE coll_id = ?;" +
                "DELETE " +
                "FROM collection " +
                "WHERE coll_id = ?;";
        jdbcTemplate.update(sql, collectionId, collectionId);
    }

    @Override
    public List<SimpleCollectionDto> listPublicCollections() {
        String sql =
                "SELECT coll_id, user_id, coll_name, coll_description, coll_cover, coll_public " +
                "FROM collection " +
                "WHERE coll_public = true;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        return simpleCollectionDtoListMapper(rowSet);
    }

    private Boolean comicInCollection(Integer collectionId, Integer comicId) {
        String sql =
                "SELECT comic_id " +
                "FROM collection_comic " +
                "WHERE coll_id = ? " +
                "AND comic_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, collectionId, comicId);
        return rowSet.next();
    }

    @Override
    public void addComicToCollection(Integer collectionId, Integer comicId) {
        if (comicInCollection(collectionId, comicId)) return;
        String sql =
                "INSERT INTO collection_comic (coll_id, comic_id) " +
                "VALUES (?, ?);";
        jdbcTemplate.update(sql, collectionId, comicId);
    }

    @Override
    public void removeComicFromCollection(Integer collectionId, Integer comicId) {
        String sql =
                "DELETE " +
                "FROM collection_comic " +
                "WHERE coll_id = ?" +
                "AND comic_id = ?;";
        jdbcTemplate.update(sql, collectionId, comicId);
    }

    @Override
    public CollectionDto getByCollectionId(Integer collectionId) {
        String sql =
                "SELECT coll_id, user_id, coll_name, coll_description, coll_cover, coll_public " +
                "FROM collection " +
                "WHERE coll_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, collectionId);
        if (rowSet.next()) {
            return collectionMapper(rowSet);
        }
        return null;
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

    private CollectionDto collectionMapper(SqlRowSet rowSet) {
        try {
            SimpleCollectionDto simple = simpleCollectionDtoMapper(rowSet);
            CollectionDto collection = new CollectionDto(simple);
            collection.setComics(comicDao.listComicsByCollectionId(collection.getCollectionId()));
            //collection.count(); TODO this throws null-pointer exception
            return collection;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
