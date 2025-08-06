package com.billbridge.billbridge.dto;

import lombok.Data;

import java.util.Map;

@Data
public class CustomSplitRequest {
    private Map<Long, Double> splits;
}
