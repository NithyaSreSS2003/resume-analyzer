package com.example.resume_analyzer.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "analyses")
@Data
public class ResumeAnalysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String candidateName;
    private String matchScore;

    @Lob
    private String matchedKeywords;
}
