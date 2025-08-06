package com.billbridge.billbridge.repository;

import com.billbridge.billbridge.model.Expense;
import com.billbridge.billbridge.model.Split;
import com.billbridge.billbridge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SplitRepository extends JpaRepository<Split, Long> {
    List<Split> findByExpense(Expense expense);
    List<Split> findByOwedBy(User user);
}
