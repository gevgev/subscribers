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
@WebServlet("/updateSubscriber")
public class updateSubscriberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	final static Logger logger = Logger.getLogger(updateSubscriberServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateSubscriberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		
		if( id!= null) {
			
			Subscriber subscriber = getSubscriberById(id);
			if(subscriber != null) {
				request.setAttribute("apiKey", subscriber.getApiKey());
				request.setAttribute("mobileToken", subscriber.getMobileToken());
				request.setAttribute("subscriberId", subscriber.getSubscriberId().toString());				
			}
		}

		RequestDispatcher view = request.getRequestDispatcher("updateSubscriberJSP.jsp");
        
        view.forward(request, response);		
	}

	private Subscriber getSubscriberById(String id) {
        Client client = Client.create();        
        WebResource resource = client.resource("http://localhost:8888/subscriber").queryParam("id", id); 
    	
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        
		if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}

		String jsonString = response.getEntity(String.class).toString();
		
		JsonMarshallUnmarshaller unmarshaller = new JsonMarshallUnmarshaller();
		
		Subscriber subscriber = unmarshaller.unmarshal(jsonString).get(0);

        logger.info(String.format("Received: [%d]  SubscriberId: [%d]  apiKey [%s]  mobileToken [%s]",
        		response.getStatus(), subscriber.getSubscriberId(), subscriber.getApiKey(), subscriber.getMobileToken() ));
		
		return subscriber;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String apiKey;
		String mobileToken;
		String subscriberId;
		
		apiKey = request.getParameter("apiKey");
		mobileToken = request.getParameter("mobileToken");
		subscriberId = request.getParameter("id");
//		subscriberId = request.getParameter("subscriberId");
				
		boolean result = UpdateSubscriber(subscriberId, apiKey, mobileToken);			
		logger.info(String.format("Modified with id: [%s] apiKey: [%s] mobileToken[%s]  status: [%b]", 
				subscriberId, apiKey, mobileToken, result));
		
		response.sendRedirect("/subscribers/dynamicsubscribers");
	}

	private boolean UpdateSubscriber(String subscriberId, String apiKey, String mobileToken) {
        Client client = Client.create();        
        WebResource resource = client.resource("http://localhost:8888/subscriber");    
        Subscriber newSubscriber = new Subscriber(apiKey, mobileToken);
    	
        ObjectMapper mapper = new ObjectMapper();
    	
        try {
        	Long id = Long.parseLong(subscriberId);
        	newSubscriber.setSubscriberId(id);
        	
			String jsonString = mapper.writeValueAsString(newSubscriber);
	       
			logger.info("About to Update by PUT: " + jsonString);
			
			ClientResponse response = resource.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, jsonString);
	        
			if (response.getStatus() != 200) {
				logger.error("Received HTTP Error" + response.getStatus() + " for PUT: "+ jsonString );
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
