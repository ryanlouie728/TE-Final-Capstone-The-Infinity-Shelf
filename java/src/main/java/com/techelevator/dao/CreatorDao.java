package com.techelevator.dao;

import com.techelevator.model.CreatorDto;

import java.util.List;

public interface CreatorDao {
    void createCreator(CreatorDto creatorDto);

    void createCreatorList(List<CreatorDto> creators);
}
