package com.demo;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;


//import common.models.Subscriber;
 
public class SubscriberServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Subscriber> subscribers = new ArrayList<Subscriber>();
 
        Subscriber d1 = new Subscriber("apikey0123", "mobileToken234");
 
        Subscriber d2 = new Subscriber("apikey0123", "mobileToken234");
 
        Subscriber d3 = new Subscriber("apikey0123", "mobileToken234");
 
        subscribers.add(d1);
        subscribers.add(d2);
        subscribers.add(d3);
        request.setAttribute("subscribersCtrl", subscribers);
 
        RequestDispatcher view = request.getRequestDispatcher("SubscribersJSP.jsp");
        
        view.forward(request, response);
    }
}