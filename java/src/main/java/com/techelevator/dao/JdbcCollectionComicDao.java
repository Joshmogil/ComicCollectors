package com.techelevator.dao;

import com.techelevator.model.Collection;
import com.techelevator.model.CollectionComic;
import com.techelevator.model.Comic;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcCollectionComicDao implements CollectionComicDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCollectionComicDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}


    @Override
    public List<Comic> getAllComicsInCollectionByCollectionId(long collection_id) {
        List <Comic> comics = new ArrayList<>();

        String sql = "SELECT c.comic_id, c.comic_title, c.img_url FROM comics c " +
                     "JOIN collection_comic l ON c.comic_id = l.comic_id" +
                     "WHERE collection_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, collection_id);
        while (results.next()) {
            Comic comic = mapRowToComics(results);
            comics.add(comic);
        }
        return comics;
    }

    private CollectionComic mapRowToCollectionComic(SqlRowSet rowSet) {

        CollectionComic collectionComic = new CollectionComic();

        collectionComic.setCollectionId(rowSet.getLong("collection_id"));
        collectionComic.setComic_id(rowSet.getLong("comic_id"));

        return collectionComic;
    }

    private Comic mapRowToComics(SqlRowSet rowSet) {

        Comic comic = new Comic();

        comic.setComicId(rowSet.getLong("comic_id"));
        comic.setComicTitle(rowSet.getString("comic_title"));
        comic.setImgUrl(rowSet.getString("img_url"));

        return comic;
    }
}
