package com.billbridge.billbridge.services;


import com.billbridge.billbridge.model.Expense;
import com.billbridge.billbridge.model.Group;
import com.billbridge.billbridge.model.Split;
import com.billbridge.billbridge.model.User;
import com.billbridge.billbridge.repository.ExpenseRepository;
import com.billbridge.billbridge.repository.GroupRepository;
import com.billbridge.billbridge.repository.SplitRepository;
import com.billbridge.billbridge.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepo;
    private final GroupRepository groupRepo;
    private final UserRepository userRepo;
    private final SplitRepository splitRepo;

    @Transactional
    public Expense addExpense(Long groupId, String description, Double amount,
                              User paidBy, List<Long> owedUserIds) {

        Group group = groupRepo.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        if (!group.getMembers().contains(paidBy)) {
            throw new RuntimeException("You are not a member of this group");
        }

        // Create expense
        Expense expense = new Expense();
        expense.setDescription(description);
        expense.setAmount(amount);
        expense.setPaidBy(paidBy);
        expense.setGroup(group);
        expense = expenseRepo.save(expense);

        // Equal split
        double splitAmount = amount / owedUserIds.size();
        List<Split> splits = new ArrayList<>();

        for (Long userId : owedUserIds) {
            User user = userRepo.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (!group.getMembers().contains(user)) {
                throw new RuntimeException("User not in group");
            }

            Split split = new Split();
            split.setAmount(splitAmount);
            split.setOwedBy(user);
            split.setExpense(expense);

            splits.add(split);
        }

        splitRepo.saveAll(splits);
        return expense;
    }

    public List<Expense> getExpensesByGroup(Long groupId) {
        Group group = groupRepo.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        return expenseRepo.findByGroup(group);
    }
}

