package com.springai.openai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ChatClientConfig {

    @Bean
    @Primary
    @Qualifier("openAiChatClient")
    public ChatClient openAiChatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient.builder(openAiChatModel).build();
    }

    @Bean
    @Qualifier("ollamaChatClient")
    public ChatClient ollamaAiChatClient(OllamaChatModel ollamaChatModel) {
        return ChatClient.builder(ollamaChatModel).build();
    }
}
