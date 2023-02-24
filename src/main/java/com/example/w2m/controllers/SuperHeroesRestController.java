package com.example.w2m.controllers;

import com.example.w2m.annotations.ExecutionTime;
import com.example.w2m.dtos.SuperHeroDTO;
import com.example.w2m.entities.SuperHero;
import com.example.w2m.mappers.SuperHeroMapper;
import com.example.w2m.services.SuperHeroService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/superheroes")
@Cacheable("superHeroesCache")
public class SuperHeroesRestController {

    private final SuperHeroService superHeroService;

    private final SuperHeroMapper superHeroMapper;

    public SuperHeroesRestController(SuperHeroService superHeroService, SuperHeroMapper superHeroMapper) {
        this.superHeroService = superHeroService;
        this.superHeroMapper = superHeroMapper;
    }

    @ExecutionTime
    @GetMapping(name = "getAllSuperHeroes", value = "/")
    public Flux<SuperHeroDTO> getAllSuperHeroes() {
        return Flux.fromIterable(superHeroMapper.toDtos(superHeroService.getAllSuperHeroes()));
    }

    @GetMapping(name = "getSuperHeroById", value = "/{id}")
    public Mono<SuperHeroDTO> getSuperHeroById(@PathVariable Long id) {
        return Mono.just(superHeroMapper.toDto(superHeroService.getSuperHeroById(id)));
    }

    @GetMapping(name = "searchByNameContains", value = "/search/{searchParam}")
    public Flux<SuperHeroDTO> searchByNameContains(@PathVariable String searchParam) {
        return Flux.fromIterable(superHeroMapper.toDtos(superHeroService.getSuperHeroesByName(searchParam)));
    }

    @ExecutionTime
    @PostMapping(name = "createSuperHero", value = "/")
    public Mono<SuperHero> createSuperHero(@RequestBody SuperHero superhero) {
        return Mono.just(superHeroService.saveSuperHero(superhero));
    }

    @CachePut(value = "superHeroesCache", key = "#id")
    @PutMapping(name = "updateSuperHero", value = "/{id}")
    public Mono<SuperHeroDTO> updateSuperHero(@PathVariable Long id, @RequestBody SuperHeroDTO superHeroDTO) {
        SuperHero superHero = superHeroService.updateSuperHero(id, superHeroMapper.toEntity(superHeroDTO));
        return Mono.just(superHeroMapper.toDto(superHero));
    }

    @ExecutionTime
    @CacheEvict(value = "superHeroesCache", key = "#id")
    @DeleteMapping(name = "deleteSuperHero", value = "/{id}")
    public Mono<Void> deleteSuperHero(@PathVariable Long id) {
        superHeroService.deleteSuperHero(id);
        return Mono.empty();
    }

}
