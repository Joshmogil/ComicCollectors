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

        String sql = "SELECT c.comic_id, c.marvel_id, c.comic_title, c.img_url FROM comics c " +
                "JOIN comic_character ON c.comic_id = comic_character.comic_id " +
                "JOIN characters h ON comic_character.character_id = h.character_id " +
                "WHERE h.character_name = ?;";

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

        return comic;
    }

}
