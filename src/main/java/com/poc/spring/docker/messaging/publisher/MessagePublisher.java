package com.poc.spring.docker.messaging.publisher;

import com.poc.spring.docker.model.domain.Employee;

public interface MessagePublisher {


    void publishToAlert(Employee message);

}
