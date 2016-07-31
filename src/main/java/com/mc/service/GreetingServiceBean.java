package com.mc.service;

import java.util.Collection;

import com.mc.model.Greeting;
import com.mc.repository.GreetingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GreetingServiceBean implements GreetingService {
	
	@Autowired
	private GreetingRepository greetingRepository;	
	
    @Override
    public Collection<Greeting> findAll() {
        Collection<Greeting> greetings = greetingRepository.findAll();
        return greetings;
    }

    @Override
    public Greeting findOne(Long id) {
        Greeting greeting = greetingRepository.findOne(id);
        return greeting;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Greeting create(Greeting greeting) {
    	if(greeting.getId()!=null){
    		//Can't create Greeting with specified ID value
    		return null;
    	}    	
        Greeting savedGreeting = greetingRepository.save(greeting);
        //Illustrate Transaction rollback
        if(savedGreeting.getId() == 4){
        	throw new RuntimeException("Roll me back!");
        }        	
        return savedGreeting;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Greeting update(Greeting greeting) {
    	Greeting greetingPersisted = findOne(greeting.getId());
    	if(greetingPersisted == null){
    		// Can't update Greeting that hasn't been persisted
    		return null;
    	}
        Greeting savedGreeting = greetingRepository.save(greeting);
        return savedGreeting;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Long id) {
    	greetingRepository.delete(id);
    }

}