package com.poc.spring.docker.messaging.consumer;

import com.poc.spring.docker.model.domain.Employee;
import org.springframework.messaging.handler.annotation.Payload;

public interface MessageConsumer {


    void processMessage(@Payload org.springframework.messaging.Message<Employee> message);
}
