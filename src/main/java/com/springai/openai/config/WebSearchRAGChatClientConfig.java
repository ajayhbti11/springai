package com.springai.openai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class WebSearchRAGChatClientConfig {

    @Bean("webSearchRAGChatClient")
    public ChatClient webSearchRAGChatClient(OpenAiChatModel openAiChatModel, ChatMemory chatMemory) {
        Advisor loggerAdvisor = new SimpleLoggerAdvisor();
        Advisor memoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        return ChatClient.builder(openAiChatModel)
                .defaultAdvisors(List.of(loggerAdvisor, memoryAdvisor))
                .defaultSystem("You are a helpful assistant that can search the web and answer questions accurately.")
                .build();
    }

}
