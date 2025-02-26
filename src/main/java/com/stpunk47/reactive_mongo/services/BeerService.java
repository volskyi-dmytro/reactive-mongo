package com.stpunk47.reactive_mongo.services;

import com.stpunk47.reactive_mongo.model.BeerDTO;
import reactor.core.publisher.Mono;

public interface BeerService {

    Mono<BeerDTO> saveBeer(Mono<BeerDTO> beerDTO);

    Mono<BeerDTO> getBeerById(String beerId);

}
