package com.techelevator.model;

public class MarvelCharacter {

    private long characterId;
    private String characterName;
    private String img_url;
    private String extension;
    private String description;


    public MarvelCharacter(){}

    public MarvelCharacter(long characterId, String characterName, String img_url, String extension, String description) {
        this.characterId = characterId;
        this.characterName = characterName;
        this.img_url = img_url;
        this.extension = extension;
        this.description = description;
    }

    public long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(long characterId) {
        this.characterId = characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Character{" +
                "characterId=" + characterId +
                ", characterName='" + characterName + '\'' +
                ", img_url='" + img_url + '\'' +
                ", extension='" + extension + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
