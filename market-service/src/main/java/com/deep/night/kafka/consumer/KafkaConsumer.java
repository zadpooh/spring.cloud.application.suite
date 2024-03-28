package com.deep.night.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {
    @KafkaListener(topics = "${kafka.my.push.topic.name}"
            , groupId = "${kafka.my.push.topic.group.name}"
            , containerFactory = "pushEntityKafkaListenerContainerFactory")
    public void listenWithHeaders(@Payload Map<String, Object> map,
                                  @Headers MessageHeaders messageHeaders) throws Exception {
        log.info("Received Message: {} headers: {}", map.toString(), messageHeaders);
    }
}
