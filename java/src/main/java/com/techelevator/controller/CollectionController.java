package com.techelevator.controller;

import com.techelevator.dao.CharacterDataDao;
import com.techelevator.dao.CollectionDataDao;
import com.techelevator.dao.ComicDataDao;
import com.techelevator.model.*;
import com.techelevator.model.StatisticModels.ComicWithStats;
import com.techelevator.services.MarvelComicService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class CollectionController {

    private CollectionDataDao collectionDao;
    private ComicDataDao comicDao;
    private CharacterDataDao characterDataDao;

    public CollectionController(CollectionDataDao collectionDao, ComicDataDao comicDao, CharacterDataDao characterDataDao) {
        this.collectionDao = collectionDao;
        this.comicDao = comicDao;
        this.characterDataDao = characterDataDao;
    }

    //Marvel Service Declaration
    String MARVEL_BASE_URL = "http://gateway.marvel.com/v1/public/";
    String privateKey = "197ef818f572516a2966e997ee7268e0cd590e21";
    String publicKey = "20afbe7ebe8ad8af2c91b02a275e06cc";

    MarvelComicService marvelComicService = new MarvelComicService(MARVEL_BASE_URL, privateKey,publicKey);

    //Create a New Collection
    @RequestMapping(path = "collections/create", method = RequestMethod.POST)//make a user not found exception
    public long createCollection(@RequestBody CreateCollectionDTO createCollectionDTO) {

        long collectionCreated = collectionDao.createCollection(createCollectionDTO.getUserId(),createCollectionDTO.getCollectionName());

        return collectionCreated;
    }

    @RequestMapping(path = "joshcomic/{comicId}", method = RequestMethod.GET)
    public Long getComicStats(@PathVariable int comicId) {

        return comicDao.getMarvelComicIdByOurComicId(comicId);

    }


    //Add Comic To Collection
    @RequestMapping(path = "collections/addcomic", method = RequestMethod.POST)//make a user not found exception
    public boolean addComicToCollection(@RequestBody AddComicDTO addComicDTO) {
        /*Example json body
        {
            "collection_id":2,
            "comic_id": 27649
        }
        */
        boolean comicAddedToCollection = false;

        try {

            MarvelComic marvelComic = marvelComicService.getComic(addComicDTO.getComic_id());

            String useableImgUrl = marvelComic.getImg_url() + "/portrait_uncanny." + marvelComic.getExtension();

            Integer comicId = comicDao.addComicToComicTable(marvelComic.getMarvel_id(), marvelComic.getTitle(), useableImgUrl, marvelComic.getDescription());


            comicAddedToCollection = collectionDao.addComicToCollectionComic(addComicDTO.getCollection_id(), comicId);


            try{

                List<MarvelCharacter> comicCharacters = marvelComicService.getCharacterListByComicId(addComicDTO.getComic_id());

                for (MarvelCharacter marvelCharacter : comicCharacters){

                    String characterUrl = marvelCharacter.getImg_url() + "/portrait_uncanny." + marvelCharacter.getExtension();

                    characterDataDao.addCharacterToCharacterTable(marvelCharacter.getCharacterId(),marvelCharacter.getCharacterName(),characterUrl,marvelCharacter.getDescription());

                }

            }catch(Exception thisCouldBeLessGeneral){
                System.out.println("Failed to find comic's characters");
            }

        } catch (Exception thisNeedToBeLessGeneral) {
            System.out.println("Failed to add comic to collection");
        }

        return comicAddedToCollection;

    }

    @RequestMapping(path = "collections/addcomics", method = RequestMethod.POST)//make a user not found exception
    public Boolean addComicsToCollection(@RequestBody List<AddComicDTO> addComicsDTO) {
        /*Example json body

        [
            {
                "collection_id":2,
                "comic_id": 20
            },
            {
                "collection_id":4,
                "comic_id": 25
            }
         ]

        */

        boolean comicAddedToCollection = false;

        for(AddComicDTO addComicDTO:addComicsDTO) {
//                Long marvelIdOfcomic = comicDao.getMarvelComicIdByOurComicId(addComicDTO.getComic_id());
//                MarvelComic marvelComic = marvelComicService.getComic(marvelIdOfcomic);

                //String useableImgUrl = marvelComic.getImg_url() + "/portrait_uncanny." + marvelComic.getExtension();

                //Integer comicId = comicDao.addComicToComicTable(marvelComic.getMarvel_id(), marvelComic.getTitle(), useableImgUrl, marvelComic.getDescription());
                comicAddedToCollection = collectionDao.addComicToCollectionComic(addComicDTO.getCollection_id(), addComicDTO.getComic_id());
//                try {
//
//                    List<MarvelCharacter> comicCharacters = marvelComicService.getCharacterListByComicId(addComicDTO.getComic_id());
//
//                    for (MarvelCharacter marvelCharacter : comicCharacters) {
//
//                        String characterUrl = marvelCharacter.getImg_url() + "/portrait_uncanny." + marvelCharacter.getExtension();
//
//                        characterDataDao.addCharacterToCharacterTable(marvelCharacter.getCharacterId(), marvelCharacter.getCharacterName(), characterUrl, marvelCharacter.getDescription());
//
//                    }
//
//                } catch (Exception thisCouldBeLessGeneral) {
//                    System.out.println("Failed to find comic's characters");
//                }
        }
        return true;

    }

    @RequestMapping(path = "collections/addcomics/new", method = RequestMethod.POST)//make a user not found exception
    public Boolean addNewComicsToCollection(@RequestBody List<AddComicDTO> addComicsDTO) {
        /*Example json body

        [
            {
                "collection_id":2,
                "comic_id": 27649
            },
            {
                "collection_id":4,
                "comic_id": 27649
            }
         ]

        */

        boolean comicAddedToCollection = false;

        for(AddComicDTO addComicDTO:addComicsDTO) {


            //first check to see if the comic is in the database
            if (true) {
            //if it is in the database, just add to the CollectionComic table, because the characters should already be in the table

                comicAddedToCollection = collectionDao.addComicToCollectionComic(addComicDTO.getCollection_id(), addComicDTO.getComic_id());

            } else {

                try {

                    MarvelComic marvelComic = marvelComicService.getComic(addComicDTO.getComic_id());
                    String useableImgUrl = marvelComic.getImg_url() + "/portrait_uncanny." + marvelComic.getExtension();
                    Integer comicSerialForCollection = comicDao.addComicToComicTable(marvelComic.getMarvel_id(), marvelComic.getTitle(), useableImgUrl, marvelComic.getDescription());
                    comicAddedToCollection = collectionDao.addComicToCollectionComic(addComicDTO.getCollection_id(), comicSerialForCollection);

                    try {

                        List<MarvelCharacter> comicCharacters = marvelComicService.getCharacterListByComicId(addComicDTO.getComic_id());
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


        return true;

    }



}
