package service;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dbAccess.dbDataAccess;
import models.Subscriber;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/subscriber")
public class SubscriberController {

	private static final Logger log = LoggerFactory.getLogger(SubscriberController.class);

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody List<Subscriber> getSubscriber(@RequestParam(value="id", required=false) String id) {
        // return new Subscriber(id, "apiKey12345", "mobileToken");

    	List<Subscriber> results = null;

    	if(id == null) {
    		results = dbDataAccess.getSubscribers();
    	}
    	else {
    		try {
    			results = new ArrayList<Subscriber>();
    			int _id = Integer.parseInt(id);
        		Subscriber subscriber = dbDataAccess.getSubscriber(_id);
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
    	
    	Subscriber _subscriber = 
    		new Subscriber(
    				dbDataAccess.createSubscriber(subscriber), 
    				subscriber.getApiKey(), subscriber.getMobileToken() );

    	return _subscriber;
    }


}