package com.skysrd.selfcommute.api.commute.controller;

import com.skysrd.selfcommute.api.commute.service.CommuteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController("/api/commute")
@RequiredArgsConstructor
public class CommuteController {
    private final CommuteService commuteService;

    @PostMapping("/go-to-work")
    public HttpEntity<?> goToWork() {
        commuteService.goToWork();
        return ResponseEntity.ok(null);
    }

    @PostMapping("/off-work")
    public HttpEntity<?> offWork() {
        commuteService.offWork();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/weekly-report")
    public HttpEntity<?> weeklyReport() {
        return ResponseEntity.ok(commuteService.weeklyReport());
    }
}
