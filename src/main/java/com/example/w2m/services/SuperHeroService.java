package com.example.w2m.services;

import com.example.w2m.entities.SuperHero;

import java.util.List;

public interface SuperHeroService {

    List<SuperHero> getAllSuperHeroes();

    SuperHero getSuperHeroById(Long id);

    List<SuperHero> getSuperHeroesByName(String searchParam);

    void deleteSuperHero(Long id);

    SuperHero saveSuperHero(SuperHero superHero);

    SuperHero updateSuperHero(Long id, SuperHero superHero);

}
