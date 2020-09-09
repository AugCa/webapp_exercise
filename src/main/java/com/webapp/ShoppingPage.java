package com.webapp;

import com.OrderItem;
import com.Product;
import com.ProductFetcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "/ShoppingPage")
public class ShoppingPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<Integer, OrderItem> orderItems = new HashMap<>();
        ProductFetcher pf = new ProductFetcher();
        List<Product> products = pf.getProducts();
        Iterator<Product> iter = products.iterator();

        while(iter.hasNext()){
            Product product = iter.next();
            String id = product.getIdAsString();
            String str = request.getParameter(id + ",");

            try{
                int purchaseQuantity = Integer.parseInt(str);
                if(purchaseQuantity > 0 && purchaseQuantity <= product.getStock()){
                    OrderItem orderItem = new OrderItem();
                    orderItem.setId(product.getId());
                    orderItem.setPrice(product.getPrice());
                    orderItem.setAmount(purchaseQuantity);
                    orderItem.setProductDescription(product.getProductDescription());
                    orderItems.put(product.getId(), orderItem);
                }
                else if(purchaseQuantity != 0){
                    request.setAttribute("errorMessage", (purchaseQuantity < 0) ? "The requested amount can not be a negative number" :
                            "The requested amount is not in stock");
                }
            }catch(NumberFormatException e){
                request.setAttribute("errorMessage", "Please enter a numeric value in the quantity field");
            }


        }
        Order order = (orderItems.isEmpty()) ? null : new Order(orderItems);
        request.setAttribute("order" ,order);
        request.getSession().setAttribute("order", order);
        request.getRequestDispatcher("/summarypage.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
