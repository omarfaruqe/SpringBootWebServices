package com.mc.service;

import java.util.Collection;

import com.mc.model.Greeting;

public interface GreetingService {

    Collection<Greeting> findAll();

    Greeting findOne(Long id);

    Greeting create(Greeting greeting);

    Greeting update(Greeting greeting);

    void delete(Long id);
    
    void evictCache();
}
