package com.dst.lootgenerator.items.services;

import com.dst.lootgenerator.hero.models.entities.Skill;
import com.dst.lootgenerator.items.repositories.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {
    private final SkillRepository skillRepository;

    public Skill saveSkill(Skill skill) {
        return skillRepository.saveAndFlush(skill);
    }

    public List<Skill> saveSkills(List<Skill> skills) {
        return skillRepository.saveAllAndFlush(skills);
    }
}
