package com.techelevator.dao;

import com.techelevator.model.ComicDto;
import com.techelevator.model.SimpleComicDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcComicDao implements ComicDao {
    private final JdbcTemplate jdbcTemplate;

    private final CharacterDao characterDao;
    private final CreatorDao creatorDao;

    public JdbcComicDao(JdbcTemplate jdbcTemplate, CharacterDao characterDao, CreatorDao creatorDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.characterDao = characterDao;
        this.creatorDao = creatorDao;
    }

    @Override
    public List<SimpleComicDto> listSimple() {
        String sql =
                "SELECT * " +
                "FROM comic;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        return simpleComicDtoListMapper(rowSet);
    }

    @Override
    public List<SimpleComicDto> listSimpleByCollection(int collectionId) {
        String sql =
                "SELECT comic.comic_id, comic.title, comic.issue_number, comic.description, comic.thumbnail " +
                "FROM comic " +
                "JOIN collection_comic AS coll_com ON coll_com.comic_id = comic.comic_id " +
                "WHERE coll_com.coll_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, collectionId);
        return simpleComicDtoListMapper(rowSet);
    }

    private Boolean comicExists(ComicDto comic) {
        String sql =
                "SELECT comic.title " +
                "FROM comic " +
                "WHERE comic.comic_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, comic.getId());
        return rowSet.next();
    }

    @Override
    public void createComic(SimpleComicDto comic) {
        String sql =
                "INSERT INTO comic (comic_id, title, issue_number, description, thumbnail) " +
                "VALUES (?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql,comic.getId(), comic.getTitle(), comic.getIssueNumber(), comic.getDescription(), comic.getThumbnailUrl());
    }

    @Override
    public void createComic(ComicDto comic) {
        String sql =
                "INSERT INTO comic (comic_id, title, issue_number, description, thumbnail) " +
                "VALUES (?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql,comic.getId(), comic.getTitle(), comic.getIssueNumber(), comic.getDescription(), comic.getThumbnailUrl());
    }

    @Override
    public void createComicList(List<ComicDto> comics) {
        for (ComicDto comic : comics) {
            if (!comicExists(comic)) {
                createComic(comic);
                characterDao.createCharacterList(comic.getCharacters());
                creatorDao.createCreatorList(comic.getCreators());
            }
        }
    }

    @Override
    public List<SimpleComicDto> listSimpleByTitle(String title) {
        title = "%" + title + "%";
        String sql =
                "SELECT comic_id, title, issue_number, description, thumbnail " +
                "FROM comic " +
                "WHERE title ILIKE ? " +
                "LIMIT 20;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, title);
        return simpleComicDtoListMapper(rowSet);
    }

    public List<SimpleComicDto> simpleComicDtoListMapper(SqlRowSet rowSet) {
        List<SimpleComicDto> comics = new ArrayList<>();
        while (rowSet.next()) {
            comics.add(simpleComicDtoMapper(rowSet));
        }
        return comics;
    }

    public SimpleComicDto simpleComicDtoMapper(SqlRowSet rowSet) {
        try {
            SimpleComicDto comic = new SimpleComicDto();
            comic.setId(rowSet.getInt("comic_id"));
            comic.setTitle(rowSet.getString("title"));
            comic.setIssueNumber(rowSet.getString("issue_number"));
            comic.setDescription(rowSet.getString("description"));
            comic.setThumbnailUrl(rowSet.getString("thumbnail"));
            return comic;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
