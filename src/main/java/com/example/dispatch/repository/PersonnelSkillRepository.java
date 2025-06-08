package com.example.dispatch.repository;

import com.example.dispatch.model.PersonnelSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonnelSkillRepository extends JpaRepository<PersonnelSkill, Integer> {  // 改為 Integer
    List<PersonnelSkill> findByPersonnel_Id(Integer personnelId);  // 改為 Integer
    void deleteByPersonnel_IdAndSkill_Id(Integer personnelId, Integer skillId);  // 改為 Integer
    boolean existsByPersonnel_IdAndSkill_Id(Integer personnelId, Integer skillId);  // 改為 Integer
}
