<%@ page import="com.ProductFetcher" %>
<%@ page import="com.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.webapp.Order" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.OrderItem" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Shopping Page</title>

</head>
<body>
<h2>Shopping Page</h2>
Welcome <%=request.getSession().getAttribute("firstname") %> <%=request.getSession().getAttribute("lastname") %> <!-- Refer to the video to understand how this works -->
<div style="text-align: right"><a href="LogoutServlet">Logout</a></div>
<hr>

<%
    ProductFetcher productFetcher = new ProductFetcher();
    List<Product> productList = productFetcher.getProducts();
    Iterator<Product> iter = productList.iterator();
%>

<form name="form" action="ShoppingPage" method="post">
<table border="1">
    <tr>
        <td>Product id</td>
        <td>Product Description</td>
        <td>Price</td>
        <td>Stock</td>
        <td>Picture</td>
        <td>Purchase amount</td>

    </tr>
<%
    Order order = (Order) request.getSession().getAttribute("order");
    HashMap<Integer, OrderItem> orderItems = new HashMap<>();



while(iter.hasNext()) {
    Product product;
    product = iter.next();
    String id = product.getIdAsString();

    int quantity = 0;
    if(order != null){
        orderItems = order.getOrderItems();
        quantity = (orderItems.containsKey(product.getId())) ? orderItems.get(product.getId()).getAmount() : 0;
    }


%>
<tr>
    <td><%=product.getId()%></td>
    <td><%=product.getProductDescription()%></td>
    <td><%=product.getPrice()%></td>
    <td><%=product.getStock()%> </td>
    <td><img src="<%=request.getContextPath()%>/files/<%=product.getProductImage()%>" width="50" height="50"></td>
    <td><input type="number", name=<%=id%>, value=<%=quantity%>></input> </td>

</tr>

    <%
        }

%>

</table>

    <input type="submit" value="Buy" ></input>
</form>




</body>
</html>