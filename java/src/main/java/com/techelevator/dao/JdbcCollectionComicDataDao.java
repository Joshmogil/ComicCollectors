package com.techelevator.dao;

import com.techelevator.model.Collection;
import com.techelevator.model.CollectionComic;
import com.techelevator.model.Comic;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedOutputStream;

@Service
public class JdbcCollectionComicDataDao implements CollectionComicDataDao {

    private JdbcTemplate jdbcTemplate;

    JdbcCollectionComicDataDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    //Add Comic to Collection
//    @Override
//    public boolean addComicToCollection(long userId, long comicId, long collectionId) {
//        String sql = "INSERT INTO collection_comic(collection_id, comic_id) " +
//                     "JOIN collection_comic cc ON collections cl "
//    }

    @Override
    public List<Comic> getAllComicsInCollectionByCollectionId(Long collectionId) {
        List<Comic> comics = new ArrayList<>();
//        Collection collection = new Collection();

        String sql = "SELECT c.comic_id, c.marvel_id, c.comic_title, c.img_url, c.description FROM comics c " +
                "JOIN collection_comic l ON c.comic_id = l.comic_id " +
                "JOIN collections n ON n.collection_id = l.collection_id " +
                "WHERE n.collection_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, collectionId);

        while (results.next()) {
            Comic comic = mapRowToComics(results);
            comics.add(comic);
        }
        return comics;


//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, collectionId);
//        while (results.next()) {
//            Comic comic = mapRowToComics(results);
//            collection = mapRowToCollections(results);
//            comics.add(comic);
//        }
//        collection.setListOfComics(comics);
//        return collection;
    }

    @Override
    public void deleteComicFromCollection(long collectionId, long comicId) {
        String sql = "DELETE FROM collection_comic WHERE collection_id = ? AND comic_id = ?;";

        jdbcTemplate.update(sql, collectionId, comicId);
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
        comic.setMarvelId(rowSet.getLong("marvel_id"));
        comic.setComicTitle(rowSet.getString("comic_title"));
        comic.setImgUrl(rowSet.getString("img_url"));
        comic.setDescription(rowSet.getString("description"));

        return comic;
    }

    private Collection mapRowToCollections(SqlRowSet rowSet) {

        Collection collection = new Collection();

        collection.setCollectionId(rowSet.getLong("collection_id"));
        collection.setCollectionName(rowSet.getString("collection_name"));
        collection.setUserId(rowSet.getLong("user_id"));

        return collection;
    }
}
