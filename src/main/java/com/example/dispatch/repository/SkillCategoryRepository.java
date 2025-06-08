package com.example.dispatch.repository;

import com.example.dispatch.model.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillCategoryRepository extends JpaRepository<SkillCategory, Integer> {  // 改為 Integer
    boolean existsByName(String name);
}
