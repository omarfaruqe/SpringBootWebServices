package com.mc.web.api;

import com.mc.model.*;

import java.math.BigInteger;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GreetingController {
	private static BigInteger nextId;
	private static Map<BigInteger, Greeting> greetingMap;
	
	private static Greeting save(Greeting greeting){
		if(greetingMap == null){
			greetingMap = new HashMap<BigInteger, Greeting>();
			nextId = BigInteger.ONE;
		}
		greeting.setId(nextId);
		nextId = nextId.add(BigInteger.ONE);
		greetingMap.put(greeting.getId(), greeting);
		return greeting;		
	}
	
	static{
		Greeting g1 = new Greeting();
		g1.setText("Hello World");
		save(g1);
		
		Greeting g2 = new Greeting();
		g2.setText("Bonjour le monde");
		save(g2);
		
		Greeting g3 = new Greeting();
		g3.setText("স্বাগতম হে বিশ্ব");
		save(g3);
	}
	
	@RequestMapping(value="/api/greeting", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Greeting>> getGreetings(){
		
		Collection<Greeting> greetings = greetingMap.values();
		
		return new ResponseEntity<Collection<Greeting>>(greetings, HttpStatus.OK);
	}
}
