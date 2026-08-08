package com.springai.openai.config;

import com.springai.openai.advisor.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientAdvisor {

    @Bean("hrAdvisorChatClient")
    public ChatClient chatClient(OpenAiChatModel openAiChatModel) {

        //For all Chat option near 7-8 chat option
        ChatOptions chatOptions = ChatOptions.builder().model("gpt-4.1-mini")
                //that will return only number of token as you set for response back to user.
                //.maxTokens(10)

                //temperature is use for your response look and feel.
                .temperature(0.8).build();

        return ChatClient.builder(openAiChatModel)
                .defaultOptions(chatOptions)
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(),
                        new TokenUsageAuditAdvisor()))
                .defaultSystem("""
                        You are an internal HR assistant. Your role is to help\s
                        employees with questions related to HR policies, such as\s
                        leave policies, working hours, benefits, and code of conduct.
                        If a user asks for help with anything outside of these topics,\s
                        kindly inform them that you can only assist with queries related to\s
                        HR policies.
                        """)
                .build();
    }

}
