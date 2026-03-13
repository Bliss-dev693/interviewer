package com.interviewai.consultant.mapper;

import com.interviewai.consultant.pojo.InterviewRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface InterviewMapper {

    //添加面试记录
    @Insert("insert into interview_record(candidate_name,gender,phone,interview_time,target_position,experience,tech_stack,expected_salary,evaluation,score) " +
            "values(#{candidateName},#{gender},#{phone},#{interviewTime},#{targetPosition},#{experience},#{techStack},#{expectedSalary},#{evaluation},#{score})")
    void insert(InterviewRecord record);

    //根据手机号查询面试记录
    @Select("select * from interview_record where phone=#{phone}")
    InterviewRecord findByPhone(String phone);

    //更新面试评估
    @Update("update interview_record set evaluation=#{evaluation},score=#{score} where id=#{id}")
    void updateEvaluation(InterviewRecord record);

}
