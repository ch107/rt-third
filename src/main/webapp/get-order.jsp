<%--
  Created by IntelliJ IDEA.
  User: ch
  Date: 6/4/25
  Time: 10:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.rothurtech.httpservletCRUD.Order" %>
<html>
<head>
    <title>Get an Order</title>
</head>
<body>
<form action="order" method="get">
    ID: <input type="text" name="id"><br/>
    <input type="submit" value="Get Order"/>
</form>
<%
    Order order = (Order) request.getAttribute("order");
    if (order != null) {
%>
<h3>Retrieved Order:</h3>
<p>ID: <%= order.getId() %></p>
<p>Item: <%= order.getItem() %></p>
<p>Amount: <%= order.getAmount() %></p>
<%
    }
%>
<a href="index.jsp">Back</a>
</body>
</html>
