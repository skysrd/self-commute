package com.skysrd.selfcommute.api.commute.domain;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class CommuteResponse {
        private LocalDate date;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private LocalTime workTime;

        public static CommuteResponse of(Commute commute) {
            CommuteResponse response = new CommuteResponse();
            response.date = commute.getStartTime().toLocalDate();
            response.startTime = commute.getStartTime();
            response.endTime = commute.getEndTime();
            response.workTime = commute.getTotalTime();
            return response;
        }

}
