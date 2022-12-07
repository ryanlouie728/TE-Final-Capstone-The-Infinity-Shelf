package com.techelevator.dao;

import com.techelevator.model.CharacterDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
    public void createCharacter(CharacterDto character, Integer comicId) {
        String sql =
                "INSERT INTO character (char_id, name, thumbnail) " +
                "VALUES (?, ?, ?);" +
                "INSERT INTO character_comic (char_id, comic_id) " +
                "VALUES (?, ?);";
        jdbcTemplate.update(sql, character.getCharacterId(), character.getName(), character.getThumbnail(), character.getCharacterId(), comicId);
    }

    @Override
    public void createCharacterList(List<CharacterDto> characters, Integer comicId) {
        for (CharacterDto character : characters) {
            if (characterExists(character)) continue;
            createCharacter(character, comicId);
        }
    }

    @Override
    public List<CharacterDto> listByComicId(Integer comicId) {
        String sql =
                "SELECT character.char_id, character.name, character.thumbnail " +
                "FROM character " +
                "JOIN character_comic ON character.char_id = character_comic.char_id " +
                "WHERE character_comic.comic_id = ?; ";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, comicId);
        return characterListMapper(rowSet);
    }

    private List<CharacterDto> characterListMapper(SqlRowSet rowSet) {
        List<CharacterDto> characters = new ArrayList<>();
        while (rowSet.next()) {
            characters.add(characterMapper(rowSet));
        }
        return characters;
    }

    private CharacterDto characterMapper(SqlRowSet rowSet) {
        try {
            CharacterDto character = new CharacterDto();
            character.setCharacterId(rowSet.getInt("char_id"));
            character.setName(rowSet.getString("name"));
            character.setThumbnail(rowSet.getString("thumbnail"));
            return character;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
