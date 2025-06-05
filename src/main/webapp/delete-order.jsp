<%--
  Created by IntelliJ IDEA.
  User: ch
  Date: 6/4/25
  Time: 10:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.rothurtech.httpservletCRUD.Order" %>
<html>
<head>
    <title>Delete an Order</title>
</head>
<body>
<form action="order" method="post">
    <input type="hidden" name="_method" value="DELETE"/>
    ID: <input type="text" name="id"><br/>
    <input type="submit" value="Delete Order"/>
</form>
<hr/>
<%
    Order order = (Order) request.getAttribute("order");
    if (order != null) {
%>
<h3>Order Deleted:</h3>
<p>ID: <%= order.getId() %></p>
<p>Item: <%= order.getItem() %></p>
<p>Amount: <%= order.getAmount() %></p>
<%
    }
%>
<a href="index.jsp">Back</a>
</body>
</html>
