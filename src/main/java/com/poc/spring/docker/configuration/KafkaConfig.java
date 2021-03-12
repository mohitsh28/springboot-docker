package com.poc.spring.docker.configuration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaConfig {

    String POC = "poc";
    String ALERT = "alert";

    @Input(POC)
    SubscribableChannel incoming();

    @Output(ALERT)
    MessageChannel outgoing();
}
