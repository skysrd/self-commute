package com.skysrd.selfcommute.api.commute.repository;

import com.skysrd.selfcommute.api.commute.domain.Commute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CommuteRepository extends JpaRepository<Commute, Long> {
    List<Commute> findAllByStartTimeBetween(LocalDateTime start, LocalDateTime end);

    Commute findFirstByEndTimeIsNull();
}
