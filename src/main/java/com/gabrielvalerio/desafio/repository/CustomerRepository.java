package com.gabrielvalerio.desafio.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.gabrielvalerio.desafio.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

  public List<Customer> findByName(String name);

}