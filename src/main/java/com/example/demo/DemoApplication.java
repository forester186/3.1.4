package com.example.demo;

import com.example.demo.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class DemoApplication {

    private static final String URL_GET_USERS = "http://91.241.64.178:7081/api/users";
    private static final String URL_POST_USER = "http://91.241.64.178:7081/api/users";
    private static final String URL_PUT_USERS = "http://91.241.64.178:7081/api/users";
    private static final String URL_DELETE_USER = "http://91.241.64.178:7081/api/users/3";

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        RestTemplate restTemplate = new RestTemplate();

        StringBuilder stringBuilder = new StringBuilder();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity <> (headers);
        ResponseEntity< String > result = restTemplate.exchange(URL_GET_USERS, HttpMethod.GET, entity, String.class);

        String cookie = result.getHeaders().get(HttpHeaders.SET_COOKIE).get(0);


        User user = new User(3L, "James", "Brown",(byte) 22);

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add(HttpHeaders.COOKIE, cookie);
        HttpEntity<User> entityPost = new HttpEntity <> (user,headers);
        ResponseEntity< String > resultPost = restTemplate.exchange(URL_POST_USER, HttpMethod.POST, entityPost, String.class);
        stringBuilder.append(resultPost.getBody());

        user.setName("Thomas");
        user.setLastName("Shelby");


        HttpEntity<User> entityPut = new HttpEntity <> (user,headers);
        ResponseEntity< String > resultPut = restTemplate.exchange(URL_PUT_USERS, HttpMethod.PUT, entityPut, String.class);
        stringBuilder.append(resultPut.getBody());


        HttpEntity<User> entityDelete = new HttpEntity <> (user,headers);
        ResponseEntity< String > resultDelete = restTemplate.exchange(URL_DELETE_USER, HttpMethod.DELETE, entityDelete, String.class);
        stringBuilder.append(resultDelete.getBody());

        System.out.println(stringBuilder);










    }



}
