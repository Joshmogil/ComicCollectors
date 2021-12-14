package com.techelevator.controller;


import com.techelevator.dao.CharacterDataDao;
import com.techelevator.dao.CollectionDataDao;
import com.techelevator.dao.ComicDataDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.MarvelCharacter;
import com.techelevator.model.MarvelComic;
import com.techelevator.model.User;
import com.techelevator.services.MarvelComicService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class DatabaseTestDataController {

    private UserDao userDao;
    private CollectionDataDao collectionDataDao;
    private ComicDataDao comicDao;
    private CharacterDataDao characterDataDao;

    public DatabaseTestDataController() {
    }

    public DatabaseTestDataController(UserDao userDao, CollectionDataDao collectionDataDao, ComicDataDao comicDao, CharacterDataDao characterDataDao) {
        this.userDao = userDao;
        this.collectionDataDao = collectionDataDao;
        this.comicDao = comicDao;
        this.characterDataDao = characterDataDao;
    }

    String MARVEL_BASE_URL = "http://gateway.marvel.com/v1/public/";
    String privateKey = "197ef818f572516a2966e997ee7268e0cd590e21";
    String publicKey = "20afbe7ebe8ad8af2c91b02a275e06cc";

    MarvelComicService marvelComicService = new MarvelComicService(MARVEL_BASE_URL, privateKey,publicKey);

    @RequestMapping(path = "testdata", method = RequestMethod.GET)
    public boolean generateTestData(){

        for(int i =3;i<9;i++){

                Integer collectionId = null;
                try {
                    collectionId = Math.toIntExact(createCollection(i)); //creates collections for each user
                } catch (ArithmeticException e) {
                    System.out.println("Too many collections in database");
                }

                addComicsToCollection(collectionId); //adds comics to those collections

        }

        return true;
    }

    public long createCollection(int i){
        String [] usernames = {"Michael Corleone","Johnny Candito","Anthony Ten","Michael Nguyen","Flash Fan 700","Marvel Fan1","711Num1Customer"};

        return collectionDataDao.createCollection(i,usernames[i-3]+"'s Collection");

    }

    public void addComicsToCollection(Integer collectionId){

        long [] randomComicIds = {211,230,700,725,767};

        for(int i = 0; i<5;i++) {

            int max = randomComicIds.length;
            int min = 0;
            int range = max - min + 1;

            int rand = (int)(Math.random()*range) + min;

            try {

                MarvelComic marvelComic = marvelComicService.getComic(randomComicIds[rand]);

                String useableImgUrl = marvelComic.getImg_url() + "/portrait_uncanny." + marvelComic.getExtension();

                Integer comicSerialForCollection = comicDao.addComicToComicTable(marvelComic.getMarvel_id(), marvelComic.getTitle(), useableImgUrl, marvelComic.getDescription());

                collectionDataDao.addComicToCollectionComic(collectionId, comicSerialForCollection);

                try {

                    List<MarvelCharacter> comicCharacters = marvelComicService.getCharacterListByComicId(randomComicIds[rand]);
                    System.out.println(comicCharacters.get(0).getCharacterName());

                    for (MarvelCharacter marvelCharacter : comicCharacters) {

                        String characterUrl = marvelCharacter.getImg_url() + "/portrait_uncanny." + marvelCharacter.getExtension();

                        characterDataDao.addCharacterToCharacterTable(marvelCharacter.getCharacterId(), marvelCharacter.getCharacterName(), characterUrl, marvelCharacter.getDescription());

                    }

                } catch (Exception thisCouldBeLessGeneral) {
                    System.out.println("Failed to find comic's characters");
                }

            } catch (Exception thisNeedToBeLessGeneral) {
                System.out.println("Failed to add comic to collection");
            }
        }


    }



}
