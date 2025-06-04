package org.rothurtech.servletsPractice;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;

@WebServlet("/hello") // map this servlet to url /hello
public class HelloServlet extends HttpServlet {

    // doGet() is protected because it is defined this way in HttpServlet
    // doGet() handle GET request
    // req contains request info such as headers, query params
    // res, created by tomcat, used to write response data
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        // set response content type to html so browser knows how to render it
        res.setContentType("text/html");

        // getWriter() return a PrintWriter which let you write text into http response body
        res.getWriter().println("<h1>Hello from Servlet!</h1>");
    }
}

