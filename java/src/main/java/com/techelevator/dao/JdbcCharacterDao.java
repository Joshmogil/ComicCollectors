package com.techelevator.dao;

import com.techelevator.model.Character;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcCharacterDao implements CharacterDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCharacterDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Character getCharacterById(long character_id) {

        Character character = null;

        String sql = "SELECT * " +
                     "FROM characters " +
                     "WHERE character_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, character_id);
        if (results.next()) {
            character = mapRowToCharacters(results);
        }
        return character;


    }

    private Character mapRowToCharacters(SqlRowSet rowSet) {
        Character character = new Character();

        character.setCharacterId(rowSet.getLong("character_id"));
        character.setCharacterName(rowSet.getString("character_name"));

        return character;
    }

}
