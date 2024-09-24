package com.spring.boot.kafka.service.impl;


import com.spring.boot.kafka.constants.*;
import com.spring.boot.kafka.entity.*;
import com.spring.boot.kafka.repositories.*;
import com.spring.boot.kafka.service.*;
import lombok.*;
import lombok.extern.log4j.*;
import org.springframework.kafka.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.stereotype.*;

import java.time.*;

@Service
@Log4j2
@AllArgsConstructor
public class KafkaServiceImpl implements KafkaService {


    private KafkaTemplate<String, String> kafkaTemplate;
    private final LocationUpdateRepository locationUpdateRepository;

    @Override
    public boolean updateLocation(String location) {
        kafkaTemplate.send(Constants.LOCATION_UPDATE_TOPIC_NAME, location);
        log.info("Location '{}' published to the topic [{}]", location, Constants.LOCATION_UPDATE_TOPIC_NAME);
        LocationUpdate locationUpdate = new LocationUpdate(null, location, LocalDateTime.now());
        locationUpdateRepository.save(locationUpdate);
        return true;
    }

    @Override
    public boolean updateWeather(String weatherData) {
        kafkaTemplate.send(Constants.WEATHER_UPDATE_TOPIC_NAME, weatherData);
        log.info("Weather '{}' published to the topic [{}]", weatherData, Constants.WEATHER_UPDATE_TOPIC_NAME);
        return true;
    }

    @Override
    public boolean sendNotification(String notification) {
        kafkaTemplate.send(Constants.NOTIFICATION_TOPIC_NAME, notification);
        log.info("Notification '{}' sent to the topic [{}]", notification, Constants.NOTIFICATION_TOPIC_NAME);
        return true;
    }

    //consumer
    @KafkaListener(topics = Constants.LOCATION_UPDATE_TOPIC_NAME, groupId = Constants.GROUP_ID)
    @Override
    public void updatedLocation(String value) {
        log.info("Consumer [{}] received location update: '{}' from topic [{}]",
                Constants.GROUP_ID, value, Constants.LOCATION_UPDATE_TOPIC_NAME);
    }

    @KafkaListener(topics = Constants.LOCATION_UPDATE_TOPIC_NAME, groupId = Constants.GROUP_ID_2)
    public void updatedLocationForGroup2(String value) {
        log.info("Consumer [{}] received location update: '{}' from topic [{}]",
                Constants.GROUP_ID_2, value, Constants.LOCATION_UPDATE_TOPIC_NAME);
    }

    @KafkaListener(topics = Constants.WEATHER_UPDATE_TOPIC_NAME, groupId = Constants.GROUP_ID)
    public void updatedWeather(String weatherData) {
        log.info("Consumer [{}] received weather update: '{}' from topic [{}]",
                Constants.GROUP_ID, weatherData, Constants.WEATHER_UPDATE_TOPIC_NAME);
    }

    @KafkaListener(topics = Constants.NOTIFICATION_TOPIC_NAME, groupId = Constants.GROUP_ID)
    public void receivedNotification(String notification) {
        log.info("Consumer [{}] received notification: '{}' from topic [{}]",
                Constants.GROUP_ID, notification, Constants.NOTIFICATION_TOPIC_NAME);
    }

}
