package com.techelevator;

import com.techelevator.services.MarvelComicService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
    }

    /*@Test
    void getComicRetrievesComic(){
        String API_BASE_URL = "http://gateway.marvel.com/v1/public/comics?ts=";
        String privateKey = "197ef818f572516a2966e997ee7268e0cd590e21";
        String publicKey = "20afbe7ebe8ad8af2c91b02a275e06cc";


        MarvelComicService comicService = new MarvelComicService(API_BASE_URL,privateKey,publicKey);

        comicService.getComic(89541);
    }*/

}
