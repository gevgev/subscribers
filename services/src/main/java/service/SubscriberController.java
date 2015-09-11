package service;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/subscriber")
public class SubscriberController {

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody Subscriber getSubscriber(@RequestParam(value="id", required=false, defaultValue="1") int id) {
        return new Subscriber(id, "apiKey12345", "mobileToken");
    }

}