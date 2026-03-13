package com.interviewai.consultant.service;

import com.interviewai.consultant.mapper.InterviewMapper;
import com.interviewai.consultant.pojo.InterviewRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("interviewBusinessService")
public class interviewBusinessService {
    @Autowired
    private InterviewMapper interviewMapper;

    public void insert(InterviewRecord record) {
        interviewMapper.insert(record);
    }

    public InterviewRecord findByPhone(String phone) {
        return interviewMapper.findByPhone(phone);
    }

    public void updateEvaluation(InterviewRecord record) {
        interviewMapper.updateEvaluation(record);
    }
}
