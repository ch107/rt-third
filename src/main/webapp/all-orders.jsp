<%--
  Created by IntelliJ IDEA.
  User: ch
  Date: 6/6/25
  Time: 3:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.rothurtech.httpservletCRUD.Order" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>All Orders</title>
</head>
<body>
<h2>All Orders</h2>

<%
  List<Order> orders = (List<Order>) request.getAttribute("orders");
  if (orders != null && !orders.isEmpty()) {
%>
<table border="1">
  <tr><th>ID</th><th>Item</th><th>Amount</th></tr>
  <%
    for (Order order : orders) {
  %>
  <tr>
    <td><%= order.getId() %></td>
    <td><%= order.getItem() %></td>
    <td><%= order.getAmount() %></td>
  </tr>
  <%
    }
  %>
</table>
<%
} else {
%>
<p>No orders found.</p>
<%
  }
%>

<a href="index.jsp">Back</a>
</body>
</html>
