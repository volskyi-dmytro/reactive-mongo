package com.stpunk47.reactive_mongo.repositories;

import com.stpunk47.reactive_mongo.domain.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
}
