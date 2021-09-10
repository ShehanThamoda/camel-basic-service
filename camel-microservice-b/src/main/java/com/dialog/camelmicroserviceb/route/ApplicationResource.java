package com.dialog.camelmicroserviceb.route;

import com.dialog.camelmicroserviceb.beans.PaymentValidationBean;
import com.dialog.camelmicroserviceb.dto.CustomerDetailsResponse;
import com.dialog.camelmicroserviceb.dto.OrderRequest;
import com.dialog.camelmicroserviceb.dto.PaymentDetailsResponse;
import com.dialog.camelmicroserviceb.processors.GetBodyOfInvokeReqProcessor;
import com.dialog.camelmicroserviceb.processors.OrderProcessor;
import com.dialog.camelmicroserviceb.processors.SetParametersToCallCustomerServiceProcessor;
import com.dialog.camelmicroserviceb.processors.SetParametersToCallPaymentServiceProcessor;
import com.dialog.camelmicroserviceb.service.OrderService;
import org.apache.camel.BeanInject;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ApplicationResource extends RouteBuilder {

    @Autowired
    private OrderService orderService;
    @BeanInject
    private OrderProcessor orderProcessor;
    @BeanInject
    private SetParametersToCallCustomerServiceProcessor setParametersToCallCustomerServiceProcessor;
    @BeanInject
    private SetParametersToCallPaymentServiceProcessor setParametersToCallPaymentServiceProcessor;
    @BeanInject
    private GetBodyOfInvokeReqProcessor getBodyOfInvokeReqProcessor;
    @Autowired
    private PaymentValidationBean paymentValidationBean;

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").port(8082).host("localhost").bindingMode(RestBindingMode.json);

        //sample for get methods how work
        rest().get("/hello-world").produces(MediaType.APPLICATION_JSON_VALUE).route().setBody(constant("Welcome"));

        /**
         * @des GET API implement for get order details.
         */
        rest().get("/getOrders").produces(MediaType.APPLICATION_JSON_VALUE)
                .route().setBody(()->orderService.getOrderList())
                .endRest();

        /**
         * @des POST API implement in here.Initial order details set to the
         * Object and direct to endPoint1.
         */
        rest().post("/addOrder").consumes(MediaType.APPLICATION_JSON_VALUE)
                .bindingMode(RestBindingMode.json)
                .produces("application/json")
                .type(OrderRequest.class)
                .to("direct:endPoint1");
//                .outType(Order.class)
//                .route().process(orderProcessor)
//                .endRest();
        //Set all the data for call getCustomer API
        from("direct:endPoint1")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .process(getBodyOfInvokeReqProcessor)
                .process(setParametersToCallCustomerServiceProcessor)
                .marshal()
                .json(JsonLibrary.Jackson)
                .log("${messageHistory}")
                .multicast()
                .to("direct:endpoint2");
        //Call getCustomer API and get the customer details. After save the Order record
        from("direct:endpoint2")
                .toD("http://localhost:8080/getCustomer/${header.customer_id}?bridgeEndpoint=true")
                .unmarshal()
                .json(JsonLibrary.Jackson, CustomerDetailsResponse.class)
                .process(orderProcessor)
                .log("form-endpoint2:${body}")
                .to("direct:endpoint3");
        //Set all the data for call payment API
        from("direct:endpoint3")
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .process(setParametersToCallPaymentServiceProcessor)
                .marshal()
                .json(JsonLibrary.Jackson)
                .log("${messageHistory}")
                .multicast()
                .to("direct:endpoint4");
        //Call payment API and get payment whether valid or not.
        from("direct:endpoint4")
                .toD("http://localhost:8080/payments?bridgeEndpoint=true")
                .unmarshal()
                .json(JsonLibrary.Jackson, PaymentDetailsResponse.class)
                .bean(paymentValidationBean)
                .log("${body}")
                .to("log:endpoint4");
    }
}

