package com.techelevator.dao;

import com.techelevator.model.CharacterDto;

import java.util.List;

public interface CharacterDao {

    void createCharacter(CharacterDto character, Integer comicId);

    void createCharacterList(List<CharacterDto> characters, Integer comicId);

    List<CharacterDto> listByComicId(Integer comicId);
}
