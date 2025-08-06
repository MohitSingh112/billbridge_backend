package com.billbridge.billbridge.services;

import com.billbridge.billbridge.dto.BalanceDTO;
import com.billbridge.billbridge.model.Expense;
import com.billbridge.billbridge.model.Payment;
import com.billbridge.billbridge.model.Split;
import com.billbridge.billbridge.model.User;
import com.billbridge.billbridge.repository.ExpenseRepository;
import com.billbridge.billbridge.repository.PaymentRepository;
import com.billbridge.billbridge.repository.SplitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final SplitRepository splitRepo;
    private final ExpenseRepository expenseRepo;
    private final PaymentRepository paymentRepo;

    public List<BalanceDTO> calculateUserBalances(User currentUser) {
        Map<User, Double> netBalance = new HashMap<>();

        // 1. From Splits → currentUser owes to others
        List<Split> splits = splitRepo.findByOwedBy(currentUser);
        for (Split split : splits) {
            User paidBy = split.getExpense().getPaidBy();
            if (!paidBy.equals(currentUser)) {
                netBalance.put(paidBy, netBalance.getOrDefault(paidBy, 0.0) - split.getAmount());
            }
        }

        // 2. From Expenses → others owe currentUser
        List<Expense> expenses = expenseRepo.findByPaidBy(currentUser);
        for (Expense expense : expenses) {
            for (Split split : expense.getSplits()) {
                User owedBy = split.getOwedBy();
                if (!owedBy.equals(currentUser)) {
                    netBalance.put(owedBy, netBalance.getOrDefault(owedBy, 0.0) + split.getAmount());
                }
            }
        }

        // 3. Adjust with Payments
        List<Payment> payments = paymentRepo.findByFromUserOrToUser(currentUser, currentUser);
        for (Payment payment : payments) {
            if (payment.getFromUser().equals(currentUser)) {
                netBalance.put(payment.getToUser(), netBalance.getOrDefault(payment.getToUser(), 0.0) + payment.getAmount());
            } else if (payment.getToUser().equals(currentUser)) {
                netBalance.put(payment.getFromUser(), netBalance.getOrDefault(payment.getFromUser(), 0.0) - payment.getAmount());
            }
        }

        // 4. Convert to DTO
        List<BalanceDTO> result = new ArrayList<>();
        for (Map.Entry<User, Double> entry : netBalance.entrySet()) {
            if (entry.getValue() > 0.01) {
                result.add(new BalanceDTO(entry.getKey().getName(), entry.getValue(), "owesYou"));
            } else if (entry.getValue() < -0.01) {
                result.add(new BalanceDTO(entry.getKey().getName(), Math.abs(entry.getValue()), "youOwe"));
            }
        }

        return result;
    }
}

