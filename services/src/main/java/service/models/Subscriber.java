package service.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Subscriber {
	
	@Id
	@GeneratedValue
	private Long subscriberId;
	private String apiKey;
	private String mobileToken;
	
	public Subscriber(Long id, String apiKey, String mobileToken) {
		this.subscriberId = id;
		this.apiKey = apiKey;
		this.mobileToken = mobileToken;
	}

	public Subscriber() {
	}
	
	public Long getId() {
		return subscriberId;
	}
	
	public void setId(Long id) {
		this.subscriberId = id;
	}
	
	public String getApiKey() {
		return apiKey;
	}
	
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public String getMobileToken() {
		return mobileToken;
	}
	
	public void setMobileToken(String mobileToken) {
		this.mobileToken = mobileToken;
	}
	
}
