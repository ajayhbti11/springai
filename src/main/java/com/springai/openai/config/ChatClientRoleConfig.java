package com.springai.openai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientRoleConfig {

    @Bean
    @Qualifier("roleChatClient")
    public ChatClient roleChatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient.builder(openAiChatModel)
                .defaultSystem("""
                        According to the company's HR policy,
                        employees are eligible for 18 days of paid leave annually.
                        Unused leave can be carried over to the next year.
                        """)
                .defaultUser("How can I help you?")
                .build();
    }
}
