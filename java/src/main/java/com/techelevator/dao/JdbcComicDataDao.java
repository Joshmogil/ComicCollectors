package com.techelevator.dao;

import com.techelevator.model.Comic;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcComicDataDao implements ComicDataDao {

    private JdbcTemplate jdbcTemplate;

    JdbcComicDataDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getComicDataByID(int comic_id){

        String sql = "SELECT comic_title FROM comics WHERE comic_id = ?;";

        return jdbcTemplate.queryForObject(sql,String.class, comic_id);

    }

    @Override
    public Comic getComicById(int comic_id) {

        Comic comic = null;


        String sql = "SELECT * FROM comics WHERE comic_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, comic_id);
        if (results.next()) {
            comic = mapRowToComics(results);
        }
        return comic;
    }

    @Override
    public List<Comic> getAllComics() {
        List<Comic> comics = new ArrayList<>();

        String sql = "SELECT * FROM comics;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Comic comic = mapRowToComics(results);
            comics.add(comic);
        }
        return comics;
    }

    private Comic mapRowToComics (SqlRowSet rowSet) {

        Comic comic = new Comic();

        comic.setComicId(rowSet.getLong("comic_id"));
        comic.setMarvelId(rowSet.getLong("marvel_id"));
        comic.setComicTitle(rowSet.getString("comic_title"));
        comic.setImgUrl(rowSet.getString("img_url"));

        return comic;
    }


}
