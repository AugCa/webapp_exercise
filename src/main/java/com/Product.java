package com;

public class Product {
    private int id;
    private String productDescription;
    private int price;
    private int stock;
    private String productImage;

    public Product() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductImage() {
        return productImage;
    }

    public int getId() {
        return id;
    }

    public String getIdAsString(){
        return Integer.toString(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productDescription='" + productDescription + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", productImage='" + productImage + '\'' +
                '}';
    }
}
