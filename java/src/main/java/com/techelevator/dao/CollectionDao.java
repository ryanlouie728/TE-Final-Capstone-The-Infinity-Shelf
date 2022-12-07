package com.techelevator.dao;

import com.techelevator.model.CollectionDto;
import com.techelevator.model.SimpleCollectionDto;

import java.util.List;

public interface CollectionDao {
    List<SimpleCollectionDto> listCollectionsByUserId(int userId);

    void createCollection(SimpleCollectionDto collection);

    void updateCollection(SimpleCollectionDto collection);

    List<SimpleCollectionDto> listPublicCollections();

    void addComicToCollection(Integer collectionId, Integer comicId);

    CollectionDto getByCollectionId(Integer collectionId);
}
