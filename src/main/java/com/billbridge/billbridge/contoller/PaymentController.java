package com.billbridge.billbridge.contoller;

import com.billbridge.billbridge.model.Payment;
import com.billbridge.billbridge.model.User;
import com.billbridge.billbridge.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/settle")
    public ResponseEntity<Payment> settleUp(@AuthenticationPrincipal User paidBy,
                                            @RequestParam Long paidTo,
                                            @RequestParam Double amount) {
        return ResponseEntity.ok(paymentService.settleUp(paidBy, paidTo, amount));
    }
}

