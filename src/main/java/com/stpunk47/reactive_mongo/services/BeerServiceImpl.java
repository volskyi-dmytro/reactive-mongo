package com.stpunk47.reactive_mongo.services;

import com.stpunk47.reactive_mongo.mappers.BeerMapper;
import com.stpunk47.reactive_mongo.model.BeerDTO;
import com.stpunk47.reactive_mongo.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    @Override
    public Mono<BeerDTO> saveBeer(Mono<BeerDTO> beerDto) {
        return beerDto.map(beerMapper::beerDtoToBeer)
                .flatMap(beerRepository::save)
                .map(beerMapper::beerToBeerDto);
    }

    @Override
    public Mono<BeerDTO> getBeerById(String beerId) {
        return null;
    }
}
