package com.mc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mc.model.*;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long> {
}
