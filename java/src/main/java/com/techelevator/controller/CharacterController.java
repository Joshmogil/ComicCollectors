package com.techelevator.controller;


import com.techelevator.dao.CharacterDataDao;
import com.techelevator.dao.ComicDataDao;
import com.techelevator.model.Comic;
import com.techelevator.model.MarvelCharacter;
import com.techelevator.model.StatisticModels.CharacterWithStats;
import com.techelevator.services.MarvelComicService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class CharacterController {

    private ComicDataDao comicDao;
    private CharacterDataDao characterDataDao;

    public CharacterController(ComicDataDao comicDao, CharacterDataDao characterDataDao) {
        this.comicDao = comicDao;
        this.characterDataDao = characterDataDao;
    }

    //Marvel Service Declaration
    String MARVEL_BASE_URL = "http://gateway.marvel.com/v1/public/";
    String privateKey = "197ef818f572516a2966e997ee7268e0cd590e21";
    String publicKey = "20afbe7ebe8ad8af2c91b02a275e06cc";

    MarvelComicService marvelComicService = new MarvelComicService(MARVEL_BASE_URL, privateKey,publicKey);

    @RequestMapping(path = "character/stats", method = RequestMethod.GET)
    public List<CharacterWithStats> getCharacterStats() {

        return characterDataDao.getCharactersWithAppearances();
    }

    @RequestMapping(path = "getMyCharacters", method = RequestMethod.GET)
    public Boolean getAllComicIds() {

         List<Long> comicIdList = comicDao.getAllMarvelIdsOfComics();

        try{

            for(Long comicId : comicIdList) {

                List<MarvelCharacter> comicCharacters = marvelComicService.getCharacterListByComicId(comicId);

                for (MarvelCharacter marvelCharacter : comicCharacters) {

                    if(characterDataDao.getCharacterByMarvelId(marvelCharacter.getCharacterId())==null){
                        //if character is not in character table, add the character
                        String characterUrl = marvelCharacter.getImg_url() + "/portrait_uncanny." + marvelCharacter.getExtension();

                        Integer characterId = characterDataDao.addCharacterToCharacterTable(marvelCharacter.getCharacterId(), marvelCharacter.getCharacterName(), characterUrl, marvelCharacter.getDescription());

                        characterDataDao.addCharacterToComicCharacterTable(comicDao.getComicSerialByMarvelId(comicId),characterId);

                    }else{

                        characterDataDao.addCharacterToComicCharacterTable(comicDao.getComicSerialByMarvelId(comicId), characterDataDao.getCharacterIdByMarvelCharacterId((int) marvelCharacter.getCharacterId()));

                    }

                }
            }

            return true;
        }catch(Exception thisCouldBeLessGeneral){
            System.out.println("Failed to find comic's characters");
        }

            return false;
    }



}
