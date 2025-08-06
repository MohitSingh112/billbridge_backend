package com.billbridge.billbridge.services;

import com.billbridge.billbridge.model.Payment;
import com.billbridge.billbridge.model.User;
import com.billbridge.billbridge.repository.PaymentRepository;
import com.billbridge.billbridge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepo;
    private final UserRepository userRepo;

    public Payment settleUp(User paidBy, Long paidToUserId, Double amount) {
        User paidTo = userRepo.findById(paidToUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Payment payment = Payment.builder()
                .amount(amount)
                .fromUser(paidBy)
                .toUser(paidTo)
                .paidAt(LocalDateTime.now())
                .build();

        return paymentRepo.save(payment);
    }

    public List<Payment> getMyPayments(User user) {
        return paymentRepo.findByPaidByOrPaidTo(user, user);
    }
}

