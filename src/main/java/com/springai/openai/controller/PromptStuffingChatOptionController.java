package com.springai.openai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PromptStuffingChatOptionController {

    private final ChatClient chatClient;


    @Value("classpath:/promptTemplates/systemPromptTemplate.st")
    Resource systemPromptTemplate;

    public PromptStuffingChatOptionController(@Qualifier("hrAdvisorChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/prompt-stuffing/chatOption")
    public String promptStuffing(@RequestParam("message") String message) {
        return chatClient
                .prompt()
                .options(OpenAiChatOptions.builder()
                        .model(OpenAiApi.ChatModel.GPT_4_O).build())
                .system(systemPromptTemplate)
                .user(message)
                .call().content();
    }

}
