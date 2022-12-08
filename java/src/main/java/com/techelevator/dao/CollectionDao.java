package com.techelevator.dao;

import com.techelevator.model.CollectionDto;
import com.techelevator.model.SimpleCollectionDto;
import com.techelevator.model.login.User;

import java.util.List;

public interface CollectionDao {


    Integer getBaseCollectionIdByUserId(Integer userId);
    CollectionDto getBaseCollectionByUserId(Integer userId);
    List<SimpleCollectionDto> listCollectionsByUserId(int userId);

    void createCollection(SimpleCollectionDto collection);

    void updateCollection(SimpleCollectionDto collection);

    void deleteCollection(Integer collectionId);

    List<SimpleCollectionDto> listPublicCollections();

    void addComicToCollection(Integer collectionId, Integer comicId);
    void removeComicFromCollection(Integer collectionId, Integer comicId);

    CollectionDto getByCollectionId(Integer collectionId);

}
