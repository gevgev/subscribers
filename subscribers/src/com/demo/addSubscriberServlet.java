package com.demo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import common.models.Subscriber;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Servlet implementation class addSubscriberServlet
 */
@WebServlet("/addSubscriber")
public class addSubscriberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	final static Logger logger = Logger.getLogger(addSubscriberServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public addSubscriberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		
		RequestDispatcher view = request.getRequestDispatcher("addSubscriberJSP.jsp");
        
        view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String apiKey;
		String mobileToken;
		
		apiKey = request.getParameter("apiKey");
		mobileToken = request.getParameter("mobileToken");

		boolean result = CreateNewSubscriber(apiKey, mobileToken);
		
		logger.info(String.format("Created with apiKey: [%s] mobileToken[%s]  status: [%b]",  apiKey, mobileToken, result));
		
		RequestDispatcher view = request.getRequestDispatcher("SubscribersJSP.jsp");
        
        view.forward(request, response);
	}

	private boolean CreateNewSubscriber(String apiKey, String mobileToken) {
        Client client = Client.create();        
        WebResource resource = client.resource("http://localhost:8888/subscriber");    
        Subscriber newSubscriber = new Subscriber(apiKey, mobileToken);
        newSubscriber.setSubscriberId(-1L);
    	
        ObjectMapper mapper = new ObjectMapper();
    	
        try {
			String jsonString = mapper.writeValueAsString(newSubscriber);
	       
			logger.info("About to submit PUT: " + jsonString);
			
			ClientResponse response = resource.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, jsonString);
	        
			if (response.getStatus() != 200) {
				   throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
				}
			
			Subscriber subscriber = mapper.readValue(response.getEntity(String.class), Subscriber.class);

	        logger.info(String.format("Status: [%d]  SubscriberId: [%d]",
	        		response.getStatus(), subscriber.getSubscriberId() ));
			
			return true;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			logger.error("Exception while converting to JSON:"+e.toString());
		} catch (JsonMappingException e) {
			e.printStackTrace();
			logger.error("Exception while converting to JSON:"+e.toString());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Exception while converting to JSON:"+e.toString());
		}
        
        return false;    
	}

}
