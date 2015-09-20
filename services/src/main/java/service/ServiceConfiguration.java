package service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@SpringBootApplication
public class ServiceConfiguration {

	// private static final Logger log = LoggerFactory.getLogger(ServiceConfiguration.class);

	@Bean
    public ShallowEtagHeaderFilter shallowEtagHeaderFilter() {
        return new ShallowEtagHeaderFilter();
    }
	
    public static void main(String[] args) {
        SpringApplication.run(ServiceConfiguration.class, args);
    }

}