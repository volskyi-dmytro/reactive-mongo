package com.stpunk47.reactive_mongo.mappers;

import com.stpunk47.reactive_mongo.domain.Beer;
import com.stpunk47.reactive_mongo.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    BeerDTO beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDTO beerDTO);
}
