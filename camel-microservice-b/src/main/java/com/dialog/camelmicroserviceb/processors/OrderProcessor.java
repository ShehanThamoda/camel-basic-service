package com.dialog.camelmicroserviceb.processors;

import com.dialog.camelmicroserviceb.dto.CustomerDetailsResponse;
import com.dialog.camelmicroserviceb.service.OrderService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessor implements Processor {

    @Autowired
    private OrderService orderService;

    @Override
    public void process(Exchange exchange) throws Exception {
        CustomerDetailsResponse body = (CustomerDetailsResponse) exchange.getIn().getBody();
        orderService.addCustomerDetails(body);
    }
}
