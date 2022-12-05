package com.techelevator.controller;

import com.techelevator.dao.CollectionDao;
import com.techelevator.model.SimpleCollectionDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/collections")
public class CollectionController {
    private CollectionDao collectionDao;

    public CollectionController(CollectionDao collectionDao) {
        this.collectionDao = collectionDao;
    }

    @GetMapping("/simple/{userId}")
    public List<SimpleCollectionDto> listSimpleByUserId(@PathVariable Integer userId) {
        return collectionDao.listCollectionsByUserId(userId);
    }

    @PostMapping("")
    public void createCollection(@Valid @RequestBody SimpleCollectionDto collection) {
        collectionDao.createCollection(collection);
    }

    @PutMapping("")
    public void updateCollection(@Valid @RequestBody SimpleCollectionDto collection) {
        collectionDao.updateCollection(collection);
    }
}
