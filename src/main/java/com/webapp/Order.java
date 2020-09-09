package com.webapp;
import com.OrderItem;



import java.util.HashMap;

public class Order {

    private HashMap<Integer, OrderItem> orderItems;

    public Order(HashMap<Integer, OrderItem> orderItems){
        this.orderItems = orderItems;
    }

    public HashMap<Integer, OrderItem> getOrderItems(){
        return orderItems;
    }
}
