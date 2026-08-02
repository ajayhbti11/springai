package com.springai.openai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ManageRolesController {


    private final ChatClient chatClient;

    public ManageRolesController(@Qualifier("ollamaChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/rolechat")
    public String chat(@RequestParam("message") String message) {
        return chatClient.prompt()

                .system("""
                                According to the company's HR policy, 
                                employees are eligible for 18 days of paid leave annually.
                                 Unused leave can be carried over to the next year.
                                """)

                .user(message)
        .call().content();
    }

}
