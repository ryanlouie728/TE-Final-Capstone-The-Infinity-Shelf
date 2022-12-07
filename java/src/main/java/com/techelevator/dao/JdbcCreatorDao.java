package com.techelevator.dao;

import com.techelevator.model.CreatorDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

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
    public void createCreator(CreatorDto creatorDto) {
        String sql =
                "INSERT INTO creator (creator_id, name, thumbnail, role) " +
                "VALUES (?, ?, ?, ?);";
        jdbcTemplate.update(sql, creatorDto.getCreatorId(), creatorDto.getName(), creatorDto.getThumbnail(), creatorDto.getRole());
    }

    @Override
    public void createCreatorList(List<CreatorDto> creators) {
        for (CreatorDto creator : creators) {
            if (creatorExists(creator)) continue;
            createCreator(creator);
        }
    }
}
