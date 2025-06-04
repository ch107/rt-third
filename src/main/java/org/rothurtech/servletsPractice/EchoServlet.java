package org.rothurtech.servletsPractice;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;

@WebServlet("/echo")
public class EchoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        // retrieve query parameter
        String name = req.getParameter("name"); // ?name=Hao
        res.setContentType("text/html");
        res.getWriter().println("<h1>Hello, " + (name != null ? name : "Guest") + "!</h1>");
    }
}