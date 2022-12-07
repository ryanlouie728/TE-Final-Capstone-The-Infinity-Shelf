package com.techelevator.dao;

import com.techelevator.model.CreatorDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCreatorDao implements CreatorDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCreatorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Boolean creatorExists(CreatorDto creator) {
        String sql =
                "SELECT name " +
                "FROM creator " +
                "WHERE creator_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, creator.getCreatorId());
        return rowSet.next();
    }

    @Override
    public void createCreator(CreatorDto creatorDto, Integer comicId) {
        String sql =
                "INSERT INTO creator (creator_id, name, thumbnail, role) " +
                "VALUES (?, ?, ?, ?);" +
                "INSERT INTO creator_comic (creator_id, comic_id) " +
                "VALUES (?, ?);";
        jdbcTemplate.update(sql, creatorDto.getCreatorId(), creatorDto.getName(), creatorDto.getThumbnail(), creatorDto.getRole(), creatorDto.getCreatorId(), comicId);
    }

    @Override
    public void createCreatorList(List<CreatorDto> creators, Integer comicId) {
        for (CreatorDto creator : creators) {
            if (creatorExists(creator)) continue;
            createCreator(creator, comicId);
        }
    }

    @Override
    public List<CreatorDto> listByComicId(Integer comicId) {
        String sql =
                "SELECT creator.creator_id, creator.name, creator.thumbnail, creator.role " +
                "FROM creator " +
                "JOIN creator_comic AS cc ON creator.creator_id = cc.creator_id " +
                "WHERE cc.comic_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, comicId);
        return creatorListMapper(rowSet);
    }

    private List<CreatorDto> creatorListMapper(SqlRowSet rowSet) {
        List<CreatorDto> creators = new ArrayList<>();
        while (rowSet.next()) {
            creators.add(creatorMapper(rowSet));
        }
        return creators;
    }

    private CreatorDto creatorMapper(SqlRowSet rowSet) {
        try {
            CreatorDto creator = new CreatorDto();
            creator.setCreatorId(rowSet.getInt("creator_id"));
            creator.setName(rowSet.getString("name"));
            creator.setThumbnail(rowSet.getString("thumbnail"));
            creator.setRole(rowSet.getString("role"));
            return creator;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
