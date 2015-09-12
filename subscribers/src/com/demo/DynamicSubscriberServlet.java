package com.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.models.Subscriber;

/**
 * Servlet implementation class dynamicSubscriberServlet
 */
@WebServlet("/dynamicsubscribers")
public class DynamicSubscriberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DynamicSubscriberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        List<Subscriber> subscribers = new ArrayList<Subscriber>();
        
        Subscriber d1 = new Subscriber("dynamic_apikey0123", "dynamic_mobileToken234");
 
        Subscriber d2 = new Subscriber("dynamic_apikey0123", "dynamic_mobileToken234");
 
        Subscriber d3 = new Subscriber("dynamic_apikey0123", "dynamic_mobileToken234");
 
        subscribers.add(d1);
        subscribers.add(d2);
        subscribers.add(d3);
        request.setAttribute("subscribersCtrl", subscribers);
 
        RequestDispatcher view = request.getRequestDispatcher("SubscribersJSP.jsp");
        
        view.forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
