package com.milli.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import java.net.URI;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.milli.cache.controller.CacheController;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CacheServicesApplicationTests {

	@LocalServerPort
    private int serverPort;

    @Autowired
    private TestRestTemplate testRestTemplate;
    
    @Autowired
    private CacheController controller;
    
    @Test
    public void contexLoads() throws Exception {
        Assert.assertNotNull(controller);
    }
    
    @Test
	public void putKeyValue() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> requestBody= new LinkedMultiValueMap<>();
		requestBody.add("value", "1111");
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);

	    final String baseUrl = "http://localhost:" + serverPort + "/api/v1/put/1";
	    URI fullResourceURI = new URI(baseUrl);
	 
	    
	    // throws rest client exception if there is any issue.
	    restTemplate.put(fullResourceURI, request);
	}
	
	@Test
	public void q_getKeyValue() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
	     
	    final String baseUrl = "http://localhost:" + serverPort + "/api/v1/get/1";
	    URI uri = new URI(baseUrl);
	 
	   // ResponseEntity<com.milli.cache.model.KeyValPair> result = restTemplate.getForEntity(uri, com.milli.cache.model.KeyValPair.class);
	    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
	     
	    //Verify request succeed
	    Assert.assertEquals(200, result.getStatusCodeValue());
	    
	    System.out.println("Body = " + result.getBody());
	}
}
