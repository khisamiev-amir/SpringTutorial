package com.example.demo;

import com.example.demo.models.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
public class OrderControllerTets {
    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testOrderCreate() {
        UserDTO userDTO = new UserDTO(1L, new FullNameDTO("Имя", "Фамилия", "Отчество"));
        ProductDTO productDTO = new ProductDTO("Морковь", 10L, 10.00);
        UserProductDTO userProductDTO = new UserProductDTO(1L, new ArrayList<String>(Arrays.asList("Морковь")));
        restTemplate.postForObject("http://localhost:8080/userController/createUser", userDTO, UserDTO.class);
        restTemplate.postForObject("http://localhost:8080/productController/createProduct", productDTO, ProductDTO.class);
        OrderDTO orderDTO = restTemplate.postForObject("http://localhost:8080/orderController/createOrderById", userProductDTO, OrderDTO.class);
        Assertions.assertEquals(userDTO, orderDTO.getUser());
        Assertions.assertTrue(orderDTO.getProductsList().contains(productDTO));
    }
}
