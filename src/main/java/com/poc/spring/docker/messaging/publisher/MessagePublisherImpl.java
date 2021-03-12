package com.poc.spring.docker.messaging.publisher;

import com.poc.spring.docker.configuration.KafkaConfig;
import com.poc.spring.docker.model.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessagePublisherImpl implements MessagePublisher {

    @Autowired
    private KafkaConfig kafkaConfig;


    @Override
    public void publishToAlert(Employee message) {
        MessageChannel messageChannel = kafkaConfig.outgoing();
        messageChannel.send(MessageBuilder.withPayload(message).build());

    }
}
