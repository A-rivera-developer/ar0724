package com.tool.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChargeInfoDto {
    private long chargeDays;
    private double dailyRentalCharge;
    private double preDiscountCharge;
    private int discountPercent;
    private double discountAmount;
    private double finalCharge;
}
