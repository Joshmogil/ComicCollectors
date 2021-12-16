package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.model.*;
import com.techelevator.model.Character;
import com.techelevator.model.StatisticModels.CharacterWithStats;
import com.techelevator.model.StatisticModels.ComicWithStats;
import com.techelevator.services.MarvelComicService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.security.Principal;
import java.util.ArrayList;
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
    private UserDao ud;


    public ComicDataController(ComicDataDao cdd, CollectionDataDao collectiondd, CollectionComicDataDao ccdd,
                               CharacterDataDao characterDataDao, SeriesDataDao sdd, UserDao ud) {
        this.cdd = cdd;
        this.collectiondd = collectiondd;
        this.ccdd = ccdd;
        this.characterDataDao = characterDataDao;
        this.sdd = sdd;
        this.ud = ud;
    }
    //Marvel Service Declaration
    String MARVEL_BASE_URL = "http://gateway.marvel.com/v1/public/";
    String privateKey = "197ef818f572516a2966e997ee7268e0cd590e21";
    String publicKey = "20afbe7ebe8ad8af2c91b02a275e06cc";

    MarvelComicService marvelComicService = new MarvelComicService(MARVEL_BASE_URL, privateKey,publicKey);



    @RequestMapping(path = "marvelcomics/{characterName}", method = RequestMethod.GET)
    public List<MarvelComic> testGetComicsCharacterName(@PathVariable String characterName){

        List<MarvelComic> comicListWithComicIds = new ArrayList<>();

        List<MarvelComic> comicList = marvelComicService.getComicListByCharacterName(characterName);


        for(MarvelComic marvelComic:comicList){
            //set comic id

            if(cdd.getComicSerialByMarvelId(marvelComic.getMarvelId()) == -1){

                Integer comicSerialForCollection = cdd.addComicToComicTable(marvelComic.getMarvelId(), marvelComic.getTitle(), marvelComic.getImgUrl(), marvelComic.getDescription());

                marvelComic.setComicId(Long.valueOf(comicSerialForCollection));

            }else{

                marvelComic.setComicId(Long.valueOf(cdd.getComicSerialByMarvelId(marvelComic.getMarvelId())));

            }

            comicListWithComicIds.add(marvelComic);

        }

        return comicListWithComicIds;

    }


    //Get Comic Object By comicID
    @RequestMapping(path = "comics/{comicId}", method = RequestMethod.GET)
    public Comic getComicById(@PathVariable int comicId) {
        return cdd.getComicById(comicId);
    }

    //Get All Comic Objects In Comics Table
    @RequestMapping(path = "comics", method = RequestMethod.GET)
    public List<Comic> getAllComics() {
        return cdd.getAllComics();
    }

    //Get Single Collection By collectionId
    @RequestMapping(path = "collections/{collectionId}", method = RequestMethod.GET)
    public Collection getCollectionById(@PathVariable int collectionId) {

        Collection collection = collectiondd.getCollectionById(collectionId);

        collection.setComicList(ccdd.getAllComicsInCollectionByCollectionId(collection.getCollectionId()));

        return collection;
    }

    //Get All Collection Objects In Collections Table
    @RequestMapping(path = "collections", method = RequestMethod.GET)
    public List<Collection> getAllCollections() {

        List<Collection> collections = collectiondd.getAllCollections();

        for (Collection collection : collections) {

            collection.setComicList(ccdd.get5ComicsInCollectionByCollectionId(collection.getCollectionId()));

        }

        return collections;
    }

    //Get All Collections For Single User By userId
    @RequestMapping(path = "collections/user/{userId}", method = RequestMethod.GET)
    public List<Collection> getUserCollections(@PathVariable int userId) {

        List<Collection> userCollection = collectiondd.getUserCollections(userId);

        for (Collection collection : userCollection) {

            collection.setComicList(ccdd.getAllComicsInCollectionByCollectionId(collection.getCollectionId()));

        }

        return userCollection;

    }

    @RequestMapping(path = "comic/stats", method = RequestMethod.GET)
    public List<ComicWithStats> getComicStats() {

        return cdd.getComicsWithAppearances();
    }

    //Get All Comic Objects In A Collection By CollectionId
    @RequestMapping(path = "collections/comics/{collectionId}", method = RequestMethod.GET)
    public List<Comic> getAllComicsInCollectionByCollectionId(@PathVariable Long collectionId) {
        return ccdd.getAllComicsInCollectionByCollectionId(collectionId);
    }

    //Delete Single Comic From Collection By collectionId && comicId


    //Get User Object By userId
    @RequestMapping(path = "users/id/{userId}", method = RequestMethod.GET)
    public User getUserById(@PathVariable long userId) {
        return ud.getUserById(userId);
    }

    //Get User Object By username
    @RequestMapping(path = "users/username/{username}", method = RequestMethod.GET)
    public User findByUsername(@PathVariable String username) {
        return ud.findByUsername(username);
    }

    //Get Single Character By Character Id
    @RequestMapping(path = "characters/id/{characterId}", method = RequestMethod.GET)
    public Character getCharacterById(@PathVariable long characterId) {
        return characterDataDao.getCharacterById(characterId);
    }

    //Get Single Character By Character Name
    @RequestMapping(path = "characters/name/{characterName}", method = RequestMethod.GET)
    public List<Comic> getAllComicsInCollectionByCollectionId(@PathVariable String characterName) {
        List<Comic> listOfComicsWithCharacter = characterDataDao.getAllComicsWithCharacterName(characterName);
        return listOfComicsWithCharacter;
    }

    //Get Single Series By Series Id
    @RequestMapping(path = "series/{seriesId}", method = RequestMethod.GET)
    public Series getSingleSeriesById(@PathVariable long seriesId) {
        return sdd.getSingleSeriesById(seriesId);
    }


    //Get All Comics Featuring A Character By Character Name
    @RequestMapping(path = "comics/character/{characterName}", method = RequestMethod.GET)
    public List<Comic> getAllComicsWithCharacterName(@PathVariable String characterName) {
        return characterDataDao.getAllComicsWithCharacterName(characterName);
    }
}
