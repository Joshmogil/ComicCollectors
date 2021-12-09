package com.techelevator.dao;


import com.techelevator.model.Collection;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCollectionDataDao implements CollectionDataDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCollectionDataDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
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

    @Override
    public boolean createCollection(String collection_name, long user_id) {

        String sql = "INSERT INTO collections (collection_name, user_id) " +
                "VALUES (?, ?) RETURNING collection_id";

        Integer newCollectionId;

        try {
            newCollectionId = jdbcTemplate.queryForObject(sql, Integer.class, collection_name, user_id);
        } catch (DataAccessException e) {
            return false;
        }

        return true;

    }

    private Collection mapRowToCollections(SqlRowSet rowSet) {
        Collection collection = new Collection();

        collection.setCollectionId(rowSet.getLong("collection_id"));
        collection.setCollectionName(rowSet.getString("collection_name"));
        collection.setUserId(rowSet.getInt("user_id"));

        return collection;
    }
}
