package com.techelevator.dao;

import com.techelevator.model.CharacterDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcCharacteDao implements CharacterDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcCharacteDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Boolean characterExists(CharacterDto characterDto) {
        String sql =
                "SELECT name " +
                "FROM character " +
                "WHERE char_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, characterDto.getCharacterId());
        return rowSet.next();
    }


    @Override
    public void createCharacter(CharacterDto character) {
        String sql =
                "INSERT INTO character (char_id, name, thumbnail) " +
                "VALUES (?, ?, ?);";
        jdbcTemplate.update(sql, character.getCharacterId(), character.getName(), character.getThumbnail());
    }

    @Override
    public void createCharacterList(List<CharacterDto> characters) {
        for (CharacterDto character : characters) {
            if (characterExists(character)) continue;
            createCharacter(character);
        }
    }
}
