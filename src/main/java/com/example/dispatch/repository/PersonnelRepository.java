package com.example.dispatch.repository;

import com.example.dispatch.model.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Integer> {
    
    List<Personnel> findByNameContainingIgnoreCase(String name);
    
    // 基本查詢方法
    List<Personnel> findByStatus(String status);
    
    Long countByStatus(String status);
    
    List<Personnel> findByNameContainingOrEnglishNameContaining(String name, String englishName);
    
    @Query("SELECT p FROM Personnel p WHERE " +
           "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.englishName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.currentClient) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.currentPosition) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Personnel> searchByKeyword(@Param("keyword") String keyword);
    
    @Query("SELECT p FROM Personnel p WHERE " +
           "p.currentSalary >= :minSalary AND p.currentSalary <= :maxSalary")
    List<Personnel> findBySalaryRange(@Param("minSalary") Integer minSalary, 
                                     @Param("maxSalary") Integer maxSalary);
    
    List<Personnel> findByCurrentClient(String currentClient);
    
    @Query("SELECT p FROM Personnel p WHERE p.status = '可派遣'")
    List<Personnel> findAvailablePersonnel();
    
    @Query("SELECT " +
           "COUNT(p) as totalCount, " +
           "SUM(CASE WHEN p.status = '可派遣' THEN 1 ELSE 0 END) as availableCount, " +
           "SUM(CASE WHEN p.status = '已派遣' THEN 1 ELSE 0 END) as dispatchedCount, " +
           "SUM(CASE WHEN p.status = '面談中' THEN 1 ELSE 0 END) as interviewingCount " +
           "FROM Personnel p")
    Object[] getPersonnelStatistics();
    
    @Query("SELECT p FROM Personnel p " +
           "LEFT JOIN FETCH p.educations " +
           "LEFT JOIN FETCH p.skills " +
           "LEFT JOIN FETCH p.languages " +
           "LEFT JOIN FETCH p.industryExperiences " +
           "LEFT JOIN FETCH p.projectExperiences " +
           "WHERE p.id = :id")
    Optional<Personnel> findByIdWithDetails(@Param("id") Integer id);
}
