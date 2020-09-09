<%@ page import="com.ProductFetcher" %>
<%@ page import="com.Product" %>
<%@ page import="com.webapp.Order" %>
<%@ page import="com.OrderItem" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Summary Page</title>

</head>
<body>
<h2>Summary Page</h2>
Customer: <%=request.getSession().getAttribute("firstname") %> <%=request.getSession().getAttribute("lastname")%>

<div style="text-align: right"><a href="LogoutServlet">Logout</a></div>
<hr>
<%
    Order order = (Order) request.getAttribute("order");
    request.getSession().setAttribute("error" , (request.getAttribute("errorMessage") != null || order == null));
%>

<form name="form" action="SummaryPage" method="post">
    <table border="1">
        <tr>
            <td>Product name</td>
            <td>Quantity</td>
            <td>Price</td>
        </tr>
        <%
            if(order!=null){
                HashMap<Integer, OrderItem> items = order.getOrderItems();
                Iterator iter = items.entrySet().iterator();
                while(iter.hasNext()) {
                    Map.Entry item = (Map.Entry) iter.next();
                    OrderItem orderItem = (OrderItem) item.getValue();


        %>
        <tr>
            <td><%=orderItem.getProductDescription()%></td>
            <td><%=orderItem.getAmount()%></td>
            <td><%=orderItem.getCost()%></td>

        </tr>
        <%
            }
            }

        %>
        <tr>
            <span style="color:red"><%=(request.getAttribute("errorMessage") == null) ? ""
                    : request.getAttribute("errorMessage")%></span>
        </tr>


    </table>

    <button class="btn btn-default" type="submit" ><%=((boolean) request.getSession().getAttribute("error")) ?
            "Go back to shopping page" : "Complete the purchase" %></>
</form>




</body>
</html>

