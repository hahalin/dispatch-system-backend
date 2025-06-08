package com.example.dispatch.service;

import com.example.dispatch.dto.SkillDTO;
import com.example.dispatch.model.Skill;
import com.example.dispatch.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public List<SkillDTO> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public SkillDTO getSkillById(Integer id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Skill not found with id: " + id));
        return convertToDto(skill);
    }

    public SkillDTO createSkill(SkillDTO skillDTO) {
        Skill skill = new Skill();
        skill.setName(skillDTO.getName());
        
        Skill savedSkill = skillRepository.save(skill);
        return convertToDto(savedSkill);
    }

    public SkillDTO updateSkill(Integer id, SkillDTO skillDTO) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Skill not found with id: " + id));
        
        skill.setName(skillDTO.getName());
        
        Skill updatedSkill = skillRepository.save(skill);
        return convertToDto(updatedSkill);
    }

    public void deleteSkill(Integer id) {
        if (!skillRepository.existsById(id)) {
            throw new EntityNotFoundException("Skill not found with id: " + id);
        }
        skillRepository.deleteById(id);
    }

    private SkillDTO convertToDto(Skill skill) {
        SkillDTO dto = new SkillDTO();
        dto.setId(skill.getId());
        dto.setName(skill.getName());
        // 暫時設置為null或預設值，因為SkillDTO中有categoryId和categoryName但Skill模型中沒有對應的關聯
        dto.setCategoryId(null);
        dto.setCategoryName(null);
        return dto;
    }
}
