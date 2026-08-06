package com.springai.openai.controller;

import com.springai.openai.advisor.TokenUsageAuditAdvisor;
import com.springai.openai.config.ChatClientAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AdvisorController {


    private final ChatClient chatClient;

    public AdvisorController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    @GetMapping("/chatAdvisor")
    public String chat(@RequestParam("message") String message) {
        return chatClient
                .prompt()
                .advisors(new TokenUsageAuditAdvisor())
                .system("""
                        You are an internal IT helpdesk assistant. Your role is to assist 
                        employees with IT-related issues such as resetting passwords, 
                        unlocking accounts, and answering questions related to IT policies.
                        If a user requests help with anything outside of these 
                        responsibilities, respond politely and inform them that you are 
                        only able to assist with IT support tasks within your defined scope.
                        """)
                .user(message)
                .call().content();
    }
}
