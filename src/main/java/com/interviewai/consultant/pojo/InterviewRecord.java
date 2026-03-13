package com.interviewai.consultant.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterviewRecord {
    private Long id;
    private String candidateName;
    private String gender;
    private String phone;
    private LocalDateTime interviewTime;
    private String targetPosition;
    private String experience;
    private String techStack;
    private Integer expectedSalary;
    private String evaluation;
    private Integer score;
}
