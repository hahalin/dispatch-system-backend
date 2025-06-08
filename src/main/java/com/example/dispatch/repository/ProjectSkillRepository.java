package com.example.dispatch.repository;

import com.example.dispatch.model.ProjectSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectSkillRepository extends JpaRepository<ProjectSkill, Integer> {  // 改為 Integer
    List<ProjectSkill> findByProject_Id(Integer projectId);  // 改為 Integer
    void deleteByProject_IdAndSkill_Id(Integer projectId, Integer skillId);  // 改為 Integer
    boolean existsByProject_IdAndSkill_Id(Integer projectId, Integer skillId);  // 改為 Integer
}
