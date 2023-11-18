package com.example.demo.controllers;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/variable")

public class VariableController {
    public static String myVariables = "Initial Value";

    @GetMapping(value = "/myVariable", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, String> getVariableValue() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("value", myVariables);
        return hashMap;
    }

    @PutMapping(value = "/changeMyVariable", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, String> changeVariableValue(@RequestBody String a) {
        myVariables = a;
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("value", a);
        return hashMap;
//        для понимания
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("value", myVariables);
//        hashMap.put("value", a);
//        myVariables = a;
//        return hashMap;
    }

    @DeleteMapping(value = "/myVariable", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, String> deleteVariableValue() {
        myVariables = "";
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("value", myVariables);
        return hashMap;
    }

//    @DeleteMapping(value = "/myVariable", produces = MediaType.APPLICATION_JSON_VALUE)
//    public void deleteVariableValue() {
//        myVariables = "";
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("value", myVariables);
//
//    }

}
