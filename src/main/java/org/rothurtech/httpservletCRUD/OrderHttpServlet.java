package org.rothurtech.httpservletCRUD;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/order")
public class OrderHttpServlet extends HttpServlet {

    private final OrderDAO orderDAO = new OrderDAO();

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String methodOverride = req.getParameter("_method");

        try {
            if (methodOverride != null) {
                switch (methodOverride.toUpperCase()) {
                    case "PUT":
                        doPut(req, res);
                        return;
                    case "DELETE":
                        doDelete(req, res);
                        return;
                }
            }

            int id = Integer.parseInt(req.getParameter("id"));
            String item = req.getParameter("item");
            double amount = Double.parseDouble(req.getParameter("amount"));

            Order order = new Order(id, item, amount);
            orderDAO.createOrder(order);
            Order newOrder = orderDAO.getOrder(id);
            req.setAttribute("order", newOrder);
            req.getRequestDispatcher("/create-order.jsp").forward(req, res);

        } catch (Exception e) {
            res.sendError(500, "Error: " + e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String idParam = req.getParameter("id");
        try {
            if (idParam != null) { // get by id
                int id = Integer.parseInt(idParam);
                Order order = orderDAO.getOrder(id);
                if (order != null) {
                    req.setAttribute("order", order);
                    req.getRequestDispatcher("/get-order.jsp").forward(req, res);
                } else {
                    res.sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found");
                }
            } else { // get all
                List<Order> orders = orderDAO.getAllOrders();
                req.setAttribute("orders", orders);
                //logging logic ---> request information: user inputs(httprequest)? + what's the success result
                req.getRequestDispatcher("/all-orders.jsp").forward(req, res);
            }
        } catch (Exception e) {
            res.sendError(500, "Error: " + e.getMessage());
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        double amount = Double.parseDouble(req.getParameter("amount"));
        String item = req.getParameter("item");

        try {
            Order order = new Order(id, item, amount);
            orderDAO.updateOrder(order);
            Order updatedOrder = orderDAO.getOrder(id);
            req.setAttribute("order", updatedOrder);
            req.getRequestDispatcher("/update-order.jsp").forward(req, res);
        } catch (Exception e) {
            res.sendError(500, "Error: " + e.getMessage());
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            Order order = orderDAO.getOrder(id); // Get before deleting
            if (order == null) {
                res.sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found");
                return;
            }

            orderDAO.deleteOrder(id);

            req.setAttribute("order", order);             // for showing on JSP
            req.setAttribute("action", "Delete");
            req.getRequestDispatcher("/delete-order.jsp").forward(req, res);
        } catch (Exception e) {
            res.sendError(500, "Error: " + e.getMessage());
        }
    }
}
