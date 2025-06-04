package org.rothurtech.httpservletCRUD;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order")
public class OrderHttpServlet extends HttpServlet {

    private final OrderDAO orderDAO = new OrderDAO();

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // Create user using JDBC
        int id = Integer.parseInt(req.getParameter("id"));
        double amount = Double.parseDouble(req.getParameter("amount"));
        String item = req.getParameter("item");

        try {
            orderDAO.createOrder(new Order(id, item, amount));
            res.getWriter().write("order created");
        } catch (Exception e) {
            res.sendError(500, "Error: " + e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // Read user(s) using JDBC
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            Order order = orderDAO.getOrder(id);
            if (order != null) {
                res.getWriter().write("Order id: " + order.getId() + ", " + "Item: " + order.getItem() + ", " +  "Amount: " + order.getAmount());
            } else {
                res.getWriter().write("Order not found");
            }
        } catch (Exception e) {
            res.sendError(500, "Error: " + e.getMessage());
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // Update user using JDBC
        int id = Integer.parseInt(req.getParameter("id"));
        double amount = Double.parseDouble(req.getParameter("amount"));
        String item = req.getParameter("item");

        try {
            Order order = new Order(id, item, amount);
            orderDAO.updateOrder(order);
            res.getWriter().write("Order updated");
        } catch (Exception e) {
            res.sendError(500, "Error: " + e.getMessage());
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // Delete user using JDBC
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            orderDAO.deleteOrder(id);
            res.getWriter().write("Order deleted");
        } catch (Exception e) {
            res.sendError(500, "Error: " + e.getMessage());
        }
    }
}
