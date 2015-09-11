package service;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class dbDataAccess {
	
	final static Logger logger = Logger.getLogger(dbDataAccess.class);

	//
	// Get all subscribers
	//
	public static List<Subscriber> getSubscribers() {

		List<Subscriber> results = new ArrayList<Subscriber>();

		String url = null;
		
		url = 
			"jdbc:mysql://173.194.110.154:3306/Subscriptions?user=testuser&password=usertestpsw01.";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.toString());
			
			return results;
		}

		java.sql.Connection conn;
		try {
			logger.info("url = " + url);
			conn = DriverManager.getConnection(url);
			ResultSet rs = conn.createStatement().executeQuery(
				    "SELECT subscriberId, apiKey, mobileToken FROM Subscriptions.Subscriber");
			
		     while(rs.next()) {
		         int id = rs.getInt("subscriberId");
		         String apiKey = rs.getString("apiKey");
		         String mobileToken = rs.getString("mobileToken");
		         
		         //results.add(String.format("id [%d]/t apiKey [%s]/t mobileToken [%s]", id, apiKey, mobileToken));
		         results.add(new Subscriber(id, apiKey, mobileToken));
		         
		      }
		      rs.close();
		      conn.close();
		      conn = null;
		 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error(e1.toString());
		}
		
		return results;
	}

	//
	// Get subscriber by Id
	//
	public static Subscriber getSubscriber(int id) {

		Subscriber subscriber = null;

		String url = null;
		
		url = 
			"jdbc:mysql://173.194.110.154:3306/Subscriptions?user=testuser&password=usertestpsw01.";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.toString());
			
			return subscriber;
		}
		PreparedStatement querySubscriber = null;

		java.sql.Connection conn;
		try {
			logger.info("url = " + url);
			conn = DriverManager.getConnection(url);

			String query = "SELECT subscriberId, apiKey, mobileToken FROM Subscriptions.Subscriber " +
							"WHERE subscriberId = ?";

			querySubscriber = conn.prepareStatement(query);
			querySubscriber.setInt(1, id);

			ResultSet rs = querySubscriber.executeQuery();
			
		     while(rs.next()) {
		         int _id = rs.getInt("subscriberId");
		         String apiKey = rs.getString("apiKey");
		         String mobileToken = rs.getString("mobileToken");
		         
		         //results.add(String.format("id [%d]/t apiKey [%s]/t mobileToken [%s]", id, apiKey, mobileToken));
		         subscriber = new Subscriber(_id, apiKey, mobileToken);
		         
		      }
		      rs.close();
		      conn.close();
		      conn = null;
		 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error(e1.toString());
		}
		
		return subscriber;
	}

}
