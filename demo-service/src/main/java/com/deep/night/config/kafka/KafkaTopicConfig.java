package com.deep.night.config.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

//@Configuration
//@Slf4j
public class KafkaTopicConfig {

//    @Value("${kafka.bootstrapAddress}")
//    private String bootstrapServers;
//
//    @Value("${kafka.my.push.topic.name}")
//    private String topicName;
//
//    @Bean
//    public KafkaAdmin kafkaAdmin() {
//        Map<String, Object> configs = new HashMap<>();
//        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        return new KafkaAdmin(configs);
//    }
//
//    @Bean
//    public NewTopic newTopic() {
//        return new NewTopic(topicName, 10, (short) 10);
//    }
}
