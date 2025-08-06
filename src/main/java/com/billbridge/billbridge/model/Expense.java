package com.billbridge.billbridge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "expenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Double amount;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Group to which the expense belongs
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    // User who paid
    @ManyToOne
    @JoinColumn(name = "paid_by")
    private User paidBy;

    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL)
    private List<Split> splits = new ArrayList<>();
}
