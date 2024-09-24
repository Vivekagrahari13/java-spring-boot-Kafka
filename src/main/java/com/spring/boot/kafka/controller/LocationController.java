package com.spring.boot.kafka.controller;


import com.spring.boot.kafka.service.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/location")
public class LocationController {

    private final KafkaService kafkaService;

    @PostMapping("/update")
    public ResponseEntity<?> updateLocation() {
        kafkaService.updateLocation("(" + Math.random() * 100 + ", " + Math.random() * 100 + ")");
        return new ResponseEntity<>(Map.of("message", "Location Updated"), HttpStatus.OK);
    }

    @PostMapping("/weather/update")
    public ResponseEntity<?> updateWeather() {
        kafkaService.updateWeather("Weather: " + Math.random() * 100 + "°C");
        return new ResponseEntity<>(Map.of("message", "Weather Updated"), HttpStatus.OK);
    }

    @PostMapping("/notification/send")
    public ResponseEntity<?> sendNotification() {
        kafkaService.sendNotification("Notification: " + UUID.randomUUID().toString());
        return new ResponseEntity<>(Map.of("message", "Notification Sent"), HttpStatus.OK);
    }
}
