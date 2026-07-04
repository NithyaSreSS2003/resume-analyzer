package com.example.resume_analyzer.controller;

import com.example.resume_analyzer.model.ResumeAnalysis;
import com.example.resume_analyzer.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @Autowired
    private ResumeRepository resumeRepository;

    @PostMapping("/analyze")
    public ResponseEntity<ResumeAnalysis> analyzeResume(
            @RequestParam("candidateName") String name,
            @RequestParam("resumeText") String resumeText,
            @RequestParam("jobDescription") String jdText) {

        // 1. Core Keyword Matching Logic
        String[] keywords = jdText.toLowerCase().split("\\W+");
        Set<String> uniqueKeywords = new HashSet<>(Arrays.asList(keywords));

        String resumeLower = resumeText.toLowerCase();
        int matchedCount = 0;
        List<String> matchedList = new ArrayList<>();

        for (String word : uniqueKeywords) {
            // Check if the word is a valid keyword and exists in the resume text
            if (word.length() > 2 && resumeLower.contains(word)) {
                matchedCount++;
                matchedList.add(word);
            }
        }

        // 2. Calculate the match percentage
        double score = uniqueKeywords.isEmpty() ? 0 : ((double) matchedCount / uniqueKeywords.size()) * 100;
        String finalScore = String.format("%.2f%%", score);

        // 3. Save the results to our H2 database
        ResumeAnalysis analysis = new ResumeAnalysis();
        analysis.setCandidateName(name);
        analysis.setMatchScore(finalScore);
        analysis.setMatchedKeywords(String.join(", ", matchedList));

        ResumeAnalysis savedAnalysis = resumeRepository.save(analysis);

        return ResponseEntity.ok(savedAnalysis);
    }
}