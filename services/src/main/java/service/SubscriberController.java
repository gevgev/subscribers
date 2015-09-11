package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import service.models.Subscriber;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.dbAccess.SubscriberDao;

@Controller
@RequestMapping("/subscriber")
public class SubscriberController {

	private static final Logger log = LoggerFactory.getLogger(SubscriberController.class);

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody List<Subscriber> getSubscriber(@RequestParam(value="id", required=false) String id) {
        // return new Subscriber(id, "apiKey12345", "mobileToken");

    	List<Subscriber> results = null;

    	if(id == null) {
    		//results = dbDataAccess.getSubscribers();
    		results = subscriberDao.getAll();
    	}
    	else {
    		try {
    			results = new ArrayList<Subscriber>();
    			Long _id = Long.parseLong(id);
        		// Subscriber subscriber = dbDataAccess.getSubscriber(_id);
    			Subscriber subscriber = subscriberDao.getById(_id);
        		results.add(subscriber);
    		}
    		catch(Exception ex) {
				ex.printStackTrace();
				log.error(ex.toString());
    		}
    	}
        return results;
    }

    @RequestMapping(method=RequestMethod.POST)
    public @ResponseBody Subscriber saveSubscriber(@RequestBody Subscriber subscriber) {

    	log.info(String.format("New subscriber data: apiKey: [%s]   token: [%s]", subscriber.getApiKey(), subscriber.getMobileToken()));
    	
    	// int _id = dbDataAccess.createSubscriber(subscriber);
    	
//    	Subscriber _subscriber = 
//    		new Subscriber(
//    				_id, 
//    				subscriber.getApiKey(), subscriber.getMobileToken() );
    	
    	// subscriberDao.create(subscriber); 
    	
    	Subscriber _subscriber = subscriberDao.create(subscriber);
    	
    	return _subscriber;
    }

	  // Wire the UserDao used inside this controller.
	  @Autowired
	  private SubscriberDao subscriberDao;


}