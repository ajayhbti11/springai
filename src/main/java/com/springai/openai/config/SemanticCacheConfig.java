package com.springai.openai.config;

import io.qdrant.client.QdrantClient;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.qdrant.QdrantVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SemanticCacheConfig {

    @Bean("cacheVectorStore")
    VectorStore cacheVectorStore(QdrantClient qdrantClient, EmbeddingModel embeddingModel) {
        return QdrantVectorStore.builder(qdrantClient, embeddingModel)
                .collectionName("semantic-cache")
                .initializeSchema(true)
                .build();
    }

    // SemanticCache, DefaultSemanticCache, SemanticCacheAdvisor are NOT available
    // in Spring AI 1.0.0. The artifact spring-ai-redis-semantic-cache does not exist
    // as a published dependency. These beans are disabled until the feature is available.

}
