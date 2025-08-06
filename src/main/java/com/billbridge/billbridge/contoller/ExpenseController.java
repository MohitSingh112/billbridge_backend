package com.billbridge.billbridge.contoller;

import com.billbridge.billbridge.model.Expense;
import com.billbridge.billbridge.model.User;
import com.billbridge.billbridge.services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping("/add")
    public ResponseEntity<Expense> addExpense(@RequestParam Long groupId,
                                              @RequestParam String description,
                                              @RequestParam Double amount,
                                              @RequestParam List<Long> owedUserIds,
                                              @AuthenticationPrincipal User paidBy) {
        return ResponseEntity.ok(
                expenseService.addExpense(groupId, description, amount, paidBy, owedUserIds)
        );
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<Expense>> getExpenses(@PathVariable Long groupId) {
        return ResponseEntity.ok(expenseService.getExpensesByGroup(groupId));
    }
}
