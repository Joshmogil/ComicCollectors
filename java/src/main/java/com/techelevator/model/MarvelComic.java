package com.techelevator.model;

public class MarvelComic {

    private String copyright;

    public MarvelComic() {
    }

    public MarvelComic(String copyright) {
        this.copyright = copyright;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @Override
    public String toString() {
        return "{" +
                "copyright=" + copyright +
                '}';
    }
}
