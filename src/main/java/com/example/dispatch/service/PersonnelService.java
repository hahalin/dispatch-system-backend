package com.example.dispatch.service;

import com.example.dispatch.dto.PersonnelDTO;
import com.example.dispatch.dto.PersonnelSkillDTO;
import com.example.dispatch.exception.ResourceNotFoundException;
import com.example.dispatch.model.AppUser;
import com.example.dispatch.model.Personnel;
import com.example.dispatch.model.Skill;
import com.example.dispatch.repository.PersonnelRepository;
import com.example.dispatch.repository.SkillRepository;
import com.example.dispatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PersonnelService {

    @Autowired
    private PersonnelRepository personnelRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private UserRepository userRepository;

    public List<PersonnelDTO> getAllPersonnel() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Personnel> personnelList;
        if ("ADMIN".equals(user.getRole())) {
            personnelList = personnelRepository.findAll();
        } else {
            // 暫時返回所有人員，後續可以加入權限控制
            personnelList = personnelRepository.findAll();
        }

        return personnelList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PersonnelDTO getPersonnelById(Integer id) {
        Personnel personnel = personnelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personnel not found with id: " + id));

        return convertToDTO(personnel);
    }
    
    public List<PersonnelDTO> getPersonnelByStatus(String status) {
        List<Personnel> personnelList = personnelRepository.findByStatus(status);
        return personnelList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<PersonnelDTO> searchPersonnel(String keyword) {
        List<Personnel> personnelList = personnelRepository.findByNameContainingOrEnglishNameContaining(keyword, keyword);
        return personnelList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public Map<String, Object> getPersonnelStats() {
        Object[] stats = personnelRepository.getPersonnelStatistics();
        Map<String, Object> result = new HashMap<>();
        
        if (stats != null && stats.length >= 4) {
            result.put("totalCount", stats[0]);
            result.put("availableCount", stats[1]);
            result.put("dispatchedCount", stats[2]);
            result.put("interviewingCount", stats[3]);
        } else {
            result.put("totalCount", 0L);
            result.put("availableCount", 0L);
            result.put("dispatchedCount", 0L);
            result.put("interviewingCount", 0L);
        }
        
        return result;
    }

    @Transactional
    public PersonnelDTO createPersonnel(PersonnelDTO personnelDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Personnel personnel = new Personnel();
        updatePersonnelFromDTO(personnel, personnelDTO);

        Personnel savedPersonnel = personnelRepository.save(personnel);

        return convertToDTO(savedPersonnel);
    }

    @Transactional
    public PersonnelDTO updatePersonnel(Integer id, PersonnelDTO personnelDTO) {
        Personnel personnel = personnelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personnel not found with id: " + id));

        updatePersonnelFromDTO(personnel, personnelDTO);

        Personnel updatedPersonnel = personnelRepository.save(personnel);

        return convertToDTO(updatedPersonnel);
    }

    @Transactional
    public void deletePersonnel(Integer id) {
        Personnel personnel = personnelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personnel not found with id: " + id));

        personnelRepository.delete(personnel);
    }
    
    private void updatePersonnelFromDTO(Personnel personnel, PersonnelDTO dto) {
        personnel.setName(dto.getName());
        personnel.setEnglishName(dto.getEnglishName());
        personnel.setGender(dto.getGender());
        personnel.setBirthYear(dto.getBirthYear());
        personnel.setAge(dto.getAge());
        personnel.setPhone(dto.getPhone());
        personnel.setEmail(dto.getEmail());
        personnel.setStatus(dto.getStatus());
        personnel.setCurrentClient(dto.getCurrentClient());
        personnel.setCurrentPosition(dto.getCurrentPosition());
        personnel.setCurrentSalary(dto.getCurrentSalary());
        personnel.setExpectedSalary(dto.getExpectedSalary());
        personnel.setIntroduction(dto.getIntroduction());
        personnel.setNotes(dto.getNotes());
        personnel.setJoinDate(dto.getJoinDate());
        personnel.setLastDispatchDate(dto.getLastDispatchDate());
        personnel.setDispatchCount(dto.getDispatchCount());
        personnel.setSuccessRate(dto.getSuccessRate());
    }

    private PersonnelDTO convertToDTO(Personnel personnel) {
        PersonnelDTO dto = new PersonnelDTO();
        dto.setId(personnel.getId());
        dto.setName(personnel.getName());
        dto.setEnglishName(personnel.getEnglishName());
        dto.setGender(personnel.getGender());
        dto.setBirthYear(personnel.getBirthYear());
        dto.setAge(personnel.getAge());
        dto.setPhone(personnel.getPhone());
        dto.setEmail(personnel.getEmail());
        dto.setStatus(personnel.getStatus());
        dto.setCurrentClient(personnel.getCurrentClient());
        dto.setCurrentPosition(personnel.getCurrentPosition());
        dto.setCurrentSalary(personnel.getCurrentSalary());
        dto.setExpectedSalary(personnel.getExpectedSalary());
        dto.setIntroduction(personnel.getIntroduction());
        dto.setNotes(personnel.getNotes());
        dto.setJoinDate(personnel.getJoinDate());
        dto.setLastDispatchDate(personnel.getLastDispatchDate());
        dto.setDispatchCount(personnel.getDispatchCount());
        dto.setSuccessRate(personnel.getSuccessRate());

        // 處理技能關聯 - 安全處理 lazy loading
        List<PersonnelSkillDTO> skillDTOs = new ArrayList<>();
        try {
            if (personnel.getSkills() != null) {
                skillDTOs = personnel.getSkills().stream()
                        .map(this::convertSkillToDTO)
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            // 如果技能載入失敗，設為空列表
            skillDTOs = new ArrayList<>();
        }
        dto.setSkills(skillDTOs);

        return dto;
    }

    private PersonnelSkillDTO convertSkillToDTO(Skill skill) {
        PersonnelSkillDTO dto = new PersonnelSkillDTO();
        dto.setId(skill.getId());
        dto.setPersonnelId(skill.getPersonnel().getId());
        dto.setSkillName(skill.getName());
        dto.setYearsOfExperience(skill.getYears().doubleValue());
        dto.setProficiency(skill.getProficiency());
        dto.setDescription(skill.getDescription());
        return dto;
    }
}
