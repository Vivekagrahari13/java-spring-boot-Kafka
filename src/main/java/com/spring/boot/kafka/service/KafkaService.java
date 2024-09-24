package com.spring.boot.kafka.service;

public interface KafkaService {
    boolean updateLocation(String location);

    boolean updateWeather(String weatherData);

    boolean sendNotification(String notification);

    void updatedLocation(String value);
}
