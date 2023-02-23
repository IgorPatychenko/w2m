package com.example.w2m.services;

import com.example.w2m.entities.SuperHero;
import com.example.w2m.exceptions.SuperHeroNotFoundException;
import com.example.w2m.repositories.SuperHeroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuperHeroServiceImpl implements SuperHeroService {

    private final SuperHeroRepository superHeroRepository;

    public SuperHeroServiceImpl(SuperHeroRepository superHeroRepository) {
        this.superHeroRepository = superHeroRepository;
    }

    public List<SuperHero> getAllSuperHeroes() {
        return this.superHeroRepository.findAll();
    }

    public SuperHero getSuperHeroById(Long id) {
        return this.superHeroRepository.findById(id).orElseThrow(SuperHeroNotFoundException::new);
    }

    public List<SuperHero> getSuperHeroesByName(String searchParam) {
        return this.superHeroRepository.findAllByNameContaining(searchParam);
    }

    public void deleteSuperHero(Long id) {
        this.superHeroRepository.deleteById(id);
    }

    public SuperHero saveSuperHero(SuperHero superHero) {
        return this.superHeroRepository.save(superHero);
    }

    public SuperHero updateSuperHero(Long id, SuperHero superHero) {
        Optional<SuperHero> superHeroOptional = superHeroRepository.findById(id);
        if(superHeroOptional.isPresent()) {
            superHero.setId(id);
            return this.superHeroRepository.save(superHero);
        }
        throw new SuperHeroNotFoundException();
    }
}
