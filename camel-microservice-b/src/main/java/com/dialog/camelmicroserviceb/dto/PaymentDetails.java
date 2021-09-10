package com.dialog.camelmicroserviceb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentDetails {

    private int orderId;
    private double amount;
    private String cardNumber;

}
