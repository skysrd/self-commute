package com.skysrd.selfcommute.api.commute.service;

import com.skysrd.selfcommute.api.commute.domain.Commute;
import com.skysrd.selfcommute.api.commute.domain.CommuteResponse;
import com.skysrd.selfcommute.api.commute.domain.WeeklyCommuteResponse;
import com.skysrd.selfcommute.api.commute.repository.CommuteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommuteService {
    private final CommuteRepository commuteRepository;

    public void goToWork() {
        if (commuteRepository.findFirstByEndTimeIsNull() != null) {
            throw new RuntimeException("이미 출근했습니다.");
        }
        Commute commute = new Commute();
        commute.setStartTime(LocalDateTime.now());
        commuteRepository.save(commute);
    }

    public void offWork() {
        if (commuteRepository.findFirstByEndTimeIsNull() == null) {
            throw new RuntimeException("아직 출근하지 않았습니다.");
        }
        Commute commute = commuteRepository.findFirstByEndTimeIsNull();
        commute.setEndTime(LocalDateTime.now());
        commuteRepository.save(commute);
    }

    public WeeklyCommuteResponse weeklyReport() {
        //이번 주 월요일
        LocalDate monday = LocalDate.now().minusDays(LocalDate.now().getDayOfWeek().getValue() - 1);
        //이번 주 일요일
        LocalDate sunday = monday.plusDays(6);
        List<Commute> commutes = commuteRepository.findAllByStartTimeBetween(monday.atStartOfDay(), sunday.atTime(23, 59, 59));
        List<CommuteResponse> responses = commutes.stream().map(CommuteResponse::of).collect(Collectors.toList());
        LocalTime totalTime = LocalTime.of(0, 0, 0);
        for(CommuteResponse response : responses) {
            totalTime = totalTime.plusHours(response.getWorkTime().getHour()).plusMinutes(response.getWorkTime().getMinute());
        }
        return new WeeklyCommuteResponse(responses, totalTime);
    }
}
