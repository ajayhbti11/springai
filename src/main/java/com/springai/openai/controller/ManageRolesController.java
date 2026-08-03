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

    public ManageRolesController(@Qualifier("roleChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/rolechat")
    public String chat(@RequestParam("message") String message) {
        return chatClient.prompt(message)
                //.user(message)
                .call()
                .content();
    }

}
