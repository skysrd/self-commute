package com.skysrd.selfcommute.api.commute.domain;

import com.skysrd.selfcommute.api.commute.domain.enums.CommuteType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Commute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    public LocalTime getTotalTime() {
        LocalTime totalTime = LocalTime.of(0, 0);
        if (startTime != null && endTime != null) {
            totalTime = LocalTime.of(endTime.toLocalTime().getHour() - startTime.toLocalTime().getHour(),
                    endTime.toLocalTime().getMinute() - startTime.toLocalTime().getMinute());
        }
        return totalTime;
    }
}