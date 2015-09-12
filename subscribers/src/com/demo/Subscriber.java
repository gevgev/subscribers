package com.demo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "Subscriber")
public class Subscriber implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long subscriberId;
	
	@NotNull
	private String apiKey;
	
	@NotNull
	private String mobileToken;
	
//	public Subscriber(Long id, String apiKey, String mobileToken) {
//		this.subscriberId = id;
//		this.apiKey = apiKey;
//		this.mobileToken = mobileToken;
//	}
	
	public Subscriber(long id) { 
		this.subscriberId = id;
	}

	public Subscriber(String apiKey, String mobileToken) {
		this.apiKey = apiKey;
		this.mobileToken = mobileToken;
	}

	public Subscriber() {
	}
	
	public Long getSubscriberId() {
		return subscriberId;
	}
	
	public void setSubscriberId(Long id) {
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