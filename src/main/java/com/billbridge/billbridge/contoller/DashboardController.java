package com.billbridge.billbridge.contoller;

import com.billbridge.billbridge.dto.BalanceDTO;
import com.billbridge.billbridge.model.User;
import com.billbridge.billbridge.services.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<List<BalanceDTO>> getBalance(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(dashboardService.calculateUserBalances(user));
    }
}


