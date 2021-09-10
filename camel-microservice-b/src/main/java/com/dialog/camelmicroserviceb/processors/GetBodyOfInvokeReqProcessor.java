package com.dialog.camelmicroserviceb.processors;

import com.dialog.camelmicroserviceb.dto.OrderRequest;
import com.dialog.camelmicroserviceb.service.OrderService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetBodyOfInvokeReqProcessor implements Processor {

    @Autowired
    OrderService orderService;

    @Override
    public void process(Exchange exchange) throws Exception {
        OrderRequest body =(OrderRequest) exchange.getIn().getBody();
        System.out.println("Body of Invoke Request " + body);
        orderService.addOrderInitially(body);
    }
}
