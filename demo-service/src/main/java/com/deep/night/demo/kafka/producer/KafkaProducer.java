package com.deep.night.demo.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Map<String, Object>> kafkaTemplate;

    @Value("${kafka.my.push.topic.name}")
    private String topicName;

    public void send(Map<String, Object> map) {
        int partition = new Random().nextInt(10);

        Message<Map<String, Object>> message = MessageBuilder
                .withPayload(map)
                .setHeader(KafkaHeaders.KEY , RandomStringUtils.randomAlphanumeric(17).toLowerCase())
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .setHeader(KafkaHeaders.PARTITION, partition)
                .build();

        CompletableFuture<SendResult<String, Map<String, Object>>> future = kafkaTemplate.send(message);

        future.whenComplete((result, ex) -> {
            if(!ObjectUtils.isEmpty(ex)){
                log.info("Sent message=[{}] , with offset=[{}]", result.getProducerRecord().value().toString(), result.getRecordMetadata().offset());
                future.complete(result);
            } else {
                log.info("Unable to send message=[] due to : {}", ex.getMessage());
            }
        });
    }
}
