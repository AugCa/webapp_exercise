<!DOCTYPE html>
<html>
<head>
    <title>Confirmation Page</title>

</head>
<body>
<h2>Order confirmed</h2>
We are pleased to tell you that your order has been confirmed. <br>
Thank you for shopping with us <%=request.getSession().getAttribute("firstname") %> <%=request.getSession().getAttribute("lastname")%> <br>

<a href="shoppingpage.jsp">Continue shopping</a>
<div style="text-align: right"><a href="LogoutServlet">Logout</a></div>

</body>
</html>
