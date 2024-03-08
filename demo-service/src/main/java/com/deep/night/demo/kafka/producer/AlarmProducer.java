package com.deep.night.demo.kafka.producer;

import com.deep.night.demo.vo.AlarmEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

//@Slf4j
//@Component
//@RequiredArgsConstructor
public class AlarmProducer {

//    @Autowired
//    private KafkaTemplate<String, AlarmEvent> kafkaTemplate;
//
//    @Value("${kafka.my.push.topic.name}")
//    private String topicName;

//    public void send(AlarmEvent alarmEvent) {
//        kafkaTemplate.send(topicName, alarmEvent);
//
//        int partitionId = new Random().nextInt(10);
//
//        kafkaTemplate.send(topicName, partitionId, RandomStringUtils.randomAlphanumeric(17).toLowerCase() , alarmEvent);
//        Message<AlarmEvent> message = MessageBuilder
//                .withPayload(alarmEvent)
//                .setHeader(KafkaHeaders.TOPIC, topicName)
//                .setHeader(KafkaHeaders.PARTITION, partition)
//                .build();
//
//        CompletableFuture<SendResult<String, AlarmEvent>> future = kafkaTemplate.send(message);
//
//
//
//        future.whenComplete((result, ex) -> {
//            if(!ObjectUtils.isEmpty(ex)){
//                log.info("Sent message=[{}] , with offset=[{}]", result.getProducerRecord().value().toString(), result.getRecordMetadata().offset());
//                future.complete(result);
//            } else {
//                log.info("Unable to send message=[] due to : {}", ex.getMessage());
//            }
//        });
//    }
}
