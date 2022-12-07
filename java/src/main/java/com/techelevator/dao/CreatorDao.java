package com.techelevator.dao;

import com.techelevator.model.CreatorDto;

import java.util.List;

public interface CreatorDao {
    void createCreator(CreatorDto creatorDto, Integer comicId);

    void createCreatorList(List<CreatorDto> creators, Integer comicId);

    List<CreatorDto> listByComicId(Integer comicId);
}
