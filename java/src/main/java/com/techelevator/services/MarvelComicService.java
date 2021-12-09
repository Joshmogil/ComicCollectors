package com.techelevator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.marvelmodel.MarvelComicData;
import com.techelevator.model.Comic;
import com.techelevator.model.MarvelComic;
import net.minidev.json.JSONObject;
import netscape.javascript.JSObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.io.DataInput;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
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

    public List<String> getComicInfo(long comicId){

        String comicJsonString = null;


        try{

            //API base URL = http://gateway.marvel.com/v1/public/comics?ts=
            List<String> marvelAuthInfo = generateAuthInfo();
            String exchangeUrl = API_BASE_URL + comicId +"?ts="+ marvelAuthInfo.get(0) + "&apikey="+marvelAuthInfo.get(2)+"&hash="+marvelAuthInfo.get(3);

            ResponseEntity<String> response =
                    restTemplate.exchange(exchangeUrl, HttpMethod.GET, makeHeaders(), String.class);
            comicJsonString = response.getBody();
            System.out.println("Response body: " +response.getBody());
            System.out.println("Response class: " + response.getBody().getClass());
            System.out.println("Response body to string: " + response.getBody().toString());

            //comic class needs an id, title, thumbnail

        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e);
        }
        //Returns a list of the id, title, url, extension

        List<String> comicInfoList = new ArrayList<String>();

        comicInfoList = rawStringToImportantInfo(comicJsonString);

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
            System.out.println("Response body: " +response.getBody());
            System.out.println("Response class: " + response.getBody().getClass());
            System.out.println("Response body to string: " + response.getBody().toString());

            //comic class needs an id, title, thumbnail

        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e);
        }

        return comicJsonString;

    }

    public List<String> rawStringToImportantInfo(String comicJsonString){

       List<String> comicInfoList = new ArrayList<String>();

       String id = findMyNumber("\"id\"",comicJsonString,1);
       comicInfoList.add(id);
       System.out.println(id);

       String title = findMyString("title",comicJsonString,3);
       comicInfoList.add(title);
       System.out.println(title);

       String path = findMyString("path",comicJsonString,3);
       comicInfoList.add(path);
       System.out.println(path);

       String extension = findMyString("extension",comicJsonString,3);
       comicInfoList.add(extension);
       System.out.println(extension);

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



    //Approach 1 to getting json from marvel API
    public JSONObject getComicJsonObj(long comicId){

        JSONObject marvelComicJsonObj = null;

        try{

            //API base URL = http://gateway.marvel.com/v1/public/comics/89541?ts=
            List<String> marvelAuthInfo = generateAuthInfo();
            String exchangeUrl = API_BASE_URL + comicId +"?ts="+ marvelAuthInfo.get(0) + "&apikey="+marvelAuthInfo.get(2)+"&hash="+marvelAuthInfo.get(3);

            System.out.println(exchangeUrl);

            ResponseEntity<JSONObject> response =
                    restTemplate.exchange(exchangeUrl, HttpMethod.GET, makeHeaders(), JSONObject.class);
            marvelComicJsonObj = response.getBody();
            System.out.println("Response body: " +response.getBody());
            System.out.println("Response class: " + response.getBody().getClass());
            System.out.println("Response body to string: " + response.getBody().toString());

            //comic class needs an id, title, thumbnail

        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e);
        }

        return marvelComicJsonObj;
    }

    //Approach to deserializing 1
    public MarvelComicData getComicData(long comicId){

        JSONObject marvelComicJsonObj = getComicJsonObj(comicId);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        MarvelComicData comicData = null;

        try{
            comicData = mapper.readValue(marvelComicJsonObj.toString(), MarvelComicData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(comicData.toString());
//        ///TESTING WITH JSON NODE!!!
//        ObjectMapper mapper2 =  new ObjectMapper();
//        try {
//            JsonNode node = mapper2.readValue(marvelComicJsonObj.toString(), JsonNode.class);
//            JsonNode copyrightNode = node.get("copyright");
//            String copyright = copyrightNode.asText();
//
//            JsonNode dataArrayNode = node.get("data");
//            String dataArray = dataArrayNode.asText();
//
//
//            JsonNode jsonNode = mapper2.readTree(marvelComicJsonObj.toString());
//            String copyright = jsonNode.get("copyright").asText();
//            String dataArray = jsonNode.get("data");
//            System.out.println(copyright);
//            System.out.println(dataArray);
//
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }


        /////

        return comicData;

    }

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

        System.out.println(listOfAuthInfo.toString());

        return listOfAuthInfo;

    }

}
