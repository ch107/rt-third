package org.rothurtech.httpservletCRUD;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/order1")
public class OrderServlet implements Servlet {

    private OrderDAO orderDAO = new OrderDAO();

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException{
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String method = request.getMethod();

        switch (method) {
            case "POST":
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    String item = req.getParameter("item");
                    double amount = Double.parseDouble(req.getParameter("amount"));
                    Order order = new Order(id, item, amount);
                    orderDAO.createOrder(order);
                } catch (Exception e) {
                    sendSafeError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error creating order.");
                }
                break;
            case "GET":
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    Order order = orderDAO.getOrder(id);
                    if (order != null) {
                        response.setContentType("text/plain");
                        response.getWriter().write("Order: " + order.getId() + ", " + order.getItem() + ", " + order.getAmount());
                    } else {
                        sendSafeError(response, HttpServletResponse.SC_NOT_FOUND, "Order not found.");
                    }
                } catch (Exception e) {
                    sendSafeError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching order.");
                }
                break;
            case "PUT":
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    String item = req.getParameter("item");
                    double amount = Double.parseDouble(req.getParameter("amount"));
                    Order order = new Order(id, item, amount);
                    orderDAO.updateOrder(order);
                } catch (Exception e) {
                    sendSafeError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating order.");
                }
                break;
            case "DELETE":
                int id = Integer.parseInt(req.getParameter("id"));
                try {
                    orderDAO.deleteOrder(id);
                } catch (Exception e) {
                    sendSafeError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting order.");
                }
                break;
            default:
                sendSafeError(response, HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Method not allowed.");
        }
    }

    private void sendSafeError(HttpServletResponse res, int status, String message) throws ServletException {
        try {
            res.sendError(status, message); // This is I/O, it writes to HTTP response, may throw IOException
        } catch (IOException e) {
            throw new ServletException("Failed to send error response", e);
        }
    }

    // Other required methods
    @Override
    public void init(ServletConfig config) throws ServletException {
        // Optional: store config or init resources
    }

    @Override
    public ServletConfig getServletConfig() {
        return null; // or store and return config if used
    }

    @Override
    public void destroy() {
        // Optional: cleanup resources
    }

    @Override
    public String getServletInfo() {
        return "order1 servlet implements servelet interface"; // or just return null
    }
}
