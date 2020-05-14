package com.portfolio.demo.dao;

import com.portfolio.demo.model.Skill;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface SkillDao {

    int addSkill(UUID id, Skill skill);

    default int addSkill(Skill skill) {
        UUID id = UUID.randomUUID();
        return addSkill(id, skill);
    }

    List<Skill> getAllSkills();

    Optional<Skill> getSkillById(UUID id);

    int deleteSkillById(UUID id);

    int updateSkillById(UUID id, Map<String, String> keyValuePair);
}
