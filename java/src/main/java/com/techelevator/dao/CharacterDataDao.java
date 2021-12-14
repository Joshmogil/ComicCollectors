package com.techelevator.dao;

import com.techelevator.model.Character;
import com.techelevator.model.Comic;
import com.techelevator.model.StatisticModels.CharacterWithStats;

import java.util.List;

public interface CharacterDataDao {

    List<CharacterWithStats> getCharactersWithAppearances();

    Integer addCharacterToCharacterTable(Long marvelId,String characterName,String imgUrl, String description);

    Character getCharacterById(long characterId);

    List<Comic> getAllComicsWithCharacterName(String characterName);
}
