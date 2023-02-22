package com.example.w2m;

import com.example.w2m.entities.SuperHero;
import com.example.w2m.repositories.SuperHeroRepository;
import com.example.w2m.services.SuperHeroServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SuperHeroServiceTest {

    @Mock
    private SuperHeroRepository superheroRepository;

    @InjectMocks
    private SuperHeroServiceImpl superheroService;

    @Test
    public void shouldReturnAllSuperHeroes() {
        List<SuperHero> superheroes = Arrays.asList(
            new SuperHero(1L, "Superman", "Super strength"),
            new SuperHero(2L, "Batman", "Rich"),
            new SuperHero(3L, "Spiderman", "Acrobat")
        );

        when(superheroRepository.findAll()).thenReturn(superheroes);

        List<SuperHero> result = superheroService.getAllSuperHeroes();

        assertEquals(3, result.size());
        assertEquals("Superman", result.get(0).getName());
        assertEquals("Batman", result.get(1).getName());
        assertEquals("Spiderman", result.get(2).getName());
    }

    @Test
    public void shouldReturnSuperHeroById() {
        SuperHero SuperHero = new SuperHero(1L, "Superman", "Super strength");
        when(superheroRepository.findById(1L)).thenReturn(Optional.of(SuperHero));

        SuperHero result = superheroService.getSuperHeroById(1L);

        assertNotNull(result);
        assertEquals("Superman", result.getName());
    }

    @Test
    public void shouldReturnSuperHeroesByName() {
        List<SuperHero> superheroes = Arrays.asList(
            new SuperHero(1L, "Superman", "Super strength"),
            new SuperHero(2L, "Spiderman", "Acrobat"),
            new SuperHero(3L, "Manolito el fuerte", "Drinking beer")
        );
        when(superheroRepository.findAllByNameContaining("man")).thenReturn(superheroes);

        List<SuperHero> result = superheroService.getSuperHeroesByName("man");

        assertEquals(3, result.size());
        assertEquals("Superman", result.get(0).getName());
        assertEquals("Spiderman", result.get(1).getName());
        assertEquals("Manolito el fuerte", result.get(2).getName());
    }

    @Test
    public void shouldSaveSuperHero() {
        SuperHero superHero = new SuperHero(1L, "Superman", "Super strength");

        superheroService.saveSuperHero(superHero);
        verify(superheroRepository, times(1)).save(superHero);
    }

    @Test
    public void shouldUpdateSuperHero() {
        SuperHero superHero = new SuperHero(1L, "Superman", "Super strength");
        SuperHero updatedSuperHero = new SuperHero(1L, "Batman", "Rich");
        when(superheroRepository.findById(1L)).thenReturn(Optional.of(superHero));
        when(superheroRepository.save(updatedSuperHero)).thenReturn(updatedSuperHero);

        SuperHero result = superheroService.updateSuperHero(1L, updatedSuperHero);

        assertNotNull(result);
        assertEquals("Batman", result.getName());
    }

    @Test
    public void shouldDeleteSuperHero() {
        superheroService.deleteSuperHero(1L);
        verify(superheroRepository, times(1)).deleteById(1L);
    }
}
