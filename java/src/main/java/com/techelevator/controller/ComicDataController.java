package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.model.Character;
import com.techelevator.model.Collection;
import com.techelevator.model.Comic;
import com.techelevator.model.Series;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@RestController
@CrossOrigin
@PreAuthorize("permitAll")
public class ComicDataController {

    private ComicDataDao cdd;
    private CollectionDataDao collectiondd;
    private CollectionComicDataDao ccdd;
    private CharacterDataDao characterDataDao;
    private SeriesDataDao sdd;
    

    public ComicDataController (ComicDataDao cdd, CollectionDataDao collectiondd, CollectionComicDataDao ccdd,
                                CharacterDataDao characterDataDao, SeriesDataDao sdd) {
        this.cdd = cdd; this.collectiondd = collectiondd;
        this.ccdd = ccdd;
        this.characterDataDao = characterDataDao;
        this.sdd = sdd;
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

    @RequestMapping(path = "collections/comics/{collectionId}", method = RequestMethod.GET)
    public List<Comic> getAllComicsInCollectionByCollectionId(@PathVariable Long collectionId) {
        List<Comic> listComicsInCollection = ccdd.getAllComicsInCollectionByCollectionId(collectionId);
        return listComicsInCollection;
    }

    @RequestMapping(path = "characters/id/{characterId}", method = RequestMethod.GET)
    public Character getCharacterById(@PathVariable long characterId) {
        return characterDataDao.getCharacterById(characterId);
    }

    @RequestMapping(path = "characters/{characterName}", method = RequestMethod.GET)
    public List<Comic> getAllComicsInCollectionByCollectionId(@PathVariable String characterName) {
        List<Comic> listOfComicsWithCharacter = characterDataDao.getAllComicsWithCharacterName(characterName);
        return listOfComicsWithCharacter;
    }

    @RequestMapping(path = "series/{seriesId}", method = RequestMethod.GET)
    public Series getSingleSeriesById(@PathVariable long seriesId) {
        return sdd.getSingleSeriesById(seriesId);
    }


    //DELETE COMIC FROM COLLECTION
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "collections/delete/{collectionId}/{comicId}", method = RequestMethod.DELETE)
    public void deleteComicFromCollection(@PathVariable long collectionId, @PathVariable long comicId) {

        ccdd.deleteComicFromCollection(collectionId, comicId);
    }


}
