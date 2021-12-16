package com.techelevator.controller;

import com.techelevator.dao.CharacterDataDao;
import com.techelevator.dao.CollectionComicDataDao;
import com.techelevator.dao.CollectionDataDao;
import com.techelevator.dao.ComicDataDao;
import com.techelevator.model.*;
import com.techelevator.services.MarvelComicService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class CollectionController {

    private CollectionDataDao collectionDao;
    private ComicDataDao comicDao;
    private CharacterDataDao characterDataDao;
    private CollectionComicDataDao collectionComicDataDao;

    public CollectionController(CollectionDataDao collectionDao, ComicDataDao comicDao, CharacterDataDao characterDataDao, CollectionComicDataDao collectionComicDataDao) {
        this.collectionDao = collectionDao;
        this.comicDao = comicDao;
        this.characterDataDao = characterDataDao;
        this.collectionComicDataDao = collectionComicDataDao;
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

            MarvelComic marvelComic = marvelComicService.getComic(addComicDTO.getComicId());

            String useableImgUrl = marvelComic.getImgUrl() + "/portrait_uncanny." + marvelComic.getExtension();

            Integer comicId = comicDao.addComicToComicTable(marvelComic.getMarvelId(), marvelComic.getTitle(), useableImgUrl, marvelComic.getDescription());


            comicAddedToCollection = collectionDao.addComicToCollectionComic(addComicDTO.getCollectionId(), comicId);


            try{

                List<MarvelCharacter> comicCharacters = marvelComicService.getCharacterListByComicId(addComicDTO.getComicId());

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

        boolean comicAddedToCollection = false;

        for(AddComicDTO addComicDTO:addComicsDTO) {

                Integer check = collectionComicDataDao.checkIfComicIdInCollectionComic(addComicDTO.getComicId());

                comicAddedToCollection = collectionDao.addComicToCollectionComic(addComicDTO.getCollectionId(), addComicDTO.getComicId());

                //check if comic exists in collection_comic, if not add that comic's characters

                if(check == -1){

                    Long marvelComicId = comicDao.getMarvelComicIdByOurComicId(addComicDTO.getComicId());

                    List<MarvelCharacter> characterList = marvelComicService.getCharacterListByComicId(marvelComicId);

                    for(MarvelCharacter marvelCharacter:characterList){

                        //check if that character is in the character table, add them to the character table and link them to the comicId if they are not

                        Integer characterId = characterDataDao.getCharacterIdByMarvelCharacterId(marvelCharacter.getCharacterId());

                        if(characterId == -1){

                            Integer newCharacterId = characterDataDao.addCharacterToCharacterTable(marvelCharacter.getCharacterId(),marvelCharacter.getCharacterName(),marvelCharacter.getImg_url(),marvelCharacter.getDescription());

                            characterDataDao.addCharacterToComicCharacterTable(addComicDTO.getComicId(),newCharacterId);

                        }


                    }

                }

        }
        return true;

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "collections/delete", method = RequestMethod.DELETE)
    public void deleteCollection(@RequestBody List<Integer> collectionsToDelete) {

        for(Integer collection:collectionsToDelete) {

            collectionDao.deleteCollection(collection);

        }

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "collections/delete/{collectionId}", method = RequestMethod.DELETE)
    public void deleteCollection(@PathVariable Integer collectionId) {

            collectionDao.deleteCollection(collectionId);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "collections/delete/comics", method = RequestMethod.DELETE)
    public Boolean deleteComicFromCollection(@RequestBody AddComicDTO[] addComicsDTO) {

        boolean comicDeletedFromCollection = false;

        for(AddComicDTO addComicDTO:addComicsDTO) {

            comicDeletedFromCollection = collectionDao.deleteComicFromCollectionComic(addComicDTO.getCollectionId(),addComicDTO.getComicId());

        }

        return comicDeletedFromCollection;

    }










    @RequestMapping(path = "collections/addcomics/new", method = RequestMethod.POST)//make a user not found exception
    public Boolean addNewComicsToCollection(@RequestBody List<AddComicDTO> addComicsDTO) {
        /*
        This method takes in a marvel id NOT a comic_id from our database

        Example json body

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

            if (comicDao.getComicSerialByMarvelId(addComicDTO.getComicId()) != null) {
            //if it is in the database, just add to the CollectionComic table, because the characters should already be in the table

                comicAddedToCollection = collectionDao.addComicToCollectionComic(addComicDTO.getCollectionId(), comicDao.getComicSerialByMarvelId(addComicDTO.getComicId()));

            } else {

                try {
                    MarvelComic marvelComic = marvelComicService.getComic(addComicDTO.getComicId());

                    String useableImgUrl = marvelComic.getImgUrl() + "/portrait_uncanny." + marvelComic.getExtension();
                    Integer comicSerialForCollection = comicDao.addComicToComicTable(marvelComic.getMarvelId(), marvelComic.getTitle(), useableImgUrl, marvelComic.getDescription());
                    comicAddedToCollection = collectionDao.addComicToCollectionComic(addComicDTO.getCollectionId(), comicSerialForCollection);

                    try {

                        List<MarvelCharacter> comicCharacters = marvelComicService.getCharacterListByComicId(addComicDTO.getComicId());

                        for (MarvelCharacter marvelCharacter : comicCharacters) {

                            System.out.println(marvelCharacter.getCharacterName() + " " + marvelCharacter.getCharacterId());

                            Integer characterIdExists = characterDataDao.getCharacterIdByMarvelCharacterId(marvelCharacter.getCharacterId());

                            System.out.println(characterIdExists);

                            if(characterIdExists == -1) {

                                String characterUrl = marvelCharacter.getImg_url() + "/portrait_uncanny." + marvelCharacter.getExtension();
                                characterDataDao.addCharacterToCharacterTable(marvelCharacter.getCharacterId(), marvelCharacter.getCharacterName(), characterUrl, marvelCharacter.getDescription());

                            }
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
