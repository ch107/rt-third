<%--
  Created by IntelliJ IDEA.
  User: ch
  Date: 6/4/25
  Time: 10:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.rothurtech.httpservletCRUD.Order" %>
<html>
<head>
    <title>Create an Order</title>
</head>
<body>
<%--action="order" -> Send the request to the URL path /order--%>
<form action="order" method="post"> Send the request to the URL path /order
  <input type="hidden" name="_method" value="POST"/>
  ID: <input type="text" name="id"><br/>
  Item: <input type="text" name="item"><br/>
  Amount: <input type="text" name="amount"><br/>
  <input type="submit" value="Create Order"/>
</form>
<%
  Order order = (Order) request.getAttribute("order");
  if (order != null) {
%>
<h3>Order Created:</h3>
<p>ID: <%= order.getId() %></p>
<p>Item: <%= order.getItem() %></p>
<p>Amount: <%= order.getAmount() %></p>
<%
  }
%>
<a href="index.jsp">Back</a>
</body>
</html>
