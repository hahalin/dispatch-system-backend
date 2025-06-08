package com.example.dispatch.repository;

import com.example.dispatch.model.PersonnelProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonnelProjectRepository extends JpaRepository<PersonnelProject, Long> {
    List<PersonnelProject> findByPersonnel_Id(Long personnelId);
    List<PersonnelProject> findByProject_Id(Long projectId);
    void deleteByPersonnel_IdAndProject_Id(Long personnelId, Long projectId);
    boolean existsByPersonnel_IdAndProject_Id(Long personnelId, Long projectId);
}
