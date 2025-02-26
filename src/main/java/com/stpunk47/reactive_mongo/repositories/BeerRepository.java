package com.stpunk47.reactive_mongo.repositories;

import com.stpunk47.reactive_mongo.domain.Beer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BeerRepository extends ReactiveMongoRepository<Beer, String> {
}
