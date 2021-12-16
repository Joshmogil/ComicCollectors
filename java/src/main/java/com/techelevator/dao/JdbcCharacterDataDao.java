package com.techelevator.dao;

import com.techelevator.model.Character;
import com.techelevator.model.Comic;
import com.techelevator.model.MarvelCharacter;
import com.techelevator.model.StatisticModels.CharacterWithStats;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcCharacterDataDao implements CharacterDataDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcCharacterDataDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer getCharacterIdByMarvelCharacterId(Long characterId){

        Integer characterIdResult = -1;

        try {

            String sql = "SELECT character_id FROM characters WHERE marvel_character_id = ? ;";

            characterIdResult = jdbcTemplate.queryForObject(sql, Integer.class, characterId);

        }catch(DataAccessException e){

        }

        return characterIdResult;

    }

    @Override
    public Integer addCharacterToComicCharacterTable(Integer comicId, Integer characterId){

        String sql = "INSERT INTO comic_character(comic_id, character_id) "+
                "VALUES(?,?) RETURNING character_id;";

        Integer characterNum = null;

        try {
            characterNum = jdbcTemplate.queryForObject(sql, Integer.class, comicId, characterId);

        }catch (DataAccessException e) {
            System.out.println("Adding character to characters table failed");
            System.out.println(e);
        }

        return characterNum;
    }


    @Override
    public Integer addCharacterToCharacterTable(Long marvelId,String characterName,String imgUrl, String description){

        String sql = "INSERT INTO characters(marvel_character_id, character_name, img_url, description)\n"+
                "VALUES(?,?,?,?) RETURNING character_id";

        Integer characterId = null;

        try {
            characterId = jdbcTemplate.queryForObject(sql, Integer.class, marvelId, characterName, imgUrl, description);

        }catch (DataAccessException e) {
            System.out.println("Adding character to characters table failed");
            System.out.println(e);
        }

        return characterId;
    }

    @Override
    public Character getCharacterById(long character_id){
        Character character = null;

        String sql = "SELECT * " +
                    "FROM characters " +
                    "WHERE character_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, character_id);
        if (results.next()){
            character = mapRowToCharacters(results);
        }
        return character;
    }

    @Override
    public Character getCharacterByMarvelId(long character_id){
        Character character = null;

        String sql = "SELECT * " +
                "FROM characters " +
                "WHERE marvel_character_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, character_id);
        if (results.next()){
            character = mapRowToCharacters(results);
        }
        return character;
    }

    @Override
    public List<CharacterWithStats> getCharactersWithAppearances() {
        List<CharacterWithStats> charactersWithStats = new ArrayList<>();

        String sql = "SELECT marvel_character_id, character_name, img_url, description, COUNT(character_name) " +
                "FROM characters " +
                "JOIN comic_character ON characters.character_id = comic_character.character_id "+
                "GROUP by marvel_character_id, character_name, img_url, description;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            CharacterWithStats characterWithStats = mapRowToCharacterWithStats(results);
            charactersWithStats.add(characterWithStats);
        }
        return charactersWithStats;
    }

    @Override
    public List<Comic> getAllComicsWithCharacterName(String characterName) {
        List<Comic> comics = new ArrayList<>();

        String sql = "SELECT comics.comic_id, comics.marvel_id, comics.comic_title, comics.img_url, comics.description FROM comics " +
                "JOIN comic_character on comics.comic_id = comic_character.comic_id " +
                "JOIN characters on comic_character.character_id = characters.character_id " +
                "WHERE LOWER(characters.character_name) LIKE LOWER(?)";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, characterName);

        while (results.next()) {
            Comic comic = mapRowToComics(results);
            comics.add(comic);
        }
        return comics;
    }

    private CharacterWithStats mapRowToCharacterWithStats(SqlRowSet rowSet){
        CharacterWithStats characterWithStats = new CharacterWithStats();

        characterWithStats.setCharacterId(rowSet.getLong("marvel_character_id"));
        characterWithStats.setCharacterName(rowSet.getString("character_name"));
        characterWithStats.setImgUrl(rowSet.getString("img_url"));
        characterWithStats.setDescription(rowSet.getString("description"));
        characterWithStats.setCount(rowSet.getLong("count"));

        return characterWithStats;
    }


    private Character mapRowToCharacters(SqlRowSet rowSet){
        Character character = new Character();

        character.setCharacterId(rowSet.getLong("character_id"));
        character.setCharacterName(rowSet.getString("character_name"));

        return character;
    }

    private Comic mapRowToComics(SqlRowSet rowSet) {

        Comic comic = new Comic();

        comic.setComicId(rowSet.getLong("comic_id"));
        comic.setMarvelId(rowSet.getLong("marvel_id"));
        comic.setComicTitle(rowSet.getString("comic_title"));
        comic.setImgUrl(rowSet.getString("img_url"));
        comic.setDescription(rowSet.getString("description"));

        return comic;
    }

}
