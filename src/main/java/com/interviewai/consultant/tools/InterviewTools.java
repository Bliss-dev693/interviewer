package com.interviewai.consultant.tools;

import com.interviewai.consultant.pojo.InterviewRecord;
import com.interviewai.consultant.service.interviewBusinessService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InterviewTools {
    @Autowired
    private interviewBusinessService interviewBusinessService;

    @Tool("开始面试，记录候选人基本信息")
    public void startInterview(
            @P("候选人姓名") String name,
            @P("性别") String gender,
            @P("手机号") String phone,
            @P("应聘岗位") String position,
            @P("工作年限") String experience,
            @P("技术栈") String techStack,
            @P("期望薪资") Integer expectedSalary
    ){
        InterviewRecord record = new InterviewRecord(
            null, name, gender, phone,
            LocalDateTime.now(), position, experience, techStack, expectedSalary, null, null
        );
        interviewBusinessService.insert(record);
    }

    @Tool("根据手机号查询面试记录")
    public InterviewRecord findInterview(@P("手机号") String phone){
        return interviewBusinessService.findByPhone(phone);
    }

    @Tool("保存面试评估结果")
    public void saveEvaluation(
            @P("记录 ID") Long id,
            @P("面试评估") String evaluation,
            @P("面试评分 (0-100)") Integer score
    ){
        InterviewRecord record = new InterviewRecord();
        record.setId(id);
        record.setEvaluation(evaluation);
        record.setScore(score);
        interviewBusinessService.updateEvaluation(record);
    }
}
