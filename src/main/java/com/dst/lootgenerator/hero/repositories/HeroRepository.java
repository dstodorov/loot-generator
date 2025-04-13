package com.dst.lootgenerator.hero.repositories;

import com.dst.lootgenerator.hero.models.entities.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {

}
