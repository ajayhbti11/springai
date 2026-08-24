package com.springai.openai;

import org.springframework.ai.model.ollama.autoconfigure.OllamaEmbeddingAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {OllamaEmbeddingAutoConfiguration.class})
public class OpenaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenaiApplication.class, args);
	}

}
