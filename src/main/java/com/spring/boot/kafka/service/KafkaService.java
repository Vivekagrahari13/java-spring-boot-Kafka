package com.spring.boot.kafka.service;

public interface KafkaService {
    boolean updateLocation(String location);

    void updatedLocation(String value);
}
