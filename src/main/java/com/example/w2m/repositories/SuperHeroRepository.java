package com.example.w2m.repositories;

import com.example.w2m.entities.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperHeroRepository extends JpaRepository<SuperHero, Long> {

    List<SuperHero> findAllByNameContaining(String searchParam);

}
