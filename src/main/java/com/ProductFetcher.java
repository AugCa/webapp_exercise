package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ProductFetcher{

    public List<Product> getProducts(){
        DBConnection dbconnection = new DBConnection();
        Connection con = dbconnection.productConnection();
        String query = "select * from product_info";

        List<Product> products = new ArrayList<>();

        try{
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setPrice(rs.getInt("product price"));
                product.setProductDescription(rs.getString("product description"));
                product.setStock(rs.getInt("quantity stock"));
                product.setProductImage(rs.getString("image"));
                products.add(product);


            }
        } catch(Exception e){
            System.out.println(e);
        }
        return products;

    }



}


