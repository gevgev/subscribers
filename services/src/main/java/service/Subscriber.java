package service;

public class Subscriber {
	private int id;
	private String apiKey;
	private String mobileToken;
	
	public Subscriber(int id, String apiKey, String mobileToken) {
		this.id = id;
		this.apiKey = apiKey;
		this.mobileToken = mobileToken;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
