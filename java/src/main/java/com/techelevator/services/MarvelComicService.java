package com.techelevator.services;


import com.techelevator.model.MarvelCharacter;
import com.techelevator.model.MarvelComic;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MarvelComicService {

    private String API_BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();
    //Need to see what else the service needs to connect to the Marvel API
    //From https://developer.marvel.com/documentation/authorization
    //Server-side applications must pass two parameters in addition to the apikey parameter:
    //ts- a timestamp (or other long string which can change on a request-by-request basis)
    //hash - a md5 digest of the ts parameter, your private key and your public key (e.g. md5(ts+privateKey+publicKey)
    //ex) http://gateway.marvel.com/v1/public/comics?ts=1&apikey=1234&hash=ffd275c5130566a2916217b101f26150 (the hash value is the md5 digest of 1abcd1234)
    private String privateKey;
    private String publicKey;
    private Long timeStamp;

    // Hulk comic 27649
    //grabs a comic by comic id ex) a thor comic : 89541
    public MarvelComicService(String API_BASE_URL, String privateKey,String publicKey){
        this.API_BASE_URL = API_BASE_URL;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.timeStamp = (long)1;
    }

    public List<MarvelCharacter> getCharacterListByComicId(long marvelComicId){

        List<MarvelCharacter> listOfCharacters = new ArrayList<MarvelCharacter>();
        String listOfCharactersJsonString = null;

        try{

            //API base URL = http://gateway.marvel.com/v1/public/
            List<String> marvelAuthInfo = generateAuthInfo();

            String exchangeUrl = API_BASE_URL + "comics/"+marvelComicId +"/characters?ts="+ marvelAuthInfo.get(0) + "&apikey="+marvelAuthInfo.get(2)+"&hash="+marvelAuthInfo.get(3);

            ResponseEntity<String> response =
                    restTemplate.exchange(exchangeUrl, HttpMethod.GET, makeHeaders(), String.class);
            listOfCharactersJsonString = response.getBody();
//

        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e);
        }

        while(listOfCharactersJsonString.contains("\"urls\"")) {

            MarvelCharacter character = new MarvelCharacter();

            List<String> comicInfoList = new ArrayList<String>();

            comicInfoList = rawStringToImportantCharacterInfo(listOfCharactersJsonString);

            character.setCharacterId(Long.valueOf(comicInfoList.get(0)));
            character.setCharacterName(comicInfoList.get(1));
            character.setDescription(comicInfoList.get(2));
            character.setImg_url(comicInfoList.get(3));
            character.setExtension(comicInfoList.get(4));

            int endOfCharacterCutoff = listOfCharactersJsonString.indexOf("\"urls\"") + 3;

            listOfCharactersJsonString = listOfCharactersJsonString.substring(endOfCharacterCutoff);

            listOfCharacters.add(character);

        }

        return listOfCharacters;
    }

    public List<MarvelComic> getComicListByCharacterName(String characterName){

        List<MarvelComic> listOfComics = new ArrayList<MarvelComic>();
        long characterId = getCharacterIdByName(characterName);
        String listOfComicsJsonString = null;

        try{

            //API base URL = http://gateway.marvel.com/v1/public/
            List<String> marvelAuthInfo = generateAuthInfo();

            String exchangeUrl = API_BASE_URL + "characters/"+characterId +"/comics?ts="+ marvelAuthInfo.get(0) + "&apikey="+marvelAuthInfo.get(2)+"&hash="+marvelAuthInfo.get(3);

            ResponseEntity<String> response =
                    restTemplate.exchange(exchangeUrl, HttpMethod.GET, makeHeaders(), String.class);
            listOfComicsJsonString = response.getBody();


        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e);
        }

        while(listOfComicsJsonString.contains("\"events\"")) {

            MarvelComic marvelComic = new MarvelComic();

            List<String> comicInfoList = new ArrayList<String>();

            comicInfoList = rawStringToImportantComicInfo(listOfComicsJsonString);

            marvelComic.setMarvelId(Long.valueOf(comicInfoList.get(0)));
            marvelComic.setTitle(comicInfoList.get(1));
            marvelComic.setDescription(comicInfoList.get(2));
            marvelComic.setImgUrl(comicInfoList.get(3));
            marvelComic.setExtension(comicInfoList.get(4));

            String useableImgUrl = marvelComic.getImgUrl() + "/portrait_uncanny." + marvelComic.getExtension();
            marvelComic.setImgUrl(useableImgUrl);

            int endOfComicCutoff = listOfComicsJsonString.indexOf("\"events\"") + 3;

            listOfComicsJsonString = listOfComicsJsonString.substring(endOfComicCutoff);

            listOfComics.add(marvelComic);

        }

        return listOfComics;
    }

    public long getCharacterIdByName(String characterName){

        String characterJsonString = null;
        long characterId = 0;

        try{

            //API base URL = http://gateway.marvel.com/v1/public/
            List<String> marvelAuthInfo = generateAuthInfo();

            //https://gateway.marvel.com:443/v1/public/characters?name=thor&apikey=20afbe7ebe8ad8af2c91b02a275e06cc

            String exchangeUrl = API_BASE_URL + "characters?name="+characterName +"&ts="+ marvelAuthInfo.get(0) + "&apikey="+marvelAuthInfo.get(2)+"&hash="+marvelAuthInfo.get(3);

            ResponseEntity<String> response =
                    restTemplate.exchange(exchangeUrl, HttpMethod.GET, makeHeaders(), String.class);
            characterJsonString = response.getBody();


        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e);
        }

        characterId = Long.valueOf(findMyNumber("\"id\"",characterJsonString,1));

        return characterId;
    }

    public MarvelComic getComic(long comicId){

        String comicJsonString = null;
        MarvelComic marvelComic = new MarvelComic();

        try{

            //API base URL = http://gateway.marvel.com/v1/public/comics?ts=
            List<String> marvelAuthInfo = generateAuthInfo();
            String exchangeUrl = API_BASE_URL +"comics/" + comicId +"?ts="+ marvelAuthInfo.get(0) + "&apikey="+marvelAuthInfo.get(2)+"&hash="+marvelAuthInfo.get(3);
            System.out.println(exchangeUrl);

            ResponseEntity<String> response =
                    restTemplate.exchange(exchangeUrl, HttpMethod.GET, makeHeaders(), String.class);
            comicJsonString = response.getBody();

        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println("Error getting comics by character name");
        }
        //Returns a list of the id, title, url, extension

        List<String> comicInfoList = new ArrayList<String>();

        comicInfoList = rawStringToImportantComicInfo(comicJsonString);

        marvelComic.setMarvelId(Long.valueOf(comicInfoList.get(0)));
        marvelComic.setTitle(comicInfoList.get(1));
        marvelComic.setDescription(comicInfoList.get(2));
        marvelComic.setImgUrl(comicInfoList.get(3));
        marvelComic.setExtension(comicInfoList.get(4));

        return marvelComic;
    }

    public List<String> getComicInfo(long comicId){

        String comicJsonString = null;


        try{

            //API base URL = http://gateway.marvel.com/v1/public/comics?ts=
            List<String> marvelAuthInfo = generateAuthInfo();
            String exchangeUrl = API_BASE_URL + comicId +"?ts="+ marvelAuthInfo.get(0) + "&apikey="+marvelAuthInfo.get(2)+"&hash="+marvelAuthInfo.get(3);

            ResponseEntity<String> response =
                    restTemplate.exchange(exchangeUrl, HttpMethod.GET, makeHeaders(), String.class);
            comicJsonString = response.getBody();

            //comic class needs an id, title, thumbnail

        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println("error getting comics by character name");
        }
        //Returns a list of the id, title, url, extension

        List<String> comicInfoList = new ArrayList<String>();

        comicInfoList = rawStringToImportantComicInfo(comicJsonString);

        return comicInfoList;
    }

    public String getComicString(long comicId){

        String comicJsonString = null;

        try{

            //API base URL = http://gateway.marvel.com/v1/public/comics?ts=
            List<String> marvelAuthInfo = generateAuthInfo();
            String exchangeUrl = API_BASE_URL + comicId +"?ts="+ marvelAuthInfo.get(0) + "&apikey="+marvelAuthInfo.get(2)+"&hash="+marvelAuthInfo.get(3);

            ResponseEntity<String> response =
                    restTemplate.exchange(exchangeUrl, HttpMethod.GET, makeHeaders(), String.class);
            comicJsonString = response.getBody();

            //comic class needs an id, title, thumbnail

        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e);
        }

        return comicJsonString;

    }

    public List<String> rawStringToImportantCharacterInfo(String comicJsonString){

        List<String> characterInfoList = new ArrayList<String>();

        String id = findMyNumber("\"id\"",comicJsonString,1);
        characterInfoList.add(id);

        String title = findMyString("\"name\"",comicJsonString,2);
        characterInfoList.add(title);

        String description = findMyString("\"description\"",comicJsonString,2);
        characterInfoList.add(description);

        String path = findMyString("\"path\"",comicJsonString,2);
        characterInfoList.add(path);

        String extension = findMyString("\"extension\"",comicJsonString,2);
        characterInfoList.add(extension);

        return characterInfoList;
    }

    public List<String> rawStringToImportantComicInfo(String comicJsonString){

       List<String> comicInfoList = new ArrayList<String>();

       String id = findMyNumber("\"id\"",comicJsonString,1);
       comicInfoList.add(id);

       String title = findMyString("\"title\"",comicJsonString,2);
       comicInfoList.add(title);

       String description = findMyString("\"description\"",comicJsonString,2);
       comicInfoList.add(description);

       String path = findMyString("\"path\"",comicJsonString,2);
       comicInfoList.add(path);

       String extension = findMyString("\"extension\"",comicJsonString,2);
       comicInfoList.add(extension);

       return comicInfoList;
    }

    public String findMyString(String key, String comicJsonString , Integer offset){

        int indexOfKey = comicJsonString.indexOf(key);

        int beginIndexOfValue = indexOfKey +key.length() + offset;

        String[] subSplit = comicJsonString.substring(beginIndexOfValue).split("\"");

        String isolatedValue = subSplit[0];

        return isolatedValue;

    }

    public String findMyNumber(String key, String comicJsonString, Integer offset){

        int indexOfKey = comicJsonString.indexOf(key);

        int beginIndexOfValue = indexOfKey +key.length() + offset;

        String[] subSplit = comicJsonString.substring(beginIndexOfValue).split(",");

        String isolatedValue = subSplit[0];

        return isolatedValue;

    }



    //Approach 1 to getting json from marvel API -- NOT USEFUL
    public JSONObject getComicJsonObj(long comicId){

        JSONObject marvelComicJsonObj = null;

        try{

            //API base URL = http://gateway.marvel.com/v1/public/comics/89541?ts=
            List<String> marvelAuthInfo = generateAuthInfo();
            String exchangeUrl = API_BASE_URL + comicId +"?ts="+ marvelAuthInfo.get(0) + "&apikey="+marvelAuthInfo.get(2)+"&hash="+marvelAuthInfo.get(3);


            ResponseEntity<JSONObject> response =
                    restTemplate.exchange(exchangeUrl, HttpMethod.GET, makeHeaders(), JSONObject.class);
            marvelComicJsonObj = response.getBody();

            //comic class needs an id, title, thumbnail

        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e);
        }

        return marvelComicJsonObj;
    }

    //Approach to deserializing 1 -- NOT USEFUL

    private HttpEntity<Void> makeHeaders() {
        HttpHeaders headers = new HttpHeaders();
        //headers.setBearerAuth(currentUser.getToken());
        return new HttpEntity<>(headers);
    }

    //This function needs to be called to get the timestamp, private key, public key, and hash of (ts + private key + public key)
    //These items are required to make a call to the marvel API from a backend server
    //They are returned in a List size 4 where element order is [0] = timestamp, [1] = private key, [2] = public key, [3] = hash of all three
    private List<String> generateAuthInfo(){

        List<String> listOfAuthInfo=new ArrayList<String>();
        timeStamp += 1; //global timestamp auto incremented each time function is called, Ie. a request to marvel api is made
        listOfAuthInfo.add(String.valueOf(timeStamp));
        listOfAuthInfo.add(privateKey);
        listOfAuthInfo.add(publicKey);

        //Generate md5 hash
        try {
            String hashString = timeStamp+privateKey+publicKey;

            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(hashString.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            listOfAuthInfo.add(hashtext);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("generateAuthInfo in MarvelComicService failed at generating hash");
        }


        return listOfAuthInfo;

    }

}
