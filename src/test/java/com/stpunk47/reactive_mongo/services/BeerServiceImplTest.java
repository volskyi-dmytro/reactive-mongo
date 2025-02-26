package com.stpunk47.reactive_mongo.services;

import com.stpunk47.reactive_mongo.domain.Beer;
import com.stpunk47.reactive_mongo.mappers.BeerMapper;
import com.stpunk47.reactive_mongo.model.BeerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerServiceImplTest {

    @Autowired
    BeerService beerService;

    @Autowired
    BeerMapper beerMapper;

    BeerDTO beerDTO;

    @BeforeEach
    void setUp() {
        beerDTO = beerMapper.beerToBeerDto(getTestBeer());
    }

    @Test
    void saveBeer() throws InterruptedException {

        Mono<BeerDTO> savedMono = beerService.saveBeer(Mono.just(beerDTO));

        savedMono.subscribe(savedDto -> {
            System.out.println(savedDto.getId());
        });


        Thread.sleep(1000L);
    }

    public static Beer getTestBeer() {
        return Beer.builder()
                .beerName("Pavlivske")
                .beerStyle("Lager")
                .price(BigDecimal.TEN)
                .quantityOnHand(22)
                .upc("123123")
                .build();
    }
}