package com.billbridge.billbridge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BalanceDTO {
    private String user;
    private double amount;
    private String direction; // "youOwe" or "owesYou"
}

