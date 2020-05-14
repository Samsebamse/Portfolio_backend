package com.portfolio.demo.dao;

import com.portfolio.demo.model.Skill;
import com.portfolio.demo.util.HelperFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("skill")
public class SkillDaoAccessService implements SkillDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SkillDaoAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addSkill(UUID id, Skill skill) {
        final String sql = "INSERT INTO skill (skillid, name, progress) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, id, skill.getName(), skill.getProgress());
    }

    @Override
    public List<Skill> getAllSkills() {
        final String sql = "SELECT * FROM skill";
        List<Skill> allSkills = jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID skillId = UUID.fromString(resultSet.getString("skillid"));
            String name = resultSet.getString("name");
            int progress = resultSet.getInt("progress");
            return new Skill(skillId, name, progress);
        });
        return allSkills;
    }

    @Override
    public Optional<Skill> getSkillById(UUID skillId) {
        final String sql = "SELECT * FROM skill WHERE skillid = ?";
        Skill skill = jdbcTemplate.queryForObject(sql, new Object[]{skillId}, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("skillid"));
            String name = resultSet.getString("name");
            int progress = resultSet.getInt("progress");
            return new Skill(id, name, progress);
        });

        return Optional.ofNullable(skill);
    }

    @Override
    public int deleteSkillById(UUID skillId) {
        final String sql = "DELETE FROM skill WHERE skillid = ?";
        return jdbcTemplate.update(sql, skillId);
    }

    @Override
    public int updateSkillById(UUID skillId, Map<String, String> keyValuePair) {
        StringJoiner stringJoiner = new StringJoiner("", "UPDATE skill SET ", " WHERE skillid = ?");
        final String sql =  stringJoiner.add(HelperFunctions.createPATCHsql(keyValuePair)).toString();
        return jdbcTemplate.update(sql, skillId);
    }
}
