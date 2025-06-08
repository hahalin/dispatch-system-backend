package com.example.dispatch.repository;

import com.example.dispatch.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, Integer> {
    
    List<Education> findByPersonnelId(Integer personnelId);
    
    void deleteByPersonnelId(Integer personnelId);
} 