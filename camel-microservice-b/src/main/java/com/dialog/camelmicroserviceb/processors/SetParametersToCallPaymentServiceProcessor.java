package com.dialog.camelmicroserviceb.processors;

import com.dialog.camelmicroserviceb.dto.PaymentDetails;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class SetParametersToCallPaymentServiceProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        PaymentDetails paymentDetails = new PaymentDetails(4,300,"42342352352");
        exchange.getIn().setBody(paymentDetails);
    }
}
