package com.spring.boot.kafka.repositories;

import com.spring.boot.kafka.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface LocationUpdateRepository extends JpaRepository<LocationUpdate, Long> {
}
