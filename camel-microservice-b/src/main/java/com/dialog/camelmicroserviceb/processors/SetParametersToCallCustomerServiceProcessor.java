package com.dialog.camelmicroserviceb.processors;

import com.dialog.camelmicroserviceb.dto.OrderRequest;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class SetParametersToCallCustomerServiceProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        OrderRequest body =(OrderRequest) exchange.getIn().getBody();
        exchange.getIn().setHeader("customer_id",body.getCustomerId());
    }
}
