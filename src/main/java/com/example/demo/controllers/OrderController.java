package com.example.demo.controllers;

import com.example.demo.models.OrderDTO;
import com.example.demo.models.ProductDTO;
import com.example.demo.models.UserDTO;
import com.example.demo.models.UserProductDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor

public class OrderController {

    public List<OrderDTO> orderDTOList = new ArrayList<>();

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping(value = "orderController/getOrders", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDTO> getOrders() {
        return orderDTOList;

    }


    @PostMapping(value = "orderController/createOrder", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
        orderDTOList.add(orderDTO);
        return orderDTO;

    }

    @PostMapping(value = "orderController/createOrderById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> createOrderById(@RequestBody UserProductDTO userProductDTO) {
        UserDTO response = restTemplate.getForObject("http://localhost:8080/userController/getUser/" + userProductDTO.getUserId(), UserDTO.class);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }else {
            List<ProductDTO> productDTOList = new ArrayList<>();
            for (int i = 0; i < userProductDTO.getProductsNameList().size(); i++) {
                ProductDTO product = restTemplate.getForObject("http://localhost:8080/productController/getProduct/" + userProductDTO.getProductsNameList().get(i), ProductDTO.class);
                if(product !=null){
                    productDTOList.add(product);
                }
            }
            OrderDTO order = new OrderDTO(response,productDTOList);
            return ResponseEntity.ok(order);




        }




    }
}
