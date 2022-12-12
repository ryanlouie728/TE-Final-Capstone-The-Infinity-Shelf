package com.techelevator.controller;

import com.techelevator.dao.CollectionDao;
import com.techelevator.model.CollectionDto;
import com.techelevator.model.Count;
import com.techelevator.model.CreatorDto;
import com.techelevator.model.SimpleCollectionDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

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

    @DeleteMapping("/{collectionId}")
    public void deleteCollection(@PathVariable Integer collectionId) {
        collectionDao.deleteCollection(collectionId);
    }

    @GetMapping("")
    public List<SimpleCollectionDto> listPublicCollections() {
        return collectionDao.listPublicCollections();
    }

    @PostMapping("/{collectionId}/{comicId}")
    public void addComicToCollection(@PathVariable Integer collectionId, @PathVariable Integer comicId) {
        collectionDao.addComicToCollection(collectionId, comicId);
    }

    @DeleteMapping("/{collectionId}/{comicId}")
    public void removeComicFromCollection(@PathVariable Integer collectionId, @PathVariable Integer comicId) {
        collectionDao.removeComicFromCollection(collectionId, comicId);
    }

    @GetMapping("/{collectionId}")
    public CollectionDto getCollectionById(@PathVariable Integer collectionId) {
        return collectionDao.getByCollectionId(collectionId);
    }

    @GetMapping("/user/{userId}")
    public List<CollectionDto> getCollectionsByUserId(@PathVariable Integer userId) {
        return collectionDao.getCollectionsByUserId(userId);
    }

    @GetMapping("/aggregate")
    public Map<String,  List<Count>> getAggregateStats() {
        List<CollectionDto> collections = collectionDao.getAllCollections();
        Map<String, List<Count>> out = new HashMap<>();
        Map<String, Integer> creators = new HashMap<>();
        for (CollectionDto collection : collections) {
            for (Count creator : collection.getCreatorCounts()) {
                if (creators.containsKey(creator.getName())) {
                    creators.put(creator.getName(), creators.get(creator.getName()) + creator.getCount());
                } else {
                    creators.put(creator.getName(), creator.getCount());
                }
            }
        }
        Map<String, Integer> characters = new HashMap<>();
        for (CollectionDto collection : collections) {
            for (Count character : collection.getCharacterCounts()) {
                if (characters.containsKey(character.getName())) {
                    characters.put(character.getName(), characters.get(character.getName()) + character.getCount());
                } else {
                    characters.put(character.getName(), character.getCount());
                }
            }
        }

        out.put("characters", sortMap(characters));
        out.put("creators", sortMap(creators));
        return out;
    }

    private List<Count> sortMap(Map<String, Integer> in) {
        List<Count> out = new ArrayList<>();
        for (Map.Entry<String,Integer> entry : entriesSortedByValues(in)) {
            out.add(new Count(entry.getKey(), entry.getValue()));
        }
        return out;
    }

    private <K,V extends Comparable<? super V>> SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
                new Comparator<Map.Entry<K,V>>() {
                    @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                        int res= e2.getValue().compareTo(e1.getValue());
                        return res != 0 ? res : 1; // Special fix to preserve items with equal values
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }


}
