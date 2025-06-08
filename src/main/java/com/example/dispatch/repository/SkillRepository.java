package com.example.dispatch.repository;

import com.example.dispatch.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {
    
    List<Skill> findByPersonnelId(Integer personnelId);
    
    void deleteByPersonnelId(Integer personnelId);
    
    @Query("SELECT DISTINCT s.name FROM Skill s ORDER BY s.name")
    List<String> findAllSkillNames();
    
    @Query("SELECT s FROM Skill s WHERE s.name = :name ORDER BY s.years DESC, s.proficiency DESC")
    List<Skill> findByNameOrderByExpertise(@Param("name") String name);
}
