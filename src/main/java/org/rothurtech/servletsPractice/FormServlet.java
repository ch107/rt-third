package org.rothurtech.servletsPractice;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;

@WebServlet("/form")
public class FormServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");

        // return a html with <form>
        res.getWriter().println(
                "<form method='post'>" +
                        "Name: <input type='text' name='name' />" +
                        "<input type='submit' value='Submit' />" +
                        "</form>"
        );
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        // retrieve form fields
        String name = req.getParameter("name");
        res.setContentType("text/html");
        res.getWriter().println("<h2>You submitted: " + name + "</h2>");
    }
}
