package com.techelevator.dao;

import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service

public class JdbcComicDao implements ComicDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcComicDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
    private Comic mapRowToComic(SqlRowSet rowset) {
        Comic comic = new Comic();
        comic.setId(rowset.getLong("comic_id"));

        return comic;
    }

     */
}
