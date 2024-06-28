package com.skysrd.selfcommute.api.commute.domain;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class CommuteReseponse {
        private LocalDate date;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private LocalDateTime workTime;

        public CommuteResponse of(Commute commute) {
            CommuteResponse response = new CommuteResponse();
            response.date = commute.getStartTime().toLocalDate();
            response.startTime = commute.getStartTime();
            response.endTime = commute.getEndTime();
            response.workTime = commute.getEndTime().minusHours(commute.getStartTime().getHour()).minusMinutes(commute.getStartTime().getMinute());
            return response;
        }
    
}
