package com.webapp;

import com.DBConnection;
import com.OrderItem;
import com.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "/SummaryPage")
public class SummaryPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Order order = (Order) request.getSession().getAttribute("order");
        //request.getSession().setAttribute("order" , order);
        boolean error = (boolean) request.getSession().getAttribute("error");

        if(!error){
                if(order != null){
                    HashMap<Integer, OrderItem> orderItems = order.getOrderItems();
                    DBConnection dbconnection = new DBConnection();
                    Connection con = dbconnection.productConnection();
                    for (Map.Entry me : orderItems.entrySet()) {
                        OrderItem orderItem = (OrderItem) me.getValue();
                        String query = "UPDATE product_info SET quantity_stock = quantity_stock -" + orderItem.getAmount() + " WHERE id=" + orderItem.getId()+ ";";
                        try{
                            PreparedStatement ps = con.prepareStatement(query);
                            ps.executeUpdate();

                        } catch(Exception e){
                            System.out.println(e);
                        }
                    }
                }
            request.getRequestDispatcher("/purchaseconfirmation.jsp").forward(request, response);

        }else{
            request.getRequestDispatcher("/shoppingpage.jsp").forward(request, response);
        }






    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
