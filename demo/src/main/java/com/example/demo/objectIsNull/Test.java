package com.example.demo.objectIsNull;

import java.util.ArrayList;
import java.util.List;

/**
 * java属于值传递
 */
public class Test {

    public static void main(String[] args) {
        OrderModel orderModel = new OrderModel();
        List<Order> list = new ArrayList<>();
        orderModel.setOrderList(list);

        Response<OrderModel> response = new Response<>();
        response.setResult(orderModel);


        addModer(response);
        System.out.println(response);
    }

    private static void addModer(Response<OrderModel> response){
        Response<OrderModel> response1 = new Response<>();
        OrderModel orderModel = new OrderModel();
        List<Order> list = new ArrayList<>();

        response1.setResult(orderModel);
        orderModel.setTotalCount(20);
        orderModel.setOrderList(list);
        list.add(new Order(1));
        list.add(new Order(2));


        response = response1;

        System.out.println("addModer==========>"+ response1);
    }
}
