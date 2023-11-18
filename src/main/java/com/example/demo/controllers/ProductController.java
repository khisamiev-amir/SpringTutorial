package com.example.demo.controllers;

import com.example.demo.models.ProductDTO;
import com.example.demo.models.UserDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@AllArgsConstructor
@NoArgsConstructor

public class ProductController {
    public List<ProductDTO> productDTOList = new ArrayList<>();


    @PostMapping(value = "productController/createProduct", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        productDTOList.add(productDTO);
        return productDTO;

    }

    @GetMapping(value = "productController/getProducts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDTO> getProducts() {
        return productDTOList; // кажется, что здесь надо понять, как вернуть весь лист, но это умеет spring.

    }

    @GetMapping(value = "productController/getProduct/{productName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTO getProduct(@PathVariable String productName) {
        Optional<ProductDTO> userOptional = productDTOList.stream()
                .filter(product -> productName.equals(product.getName()))
                .findFirst();
        return userOptional.orElse(null); // кажется, что здесь надо понять, как вернуть весь лист, но это умеет spring.
    }



}
