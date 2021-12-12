package com.techelevator.dao;


import com.techelevator.model.Collection;
import com.techelevator.model.Comic;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCollectionDataDao implements CollectionDataDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCollectionDataDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean addComicToCollectionComic(Integer collectionId, Integer comicIdSerial){

        String sql = "INSERT INTO collection_comic(collection_id, comic_id)\n"+
                "VALUES(?,?) RETURNING collection_id";

        long collection_id = -1;

        try {

            collection_id = jdbcTemplate.queryForObject(sql, Integer.class, collectionId, comicIdSerial);

        }catch (DataAccessException e) {
            System.out.println("Adding comic to collection failed");
        }

        if(collection_id != -1){

            return true;
        }

        return false;
    }


    @Override
    public boolean createCollection(long userId, String collectionName){

        String sql = "INSERT INTO collections(collection_name, user_id)\n"+
                "VALUES(?,?) RETURNING collection_id";

        long collection_id = -1;

        try {
            collection_id = jdbcTemplate.queryForObject(sql, Long.class, collectionName, userId);

        }catch (DataAccessException e) {
            System.out.println("Collection creation failed");
        }

        if(collection_id != -1){

            return true;
        }

        return false;
    }

    @Override
    public Collection getCollectionById(long collectionId) {
     Collection collection = null;

     String sql = "SELECT * " +
             "FROM collections " +
             "WHERE collection_id =?;";

     SqlRowSet results = jdbcTemplate.queryForRowSet(sql, collectionId);
     if (results.next()) {
         collection = mapRowToCollections(results);
     }
        return collection;
    }

    @Override
    public List<Collection> getAllCollections() {
        List <Collection> collections = new ArrayList<>();

        String sql = "SELECT * FROM collections;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Collection collection = mapRowToCollections(results);
            collections.add(collection);

        }

        return collections;
    }

//    @Override
//    public List<List<Comic>> getAllCollectionsWithComics() {
//        List <List<Comic>> collectionWithComics = new ArrayList<>();
//
//        String sql = "SELECT * FROM collections;";
//
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
//
//        while (results.next()) {
//            Collection collection = mapRowToCollections(results);
//            collectionWithComics.add(collection);
//
//        }
//
//        return collections;
//    }




    @Override
    public List<Collection> getUserCollections(long userId) {
        List <Collection> collections = new ArrayList<>();

        String sql = "SELECT * " +
                "FROM collections " +
                "WHERE user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            Collection collection= mapRowToCollections(results);
            collections.add(collection);
        }
        return collections;
    }





    private Collection mapRowToCollections(SqlRowSet rowSet) {
        Collection collection = new Collection();

        collection.setCollectionId(rowSet.getLong("collection_id"));
        collection.setCollectionName(rowSet.getString("collection_name"));
        collection.setUserId(rowSet.getInt("user_id"));

        return collection;
    }
}
