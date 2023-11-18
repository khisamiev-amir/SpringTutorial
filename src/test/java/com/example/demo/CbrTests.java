package com.example.demo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@SpringBootTest
public class CbrTests {
    RestTemplate restTemplate = new RestTemplate();


    @Test
    public void testOrderCreate() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://www.cbr-xml-daily.ru/daily_json.js", String.class);
        String value = responseEntity.getBody();
        Assertions.assertTrue(value.contains("Таджикских сомони"));

    }

    @Test
    public void test1() {
        RestTemplate restTemplate = new RestTemplate();
        String URL = "https://reqres.in/api/users?page=2";

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL, String.class);

        HttpStatusCode httpStatusCode = HttpStatus.OK;
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        Assertions.assertEquals(httpStatusCode, statusCode);

        HttpHeaders headers = responseEntity.getHeaders();

        String contentType = headers.getContentType().toString();

        Assertions.assertEquals(contentType, "application/json;charset=utf-8", "contentType!=application/json;charset=utf-8");


//        MediaType contentTypeMediaType = responseEntity.getHeaders().getContentType();
//        Assertions.assertNotNull(contentTypeMediaType, "Content-Type is null");
//        Assertions.assertTrue(contentTypeMediaType.includes(MediaType.APPLICATION_JSON), "Content type is not application/json");


//        Assertions.assertTrue(responseEntity.getBody().contains("\"page\":"));

        JSONObject jsonObject = new JSONObject(responseEntity.getBody());
        Assertions.assertTrue(jsonObject.has("page"));
        JSONArray dataArray = jsonObject.getJSONArray("data");
        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject jsonObjectFromArray = ((JSONObject) dataArray.get(i));
            Assertions.assertTrue(jsonObjectFromArray.has("id") && jsonObjectFromArray.has("email") && jsonObjectFromArray.has("first_name") && jsonObjectFromArray.has("last_name") && jsonObjectFromArray.has("avatar"));
        }

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject jsonObjectFromArray = ((JSONObject) dataArray.get(i));
            String email = jsonObjectFromArray.getString("email");
            Assertions.assertTrue(email.contains("@") && email.contains("."));
            // Поиск символа '@'
            int atIndex = email.indexOf('@');

            // Поиск символа '.'
            int dotIndex = email.indexOf('.');
            Assertions.assertTrue(dotIndex - atIndex > 1 && atIndex != 0 && dotIndex != email.length() - 1);
        }


    }

    @Test
    public void doubleValue() {
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            arrayList.add(i);
        }
        arrayList.add(55);

        Collections.sort(arrayList);
        System.out.println(arrayList.toString());
        for (int i = 0; i < arrayList.size()-1; i++) {

            if (Objects.equals(arrayList.get(i), arrayList.get(i + 1))) {
                System.out.println(arrayList.get(i));

            }
        }
        System.out.println("Done!");
    }


}

