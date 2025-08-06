package com.billbridge.billbridge.contoller;

import com.billbridge.billbridge.dto.CustomSplitRequest;
import com.billbridge.billbridge.model.Expense;
import com.billbridge.billbridge.model.User;
import com.billbridge.billbridge.services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/custom")
    public ResponseEntity<Expense> addCustomSplitExpense(@RequestParam Long groupId,
                                                         @RequestParam String description,
                                                         @RequestParam Double amount,
                                                         @RequestBody CustomSplitRequest request,
                                                         @AuthenticationPrincipal User paidBy) {
        return ResponseEntity.ok(
                expenseService.addCustomSplitExpense(groupId, description, amount, paidBy, request.getSplits())
        );
    }

}
