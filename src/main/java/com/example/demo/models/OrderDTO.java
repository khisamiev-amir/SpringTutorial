package com.example.demo.models;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class OrderDTO {
    private UserDTO user;
    private List<ProductDTO> productsList;
    private UUID orderNumber;
    private Double totalPrice;

    @JsonCreator
    public OrderDTO(@JsonProperty("user") UserDTO user,
                    @JsonProperty("productsList") List<ProductDTO> productsList) {
        this.user = user;
        this.productsList = productsList;
        this.orderNumber = UUID.randomUUID();
        this.totalPrice = this.totalPriceSum();
    }

    private Double totalPriceSum() {


        return productsList.stream().mapToDouble(ProductDTO::getPrice).sum();


    }
}
