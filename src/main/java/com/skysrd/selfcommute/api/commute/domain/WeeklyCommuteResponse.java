package com.skysrd.selfcommute.api.commute.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
public class WeeklyCommuteResponse {
    List<CommuteResponse> contents;
    LocalTime totalTime;

    public WeeklyCommuteResponse(List<CommuteResponse> contents, LocalTime totalTime) {
        this.contents = contents;
        this.totalTime = totalTime;
    }

}
