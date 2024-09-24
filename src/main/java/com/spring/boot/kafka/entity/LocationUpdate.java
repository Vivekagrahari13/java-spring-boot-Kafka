package com.spring.boot.kafka.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Entity
@Table(name = "location_updates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;

    @Column(name = "received_at")
    private LocalDateTime receivedAt;
}
