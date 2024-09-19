package com.spring.boot.kafka.service.impl;



import com.spring.boot.kafka.constants.*;
import com.spring.boot.kafka.service.*;
import lombok.*;
import lombok.extern.log4j.*;
import org.springframework.kafka.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.stereotype.*;

@Service
@Log4j2
@AllArgsConstructor
public class KafkaServiceImpl implements KafkaService {


    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public boolean updateLocation(String location) {
        kafkaTemplate.send(Constants.LOCATION_UPDATE_TOPIC_NAME, location);
        log.info("Location '{}' published to the topic [{}]", location, Constants.LOCATION_UPDATE_TOPIC_NAME);
        return true;
    }

    @KafkaListener(topics = Constants.LOCATION_UPDATE_TOPIC_NAME, groupId = Constants.GROUP_ID)
    @Override
    public void updatedLocation(String value) {
        log.info("Consumer [{}] received location update: '{}' from topic [{}]",
                Constants.GROUP_ID, value, Constants.LOCATION_UPDATE_TOPIC_NAME);
    }

}
