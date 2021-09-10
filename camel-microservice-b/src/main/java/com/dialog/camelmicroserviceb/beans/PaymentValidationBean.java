package com.dialog.camelmicroserviceb.beans;

import com.dialog.camelmicroserviceb.dto.PaymentDetailsResponse;
import com.dialog.camelmicroserviceb.service.PaymentValidationService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentValidationBean {

    @Autowired
    PaymentValidationService paymentValidationService;

    //transformation
    public String validate(Exchange exchange) throws Exception {
        PaymentDetailsResponse body = (PaymentDetailsResponse) exchange.getIn().getBody();
        return paymentValidationService.validateThePaymentResponse(body);
    }
}
