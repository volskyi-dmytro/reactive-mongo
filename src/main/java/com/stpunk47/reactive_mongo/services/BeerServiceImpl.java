package com.stpunk47.reactive_mongo.services;

import com.stpunk47.reactive_mongo.model.BeerDTO;
import reactor.core.publisher.Mono;

public class BeerServiceImpl implements BeerService {
    @Override
    public Mono<BeerDTO> saveNewBeer(BeerDTO beerDTO) {
        return null;
    }

    @Override
    public Mono<BeerDTO> getBeerById(String beerId) {
        return null;
    }
}
