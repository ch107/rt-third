package org.rothurtech.servletsPractice;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;

@WebServlet("/api/user")
public class JsonServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        res.getWriter().println("{ \"id\": 1, \"name\": \"Hao\" }");
    }
}
