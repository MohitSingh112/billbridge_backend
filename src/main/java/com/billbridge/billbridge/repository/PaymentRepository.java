package com.billbridge.billbridge.repository;

import com.billbridge.billbridge.model.Group;
import com.billbridge.billbridge.model.Payment;
import com.billbridge.billbridge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByFromUserOrToUser(User fromUser, User toUser);

    List<Payment> findByPaidByOrPaidTo(User paidBy, User paidTo);

}
