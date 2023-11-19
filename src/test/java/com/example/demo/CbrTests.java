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
        for (int i = 0; i < arrayList.size() - 1; i++) {

            if (Objects.equals(arrayList.get(i), arrayList.get(i + 1))) {
                System.out.println(arrayList.get(i));

            }
        }
        System.out.println("Done!");
        System.out.println("Картошка");
        System.out.println("Морковь");
    }

    @Test
    public void SortListInteger() {
        List<Integer> list = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5);
        ArrayList<Integer> arrayList = new ArrayList<>(list);
        ArrayList<Integer> evenArrayList = new ArrayList<>();
        ArrayList<Integer> oddArrayList = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {

            if (arrayList.get(i) % 2 == 0) {
                oddArrayList.add(arrayList.get(i));
            } else {
                evenArrayList.add(arrayList.get(i));
            }
        }

        ArrayList<Integer> resultArrayList = new ArrayList<>();
        resultArrayList.addAll(oddArrayList);
        resultArrayList.addAll(evenArrayList);
        System.out.println(resultArrayList);
    }

    @Test
    public void lengthOfLastWord() {
        String s = "Отправь меня на Луну";
        int sLength = 0;
        for (int i = s.length() - 1; i > 0; i--) {
            if (s.charAt(i) != ' ') {
                sLength++;
            } else if (s.charAt(i) == ' ') {
                break;

            }

        }
        System.out.println(s.substring(s.length() - sLength, s.length()));
    }

    @Test
    public void bubbleSort() {
        List<Integer> list = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5);
        for (int j = 0; j < list.size(); j++) {


        for (int i = 0; i < list.size()-1; i++) {
            if (list.get(i)> list.get(i+1)){
                Integer integer = list.get(i);
                list.set(i, list.get(i+1));
                list.set(i+1,integer);
            }
        }
        }
        System.out.println(list);
    }

    @Test
    public void searchValue(){
        RestTemplate restTemplate = new RestTemplate();  //ВСЕГДА СОЗДАЕМ, ПРОСТО ЗАПОМНИ
        String URL = "https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m"; //ВСЕГДА СОЗДАЕМ, ПРОСТО ЗАПОМНИ
        ResponseEntity <String> responseEntity = restTemplate.getForEntity(URL, String.class); //ВСЕГДА ResponseEntity, всегда <String> , всгеда внутри вот так (URL, String.class)
        HttpStatusCode httpStatusCode = HttpStatus.OK; //ВСЕГДА СОЗДАЕМ, ПРОСТО ЗАПОМНИ
        HttpStatusCode statusCode = responseEntity.getStatusCode(); //ВСЕГДА СОЗДАЕМ, ПРОСТО ЗАПОМНИ
        Assertions.assertEquals(httpStatusCode, statusCode, "Статус код не равен 200ОК"); //ВСЕГДА СОЗДАЕМ, ПРОСТО ЗАПОМНИ

        JSONObject jsonObject = new JSONObject(responseEntity.getBody());
        JSONArray jsonArray = jsonObject.getJSONObject("hourly").getJSONArray("temperature_2m");
        List<Double> doubleList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            doubleList.add(jsonArray.getDouble(i));
        }
        Assertions.assertTrue(doubleList.contains(3.8), "Массив не содержит значение 3.8");
    }
}

