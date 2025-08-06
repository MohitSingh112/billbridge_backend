package com.billbridge.billbridge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private LocalDateTime paidAt = LocalDateTime.now();

    // From user
    @ManyToOne
    @JoinColumn(name = "from_user")
    private User fromUser;

    // To user
    @ManyToOne
    @JoinColumn(name = "to_user")
    private User toUser;

    // Which group it belongs to
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
}
