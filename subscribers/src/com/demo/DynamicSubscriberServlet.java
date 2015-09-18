package com.demo;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import common.models.Subscriber;

/**
 * Servlet implementation class dynamicSubscriberServlet
 */
@WebServlet("/dynamicsubscribers")
public class DynamicSubscriberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	final static Logger logger = Logger.getLogger(DynamicSubscriberServlet.class);
	
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

        request.setAttribute("subscribersCtrl", populateSubscribersList());
 
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
	
	private List<Subscriber> populateSubscribersList() {
		Client client = Client.create();

		WebResource webResource = client
		   .resource(com.util.Properties.getServiceURL());

		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON)
                   .get(ClientResponse.class);

		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}
		
		String jsonString = response.getEntity(String.class);
		logger.info("Received: " + jsonString);
		
		JsonMarshallUnmarshaller marshaller = new JsonMarshallUnmarshaller();
		
		List<Subscriber> list = marshaller.unmarshal(jsonString);
		
		return list;
	}
	
	

}
