package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserProductDTO {
    private Long userId;
    private List<String> productsNameList;
}
