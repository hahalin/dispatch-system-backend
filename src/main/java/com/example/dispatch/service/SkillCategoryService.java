package com.example.dispatch.service;

import com.example.dispatch.dto.SkillCategoryDTO;
import com.example.dispatch.model.SkillCategory;
import com.example.dispatch.repository.SkillCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillCategoryService {

    @Autowired
    private SkillCategoryRepository skillCategoryRepository;

    public List<SkillCategoryDTO> getAllSkillCategories() {
        return skillCategoryRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public SkillCategoryDTO getSkillCategoryById(Integer id) {  // 改為 Integer
        SkillCategory skillCategory = skillCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Skill category not found with id: " + id));
        return convertToDto(skillCategory);
    }

    public SkillCategoryDTO createSkillCategory(SkillCategoryDTO skillCategoryDTO) {
        if (skillCategoryRepository.existsByName(skillCategoryDTO.getName())) {
            throw new RuntimeException("Skill category with this name already exists");
        }
        
        SkillCategory skillCategory = new SkillCategory();
        skillCategory.setName(skillCategoryDTO.getName());
        
        SkillCategory savedCategory = skillCategoryRepository.save(skillCategory);
        return convertToDto(savedCategory);
    }

    public SkillCategoryDTO updateSkillCategory(Integer id, SkillCategoryDTO skillCategoryDTO) {  // 改為 Integer
        SkillCategory skillCategory = skillCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Skill category not found with id: " + id));
        
        if (!skillCategory.getName().equals(skillCategoryDTO.getName()) && 
                skillCategoryRepository.existsByName(skillCategoryDTO.getName())) {
            throw new RuntimeException("Skill category with this name already exists");
        }
        
        skillCategory.setName(skillCategoryDTO.getName());
        
        SkillCategory updatedCategory = skillCategoryRepository.save(skillCategory);
        return convertToDto(updatedCategory);
    }

    public void deleteSkillCategory(Integer id) {  // 改為 Integer
        if (!skillCategoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Skill category not found with id: " + id);
        }
        skillCategoryRepository.deleteById(id);
    }

    private SkillCategoryDTO convertToDto(SkillCategory skillCategory) {
        SkillCategoryDTO dto = new SkillCategoryDTO();
        dto.setId(skillCategory.getId());
        dto.setName(skillCategory.getName());
        return dto;
    }
}
