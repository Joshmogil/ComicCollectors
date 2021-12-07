package com.techelevator.dao;

import com.techelevator.model.Character;
import com.techelevator.model.Comic;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class JdbcComicDao implements ComicDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcComicDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Comic getSingleComicByID(long comic_id) {

        Comic comic = null;

        String sql = "SELECT * " +
                     "FROM comics " +
                     "WHERE comic_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, comic_id);
        if (results.next()) {
            comic = mapRowToComics(results);
        }
        return comic;
    }

//add to collection

    /*
    private Comic mapRowToComic(SqlRowSet rowset) {
        Comic comic = new Comic();
        comic.setId(rowset.getLong("comic_id"));

        return comic;
    }

     */

    private Comic mapRowToComics(SqlRowSet rowSet) {
        Comic comic = new Comic();

        comic.setComicId(rowSet.getLong("comic_id"));
        comic.setComicTitle(rowSet.getNString("comic_title"));
        comic.setImgUrl(rowSet.getString("img_url"));

        return comic;
    }
}
