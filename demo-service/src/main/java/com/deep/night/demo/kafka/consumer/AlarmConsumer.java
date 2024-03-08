package com.deep.night.demo.kafka.consumer;

import com.deep.night.demo.kafka.producer.AlarmProducer;
import com.deep.night.demo.vo.AlarmEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

//@Slf4j
//@Component
//@RequiredArgsConstructor
public class AlarmConsumer {

//    @KafkaListener(topics = "${kafka.my.push.topic.name}"
//            , groupId = "${kafka.my.push.topic.group.name}"
//            , containerFactory = "pushEntityKafkaListenerContainerFactory")
//    public void listenWithHeaders(@Payload AlarmEvent alarmEvent,
//                                  @Headers MessageHeaders messageHeaders) {
//        log.info("Received Message: {} headers: {}",alarmEvent.toString(), messageHeaders);
//    }
}
