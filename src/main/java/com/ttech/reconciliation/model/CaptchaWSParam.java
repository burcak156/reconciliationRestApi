package com.ttech.reconciliation.model;

public class CaptchaWSParam {
	private String getUrl;
	private String checkUrl;
	private String applKey;
	private String secretKey;
	private String gwToken;

	public CaptchaWSParam() {
		
	}

	public String getGetUrl() {
		return getUrl;
	}

	public void setGetUrl(String getUrl) {
		this.getUrl = getUrl;
	}

	public String getCheckUrl() {
		return checkUrl;
	}

	public void setCheckUrl(String checkUrl) {
		this.checkUrl = checkUrl;
	}

	public String getApplKey() {
		return applKey;
	}

	public void setApplKey(String applKey) {
		this.applKey = applKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getGwToken() {
		return gwToken;
	}

	public void setGwToken(String gwToken) {
		this.gwToken = gwToken;
	}

}
