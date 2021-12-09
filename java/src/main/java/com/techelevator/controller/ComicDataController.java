package com.techelevator.controller;

import com.techelevator.dao.CollectionDataDao;
import com.techelevator.dao.ComicDataDao;
import com.techelevator.model.Collection;
import com.techelevator.model.Comic;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
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

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "collections", method = RequestMethod.POST)
    public boolean createCollection(@RequestBody Collection newCollection ){
        if (!collectiondd.createCollection(newCollection.getCollectionName(), newCollection.getUserId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Collection Creation Failed");
        }
        return true;
    }

//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(path = "collections", method = RequestMethod.POST)
//    public boolean createCollection(@RequestBody String collectionName, Principal user) {
//        return collectiondd.createCollection(collectionName, user.getId())
//    }

}
