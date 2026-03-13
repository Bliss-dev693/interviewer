package com.interviewai.consultant.controller;



import com.interviewai.consultant.aiservice.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class InterviewController {
    @Autowired
    private InterviewService interviewService;

    @RequestMapping(value = "/interview", produces = "text/html;charset=utf-8")
    public Flux<String> interview(String memoryId, String message){
        Flux<String> result = interviewService.chat(memoryId, message);
        return result;
    }
}
