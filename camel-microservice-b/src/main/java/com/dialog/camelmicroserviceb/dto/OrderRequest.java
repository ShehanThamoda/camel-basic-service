package com.dialog.camelmicroserviceb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderRequest {

    private int id;
    private String name;
    private double price;
    private String customerId;
 }
