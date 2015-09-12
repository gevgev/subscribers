package com.demo;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
 
public class TestDemoServlet extends HttpServlet {
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Demo> demoNames = new ArrayList<Demo>();
 
        Demo d1 = new Demo();
        d1.setName("Rahul");
 
        Demo d2 = new Demo();
        d2.setName("Rahul123");
 
        Demo d3 = new Demo();
        d3.setName("Rahul456");
 
        demoNames.add(d1);
        demoNames.add(d2);
        demoNames.add(d3);
        request.setAttribute("demoNames", demoNames);
 
        RequestDispatcher view = request.getRequestDispatcher("DemoJSP.jsp");
        view.forward(request, response);
    }
}