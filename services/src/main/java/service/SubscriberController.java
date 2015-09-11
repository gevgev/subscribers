package service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import service.dbAccess.SubscriberDao;
import common.models.Subscriber;

@Controller
@RequestMapping("/subscriber")
public class SubscriberController {

	private static final Logger log = LoggerFactory.getLogger(SubscriberController.class);

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody List<Subscriber> getSubscriber(@RequestParam(value="id", required=false) String id) {

    	List<Subscriber> results = null;

    	if(id == null) {
    		results = subscriberDao.getAll();
    	}
    	else {
    		try {
    			results = new ArrayList<Subscriber>();
    			Long _id = Long.parseLong(id);

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
    	    	
    	Subscriber _subscriber = subscriberDao.create(subscriber);
    	
    	return _subscriber;
    }
    
    @RequestMapping(method=RequestMethod.PUT)
    public @ResponseBody Subscriber updateSubscriber(@RequestBody Subscriber subscriber) {
    	log.info(String.format("Updating subscriber data: id: [%s]  apiKey: [%s]   token: [%s]", subscriber.getSubscriberId().toString(), subscriber.getApiKey(), subscriber.getMobileToken()));
    	
    	subscriberDao.update(subscriber);
    	
    	return subscriber;
    }
    
    @SuppressWarnings("rawtypes")
	@RequestMapping(method=RequestMethod.DELETE)
    public ResponseEntity deleteSubscriber(@RequestParam(value="id", required=true) Long id) {
    	
    	Subscriber subscriber = new Subscriber(id);
    	try {
    		subscriberDao.delete(subscriber);
    		return ResponseEntity.ok().build();
  
    	} catch(Exception ex)
    	{
    		log.error(String.format("Exception while deleting subscriber id: [%s] --- : %s", id.toString(), ex.toString()));
    		return ResponseEntity.notFound().build();
    	}
    	
    }

	  // Wire the UserDao used inside this controller.
	  @Autowired
	  private SubscriberDao subscriberDao;


}