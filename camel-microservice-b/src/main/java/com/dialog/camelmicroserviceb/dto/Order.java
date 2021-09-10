package com.dialog.camelmicroserviceb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {

    private int id;
    private String name;
    private double price;
    private String customerId;
    private String customeName;
    private String customerMobile;

    public Order(int id, String name, double price, String customerId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.customerId = customerId;
    }
}
