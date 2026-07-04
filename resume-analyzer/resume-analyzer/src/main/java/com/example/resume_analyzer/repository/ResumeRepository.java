package com.example.resume_analyzer.repository;

import com.example.resume_analyzer.model.ResumeAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<ResumeAnalysis, Long> {
}
