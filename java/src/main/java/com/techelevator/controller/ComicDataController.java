package com.techelevator.controller;

import com.techelevator.dao.CollectionDataDao;
import com.techelevator.dao.ComicDataDao;
import com.techelevator.model.Collection;
import com.techelevator.model.Comic;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ComicDataController {

    private ComicDataDao cdd;
    private CollectionDataDao collectiondd;
    

    public ComicDataController (ComicDataDao cdd, CollectionDataDao collectiondd) {
        this.cdd = cdd; this.collectiondd = collectiondd;
    }

    @RequestMapping(path = "comics/{comicId}", method = RequestMethod.GET)
    public Comic getComicById(@PathVariable int comicId) {return cdd.getComicById(comicId);}

    @RequestMapping(path = "comics", method = RequestMethod.GET)
    public List<Comic> getAllComics() {return cdd.getAllComics();}

    @RequestMapping(path ="collections/{collectionId}", method = RequestMethod.GET)
    public Collection getCollectionById(@PathVariable int collectionId) {return collectiondd.getCollectionById(collectionId);}

    @RequestMapping(path = "collections", method = RequestMethod.GET)
    public List<Collection> getAllCollections(){return collectiondd.getAllCollections();}

    @RequestMapping(path = "collections/user/{userId}", method = RequestMethod.GET)
    public List<Collection> getUserCollections(@PathVariable int userId){
        return collectiondd.getUserCollections(userId);
    }

//    @RequestMapping(path = "collections", method = RequestMethod.POST)
//    public Collection createCollection(@RequestBody Collection collection){
//        Collection createdCollection = collectiondd.createCollection(collection.getCollectionName(),collection.getUserId());
//        return createdCollection;
//    }

}
