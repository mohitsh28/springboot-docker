package com.poc.spring.docker.messaging.consumer;

import com.poc.spring.docker.configuration.KafkaConfig;
import com.poc.spring.docker.model.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableBinding(KafkaConfig.class)
public class MessageConsumerImpl implements MessageConsumer {

    @Override
    @StreamListener(KafkaConfig.POC)
    public void processMessage(@Payload org.springframework.messaging.Message<Employee> message) {
        Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT,Acknowledgment.class);
        try {

        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }
}


