package com.techelevator.XX;

import com.techelevator.XX.XX_CollectionDao;
import com.techelevator.model.Collection;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class XX_JdbcCollectionDao implements XX_CollectionDao {

    private JdbcTemplate jdbcTemplate;

    public XX_JdbcCollectionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Collection getSingleCollectionByCollectionId(long collection_id) {
        Collection collection = null;

        String sql = "SELECT * " +
                     "FROM collections " +
                     "WHERE collection_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, collection_id);
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
    public List<Collection> getAllCollectionsByUser(long user_id) {
        List <Collection> collections = new ArrayList<>();

        String sql = "SELECT * " +
                     "FROM collections " +
                     "WHERE user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, user_id);
        while (results.next()) {
            Collection collection = mapRowToCollections(results);
            collections.add(collection);
        }
        return collections;
    }

    @Override
    public boolean createCollection(String collection_name, long user_id) {

        String sql = "INSERT INTO collections (collection_name, user_id) " +
                     "VALUES (?, ?) RETURNING collection_id;";
        Integer newCollectionId;
        try {
            newCollectionId = jdbcTemplate.queryForObject(sql, Integer.class, collection_name, user_id);
        } catch (DataAccessException e) {
            return false;
        }
        return true;
    }


//create

    /*
    private Collection mapRowToCollection(SqlRowSet rowset) {
        Collection collection = new Collection();
        collection.setUserId(rowset.getLong("user_id"));

        how to set a list?

        return collection;
    }

     */

    private Collection mapRowToCollections(SqlRowSet rowSet) {

        Collection collection = new Collection();

        collection.setCollectionId(rowSet.getLong("collection_id"));
        collection.setCollectionName(rowSet.getString("collection_name"));
        collection.setUserId(rowSet.getInt("user_id"));

        return collection;
    }

}
