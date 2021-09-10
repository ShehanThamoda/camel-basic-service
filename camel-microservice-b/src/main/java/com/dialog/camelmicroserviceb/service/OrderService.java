package com.dialog.camelmicroserviceb.service;

import com.dialog.camelmicroserviceb.dto.CustomerDetailsResponse;
import com.dialog.camelmicroserviceb.dto.Order;
import com.dialog.camelmicroserviceb.dto.OrderRequest;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private static List<Order> orderList = new ArrayList<>();
    Order order;

    @PostConstruct
    public void initDB(){
        orderList.add(new Order(1,"GSM",200,"C001","Shehan","0772222222"));
        orderList.add(new Order(2,"DialogTv",3000,"C002","Kasun","0773333333"));
        orderList.add(new Order(3,"Woms",300,"C003","Amila","0765588990"));
    }

    public Order addOrderInitially(OrderRequest orderRequest){
        Order order = new Order(orderRequest.getId(),orderRequest.getName(),orderRequest.getPrice(),
                    orderRequest.getCustomerId());
        this.order=order;
        return  order;
    }

    public void addCustomerDetails(CustomerDetailsResponse customerDetailsResponse){
            System.out.println(">>>>>>>>>>>>>"+order.getCustomerId()+">>>>"+customerDetailsResponse.getCustomerId());
            if(order.getCustomerId().equals(customerDetailsResponse.getCustomerId())){
                order.setCustomeName(customerDetailsResponse.getCustomeName());
                order.setCustomerMobile(customerDetailsResponse.getCustomerMobile());
                orderList.add(order);
            }
    }

    public List<Order> getOrderList(){
        return orderList;
    }

}
