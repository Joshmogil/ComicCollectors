package com.techelevator.dao;

import com.techelevator.model.Comic;
import com.techelevator.model.StatisticModels.CharacterWithStats;
import com.techelevator.model.StatisticModels.ComicWithStats;
import org.springframework.dao.DataAccessException;
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


    public Integer addComicToComicTable(Long marvelId,String comicTitle,String imgUrl, String description){

        String sql = "INSERT INTO comics(marvel_id, comic_title, img_url, description) "+
                "SELECT ?,?,?,? WHERE NOT EXISTS (SELECT comic_id FROM comics WHERE marvel_id = ?) " +
                "RETURNING comic_id";

        Integer comicId = null;

        try {
            comicId = jdbcTemplate.queryForObject(sql, Integer.class, marvelId, comicTitle, imgUrl, description, marvelId);

            if(comicId == null){
                String sql2 = "SELECT comic_ic FROM comics WHERE marvel_id = ? LIMIT 1 ";

                comicId = jdbcTemplate.queryForObject(sql2,Integer.class, marvelId);

            }
        }catch (DataAccessException e) {
            System.out.println("Adding comic to comics table failed");
        }

        return comicId;
    }


    public Integer getComicSerialByMarvelId(long marvelId){

        String sql = "SELECT comic_id FROM comics WHERE marvel_id = ?;";

        return jdbcTemplate.queryForObject(sql,Integer.class, marvelId);

    }

    @Override
    public Long getMarvelComicIdByOurComicId(int comic_id){

        String sql = "SELECT marvel_id FROM comics WHERE comic_id = ?;";

        return jdbcTemplate.queryForObject(sql,Long.class, comic_id);

    }

    @Override
    public List<ComicWithStats> getComicsWithAppearances() {
        List<ComicWithStats> comicsWithStats = new ArrayList<>();

        String sql = "SELECT marvel_id , comic_title, img_url, description, COUNT(marvel_id) " +
                "FROM comics AS name_count " +
                "GROUP by marvel_id, comic_title, img_url, description;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            ComicWithStats comicWithStats = mapRowToComicWithStats(results);
            comicsWithStats.add(comicWithStats);

        }
        return comicsWithStats;
    }

    @Override
    public List<Long> getAllMarvelIdsOfComics(){

        List<Long> marvelIdList= new ArrayList<>();

        String sql = "SELECT marvel_id FROM comics;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            marvelIdList.add(results.getLong(1));
        }
        return marvelIdList;

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

    private ComicWithStats mapRowToComicWithStats (SqlRowSet rowSet) {

        ComicWithStats comicWithStats = new ComicWithStats();

        comicWithStats.setMarvelId(rowSet.getLong("marvel_id"));
        comicWithStats.setTitle(rowSet.getString("comic_title"));
        comicWithStats.setImgUrl(rowSet.getString("img_url"));
        comicWithStats.setDescription(rowSet.getString("description"));
        comicWithStats.setCount(rowSet.getLong("count"));

        return comicWithStats;
    }

    private Comic mapRowToComics (SqlRowSet rowSet) {

        Comic comic = new Comic();

        comic.setComicId(rowSet.getLong("comic_id"));
        comic.setMarvelId(rowSet.getLong("marvel_id"));
        comic.setComicTitle(rowSet.getString("comic_title"));
        comic.setImgUrl(rowSet.getString("img_url"));
        comic.setDescription(rowSet.getString("description"));

        return comic;
    }


}
