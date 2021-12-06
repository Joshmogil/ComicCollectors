package com.techelevator.dao;

import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service

public class JdbcCollectionDao implements CollectionDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcCollectionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    /*
    private Collection mapRowToCollection(SqlRowSet rowset) {
        Collection collection = new Collection();
        collection.setUserId(rowset.getLong("user_id"));

        how to set a list?

        return collection;
    }

     */
}
