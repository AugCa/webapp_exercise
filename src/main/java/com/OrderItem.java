package com;

public class OrderItem extends Product{

    private int amount;

    public OrderItem(){
        super();
    }



    public int getAmount(){
        return amount;
    }


    public int getCost(){
        return getPrice() * amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "product=" +
                ", amount=" + amount +
                '}';
    }
}
