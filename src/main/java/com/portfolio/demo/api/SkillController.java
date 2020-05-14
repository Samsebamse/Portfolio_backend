package com.portfolio.demo.api;

import com.portfolio.demo.model.Skill;
import com.portfolio.demo.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/skills")
public class SkillController {

    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public void addSkill(@Valid @NonNull @RequestBody Skill skill) {
        skillService.addSkill(skill);
    }

    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }

    @GetMapping(path = "{id}")
    public Optional<Skill> getSkillById(@PathVariable("id") UUID id) {
        return skillService.getSkillById(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteSkillById(@PathVariable("id") UUID id) {
        skillService.deleteSkillById(id);
    }

    @PatchMapping(path = "{id}")
    public void updateSkillById(
            @PathVariable("id") UUID id,
            @Valid @NonNull @RequestBody Map<String, String> keyValuePair
    ) throws SQLException {
        skillService.updateSkillById(id, keyValuePair);
    }
}
