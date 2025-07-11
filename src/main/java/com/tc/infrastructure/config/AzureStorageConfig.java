package com.tc.infrastructure.config;

import com.azure.storage.queue.QueueAsyncClient;
import com.azure.storage.queue.QueueClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureStorageConfig {

    @Value("${azure.queue.name}")
    private String azureQueueName;

    @Value("${azure.queue.connection-string}")
    private String azureQueueConnectionString;

    @Bean
    public QueueAsyncClient queueAsyncClient() {
        return new QueueClientBuilder()
                .connectionString(azureQueueConnectionString)
                .queueName(azureQueueName)
                .buildAsyncClient();
    }

}
