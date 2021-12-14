package com.techelevator.dao;

import com.techelevator.model.Character;
import com.techelevator.model.Comic;
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
