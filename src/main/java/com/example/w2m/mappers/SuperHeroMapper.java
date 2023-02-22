package com.example.w2m.mappers;

import com.example.w2m.dtos.SuperHeroDTO;
import com.example.w2m.entities.SuperHero;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SuperHeroMapper {

    @Named("toDto")
    SuperHeroDTO toDto(SuperHero entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<SuperHeroDTO> toDtos(List<SuperHero> entities);

    SuperHero toEntity(SuperHeroDTO superHeroDTO);
}
