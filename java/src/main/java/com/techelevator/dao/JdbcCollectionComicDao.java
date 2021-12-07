package com.techelevator.dao;

import com.techelevator.model.Collection;
import com.techelevator.model.CollectionComic;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

    @Component
    public class JdbcCollectionComicDao implements CollectionComicDao {

        private JdbcTemplate jdbcTemplate;

        public JdbcCollectionComicDao(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        @Override
        public void addComicToCollection(long collection_id, long comic_id) {

            String sql = "INSERT INTO collection_comic (collection_id, comic_id) " +
                    "VALUES (?, ?);";

        }

        private CollectionComic mapRowToCollectionComic(SqlRowSet rowSet) {

            CollectionComic collectionComic = new CollectionComic();

            collectionComic.setCollection_id(rowSet.getLong("collection_id"));
            collectionComic.setComic_id(rowSet.getLong("comic_id"));
            collectionComic.setUser_id(rowSet.getLong("user_id"));

            return collectionComic;
        }
    }
