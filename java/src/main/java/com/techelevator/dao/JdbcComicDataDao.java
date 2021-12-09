package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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


}
