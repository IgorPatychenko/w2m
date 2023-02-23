package com.example.w2m;

import com.example.w2m.dtos.SuperHeroDTO;
import com.example.w2m.entities.SuperHero;
import com.example.w2m.mappers.SuperHeroMapper;
import com.example.w2m.services.SuperHeroService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SuperHeroIntegrationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private SuperHeroService superHeroService;

    @Autowired
    private SuperHeroMapper superHeroMapper;

    @Test
    @Order(1)
    public void createSuperHeroTest() {
        SuperHero superHero = new SuperHero("Batman", "Rich");
        webTestClient.post().uri("/api/superheroes/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(superHero)
                .exchange()
                .expectStatus().isOk()
                .expectBody(SuperHero.class)
                .consumeWith(response -> {
                    SuperHero superHeroResponse = response.getResponseBody();
                    assertNotNull(superHeroResponse);
                    assertNotNull(superHeroResponse.getId());
                    assertEquals("Batman", superHeroResponse.getName());
                    assertEquals("Rich", superHeroResponse.getSuperPower());
                });
    }

    @Test
    @Order(2)
    public void getAllSuperHeroesTest() {
        List<SuperHero> superHeroes = superHeroService.getAllSuperHeroes();
        webTestClient.get().uri("/api/superheroes/")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(SuperHeroDTO.class)
                .isEqualTo(superHeroMapper.toDtos(superHeroes));
    }

    @Test
    @Order(3)
    public void getSuperHeroByIdTest() {
        SuperHero superHero = superHeroService.getAllSuperHeroes().get(0);
        webTestClient.get().uri("/api/superheroes/{id}", superHero.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(SuperHeroDTO.class)
                .isEqualTo(superHeroMapper.toDto(superHero));
    }

    @Test
    @Order(4)
    public void searchByNameContainsTest() {
        List<SuperHero> superHeroes = superHeroService.getSuperHeroesByName("man");
        webTestClient.get().uri("/api/superheroes/search/{searchParam}", "man")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(SuperHeroDTO.class)
                .isEqualTo(superHeroMapper.toDtos(superHeroes));
    }

    @Test
    @Order(5)
    public void updateSuperHeroTest() {
        SuperHero superHero = superHeroService.getAllSuperHeroes().get(0);
        SuperHeroDTO superHeroDTO = superHeroMapper.toDto(superHero);
        superHeroDTO.setName("New Name");
        webTestClient.put().uri("/api/superheroes/{id}", superHeroDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(superHeroDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody(SuperHeroDTO.class)
                .isEqualTo(superHeroDTO);
    }

    @Test
    @Order(6)
    public void deleteSuperHeroTest() {
        SuperHero superHero = superHeroService.getAllSuperHeroes().get(0);
        webTestClient.delete().uri("/api/superheroes/{id}", superHero.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();
    }

}
