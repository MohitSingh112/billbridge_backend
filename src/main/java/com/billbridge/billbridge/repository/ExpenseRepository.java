package com.billbridge.billbridge.repository;

import com.billbridge.billbridge.model.Expense;
import com.billbridge.billbridge.model.Group;
import com.billbridge.billbridge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByGroup(Group group);

    List<Expense> findByPaidBy(User paidBy);
}
