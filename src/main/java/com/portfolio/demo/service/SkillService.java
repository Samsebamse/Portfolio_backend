package com.portfolio.demo.service;

import com.portfolio.demo.dao.SkillDao;
import com.portfolio.demo.model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class SkillService {

    private final SkillDao skillDao;

    @Autowired
    public SkillService(@Qualifier("skill") SkillDao skillDao) {
        this.skillDao = skillDao;
    }

    public int addSkill(Skill skill) {
        return skillDao.addSkill(skill);
    }

    public List<Skill> getAllSkills() {
        return skillDao.getAllSkills();
    }

    public Optional<Skill> getSkillById(UUID id) {
        return skillDao.getSkillById(id);
    }

    public int deleteSkillById(UUID id) {
        return skillDao.deleteSkillById(id);
    }

    public int updateSkillById(UUID id, Map<String, String> keyValuePair) {
        return skillDao.updateSkillById(id, keyValuePair);
    }
}
