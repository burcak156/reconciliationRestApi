package com.ttech.reconciliation.captcha;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CaptchaClient {

	
	public JSONObject callGetCaptchaService(String url, String applKey, String secretKey, String gwToken) {

		// request url
	
		// create an instance of RestTemplate
		RestTemplate restTemplate = new RestTemplate();

		// create headers
		HttpHeaders headers = new HttpHeaders();
		// set `content-type` header
		headers.setContentType(MediaType.APPLICATION_JSON);
		// set `accept` header
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		
		headers.add("authorization", "Bearer " + gwToken);
		headers.add("servicesecretkey", secretKey);
		headers.add("applicationkey", applKey);
		
		
		
		// request body parameters
		Map<String, Object> map = new HashMap<>();
		map.put("level", 3);
		map.put("soundRequested", false);
		map.put("length", 6);

		// build the request
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

		// send POST request
		ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

		// check response
		if (response.getStatusCode() == HttpStatus.OK) {
		//	System.out.println("Request Successful");
			//System.out.println(response.getBody().toString());
			 JSONObject respJson = new JSONObject(response.getBody());
			 return respJson;//respJson.getJSONObject("message").getString("image");
			 
		} else {
			//System.out.println("Request Failed");
		//	System.out.println(response.getStatusCode());
			return null;
		}


	}
	
	public JSONObject callCheckCaptchaService(String url, String applKey, String secretKey, String gwToken, String captchaText, String captchaId) {

		//System.out.println("captchaText" + captchaText + " captchaId " + captchaId);
		// request url
		// create an instance of RestTemplate
		RestTemplate restTemplate = new RestTemplate();

		// create headers
		HttpHeaders headers = new HttpHeaders();
		// set `content-type` header
		headers.setContentType(MediaType.APPLICATION_JSON);
		// set `accept` header
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		headers.add("authorization", "Bearer " + gwToken);
		headers.add("servicesecretkey", secretKey);
		headers.add("applicationkey", applKey);
		
		
		
		// request body parameters
		Map<String, Object> map = new HashMap<>();
		map.put("captchaText", captchaText);
		map.put("identifier", captchaId);

		// build the request
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

		// send POST request
		ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

		// check response
		if (response.getStatusCode() == HttpStatus.OK) {
			System.out.println("Request Successful");
			System.out.println(response.getBody().toString());
			 JSONObject respJson = new JSONObject(response.getBody());
			 return respJson;//respJson.getJSONObject("message").getString("image");
			 
		} else {
			System.out.println("Request Failed");
			System.out.println(response.getStatusCode());
			return null;
		}


	}
	
	

}
