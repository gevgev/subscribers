package com.demo;

import java.io.IOException;
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

/**
 * Servlet implementation class deleteSubscriber
 */
@WebServlet("/deleteSubscriber")
public class deleteSubscriberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	final static Logger logger = Logger.getLogger(deleteSubscriberServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteSubscriberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        
        deleteSubscriber(id);
        
		response.sendRedirect("/subscribers/dynamicsubscribers");

	}

	private void deleteSubscriber(String id) {
		if (id != null) {
        	
			Client client = Client.create();       
	        WebResource resource = client.resource(com.util.Properties.SERVICE_URL).queryParam("id", id); 

	        logger.info("About to Delete: " + id);
	    	
			ClientResponse response = resource.type(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
	        
			if (response.getStatus() != 200) {
				logger.error("Received HTTP Error" + response.getStatus() + " for DELETE: "+ id );
				   throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
				}
	        }
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
