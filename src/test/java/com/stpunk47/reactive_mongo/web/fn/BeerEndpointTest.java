package com.stpunk47.reactive_mongo.web.fn;

import com.stpunk47.reactive_mongo.model.BeerDTO;
import com.stpunk47.reactive_mongo.services.BeerServiceImplTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockOAuth2Login;

@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class BeerEndpointTest {

    @Container
    @ServiceConnection
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @Autowired
    WebTestClient webTestClient;

    @Test
    @Order(2)
    void testListBeers() {
        webTestClient
                .mutateWith(mockOAuth2Login())
                .get().uri(BeerRouterConfig.BEER_PATH)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-type", "application/json")
                .expectBody()
                .jsonPath("$.length()").isNumber()
                .jsonPath("$.length()").value(greaterThan(1));
    }

    @Test
    void testCreateBeer() {

        BeerDTO testDto = getSavedTestBeer();

        webTestClient
                .mutateWith(mockOAuth2Login())
                .post().uri(BeerRouterConfig.BEER_PATH)
                .body(Mono.just(testDto), BeerDTO.class)
                .header("Content-Type", "application/json")
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().exists("location");
    }

    public BeerDTO getSavedTestBeer(){
        FluxExchangeResult<BeerDTO> beerDTOFluxExchangeResult = webTestClient
                .mutateWith(mockOAuth2Login())
                .post().uri(BeerRouterConfig.BEER_PATH)
                .body(Mono.just(BeerServiceImplTest.getTestBeer()), BeerDTO.class)
                .header("Content-Type", "application/json")
                .exchange()
                .returnResult(BeerDTO.class);

        List<String> location = beerDTOFluxExchangeResult.getResponseHeaders().get("Location");

        return webTestClient
                .mutateWith(mockOAuth2Login())
                .get().uri(BeerRouterConfig.BEER_PATH)
                .exchange().returnResult(BeerDTO.class).getResponseBody().blockFirst();
    }
}
