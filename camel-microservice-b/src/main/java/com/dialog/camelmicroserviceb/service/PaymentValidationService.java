package com.dialog.camelmicroserviceb.service;

import com.dialog.camelmicroserviceb.dto.PaymentDetailsResponse;
import org.springframework.stereotype.Service;

@Service
public class PaymentValidationService {

     public String validateThePaymentResponse(PaymentDetailsResponse response){
         String result = null;
         if(response.getPaymentStatus().equals("SUCCESS")){
             result = "Payment Success";
         }else{
             result = "Payment Fail";
         }
         return  result;
     }


}
