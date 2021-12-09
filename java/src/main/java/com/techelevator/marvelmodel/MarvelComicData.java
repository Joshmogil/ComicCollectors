package com.techelevator.marvelmodel;

public class MarvelComicData {

    private MarvelComicResults results;

    public MarvelComicData() {
    }

    public MarvelComicResults getResults() {
        return results;
    }

    public void setResults(MarvelComicResults results) {
        this.results = results;
    }

    public String toString() {
        return "{" +
                "status=" + //+
                "code=" + //code +
                '}';
    }
}
