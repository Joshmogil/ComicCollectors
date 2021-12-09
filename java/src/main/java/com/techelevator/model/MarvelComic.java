package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.web.JsonPath;

import java.util.List;
import java.util.Map;

public class MarvelComic {


      private Integer code;
      private String status;
//    private Long id;
//    private String title;
//    private String description;
//    private String thumbnail;
//    private String series;


    public MarvelComic() {
    }

    public MarvelComic(Integer code, String status) {
        this.code = code;
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //    public MarvelComic(String copyright, Map<String,String> data, Long id, String title, String series, String description, String thumbnail) {
//        this.copyright = copyright;
//        this.data = data;
//        this.id = id;
//        this.title = title;
//        this.series = series;
//        this.description = description;
//        this.thumbnail = thumbnail;
//    }


    @Override
    public String toString() {
        return "{" +
                "status=" + status +
                "code=" + code +
                '}';
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getSeries() {
//        return series;
//    }
//
//    public void setSeries(String series) {
//        this.series = series;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getThumbnail() {
//        return thumbnail;
//    }
//
//    public void setThumbnail(String thumbnail) {
//        this.thumbnail = thumbnail;
//    }
//
//    public Map<String, String> getData() {
//        return data;
//    }
//
//    public void setData(Map<String, String> data) {
//        this.data = data;
//    }

//    @Override
//    public String toString() {
//        return "{" +
//                "id=" + id +
//                "data=" + data +
//                "copyright=" + copyright +
//                "title=" + title +
//                "description=" + description +
//                "series=" + series +
//                "thumbnail=" + thumbnail +
//                '}';
//    }
//}


}

//
//    private String copyright;
//    private Data data;
//
//    public MarvelComic() {}
//
//    public MarvelComic(String copyright, Data data) {
//        this.copyright = copyright;
//        this.data = data;
//    }
//
//    public String getCopyright() {
//        return copyright;
//    }
//
//    public void setCopyright(String copyright) {
//        this.copyright = copyright;
//    }
//
//    public Data getData() {
//        return data;
//    }
//
//    public void setData(Data data) {
//        this.data = data;
//    }
//}
//
//class Data{
//
//    Data (){}
//
//    public Data(Results results) {
//        this.results = results;
//    }
//
//    private Results results;
//
//    public Results getResults() {
//        return results;
//    }
//
//    public void setResults(Results results) {
//        this.results = results;
//    }
//}
//
//class Results{
//
//    @JsonProperty("id")
//    private Integer id;
//    @JsonProperty("title")
//    private String title;
//    @JsonProperty("description")
//    private String description;
//
//    private Thumbnail thumbnail;
//
//    Results () {}
//
//    public Results(Integer id, String title, String description, Thumbnail thumbnail) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//        this.thumbnail = thumbnail;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public Thumbnail getThumbnail() {
//        return thumbnail;
//    }
//
//    public void setThumbnail(Thumbnail thumbnail) {
//        this.thumbnail = thumbnail;
//    }
//}
//
//class Thumbnail{
//
//    @JsonProperty("path")
//    private String path;
//    @JsonProperty("extension")
//    private String extension;
//
//    Thumbnail () {}
//
//    public Thumbnail(String path, String extension) {
//        this.path = path;
//        this.extension = extension;
//    }
//
//    public String getPath() {
//        return path;
//    }
//
//    public void setPath(String path) {
//        this.path = path;
//    }
//
//    public String getExtension() {
//        return extension;
//    }
//
//    public void setExtension(String extension) {
//        this.extension = extension;
//    }
//}







//    private String copyright;
//    private Map<String,String> data;
//    private Long id;
//    private String title;
//    private String description;
//    private String thumbnail;
//
//
//    public MarvelComic() {
//    }
//
//    public MarvelComic(String copyright, Map<String,String> data, Long id, String title, String series, String description, String thumbnail) {
//        this.copyright = copyright;
//        this.data = data;
//        this.id = id;
//        this.title = title;
//        this.series = series;
//        this.description = description;
//        this.thumbnail = thumbnail;
//    }
//
//    public String getCopyright() {
//        return copyright;
//    }
//
//    public void setCopyright(String copyright) {
//        this.copyright = copyright;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getSeries() {
//        return series;
//    }
//
//    public void setSeries(String series) {
//        this.series = series;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getThumbnail() {
//        return thumbnail;
//    }
//
//    public void setThumbnail(String thumbnail) {
//        this.thumbnail = thumbnail;
//    }
//
//    public Map<String, String> getData() {
//        return data;
//    }
//
//    public void setData(Map<String, String> data) {
//        this.data = data;
//    }

//    @Override
//    public String toString() {
//        return "{" +
//                "id=" + id +
//                "data=" + data +
//                "copyright=" + copyright +
//                "title=" + title +
//                "description=" + description +
//                "series=" + series +
//                "thumbnail=" + thumbnail +
//                '}';
//    }
//}
