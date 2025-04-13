package com.dst.lootgenerator.items.repositories;

import com.dst.lootgenerator.hero.models.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository <Skill, Long>{
}
