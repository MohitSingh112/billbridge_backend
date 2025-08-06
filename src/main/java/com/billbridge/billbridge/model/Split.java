package com.billbridge.billbridge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "splits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Split {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    // Expense to which this split belongs
    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;

    // User who owes this amount
    @ManyToOne
    @JoinColumn(name = "owed_by")
    private User owedBy;
}

