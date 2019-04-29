package com.milli.cache.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.milli.cache.model.KeyNotFoundException;
import com.milli.cache.model.KeyValPair;
import com.milli.cache.service.CacheService;

@RestController
public class CacheController {
	
	@Autowired
	private CacheService cacheService;
	
	@GetMapping("/api/v1/get/{key}")
	public KeyValPair get(@PathVariable Integer key) {
		Integer val =  cacheService.get(key);
		if(val == null)
		{
			KeyNotFoundException keyNotFoundExc = new KeyNotFoundException("Key not found in the cache");
			throw keyNotFoundExc;
			//This add the the full stack trace to the exception. Need to remove it.
			//ResponseStatusException respStatExc =  new ResponseStatusException( HttpStatus.NOT_FOUND, "Key not found in the cache", null);
			//throw respStatExc;
		}
		else
		{
			KeyValPair keyValPair = new KeyValPair(key, val);
			return keyValPair;
		}
	}

	@PutMapping(path = "/api/v1/put/{key}", consumes = "application/x-www-form-urlencoded")
	public KeyValPair put(@PathVariable Integer key, @RequestParam MultiValueMap<String,String> body) {
		try
		{
		Integer val = Integer.parseInt(body.getFirst("value")); // body.get("value");
		cacheService.put(key, val);
		KeyValPair keyValPair = new KeyValPair(key, val);
		return keyValPair;
		}
		catch(Exception ex)
		{
			 System.out.println("exc = " + ex.getMessage());
			 throw ex;
		}
	}
}
