package com.mint.financial.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mint.financial.entity.NoOfHits;
import com.mint.financial.entity.PayLoad;

//this  is the repository
@Repository
public interface PayloadRepository extends MongoRepository<NoOfHits,String> {
    
	    List<NoOfHits> findByPayLoadCardNumber(String cardNumber) ;
}






















