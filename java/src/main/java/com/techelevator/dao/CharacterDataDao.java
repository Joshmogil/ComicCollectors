package com.techelevator.dao;

import com.techelevator.model.Character;
import com.techelevator.model.Comic;

import java.util.List;

public interface CharacterDataDao {

    Integer addCharacterToCharacterTable(Long marvelId,String characterName,String imgUrl, String description);

    Character getCharacterById(long characterId);

    List<Comic> getAllComicsWithCharacterName(String characterName);
}
