package com.tool.store.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChargeInfoModel {
    private long chargeDays;
    private double dailyRentalCharge;
    private int discountPercent;
    private double preDiscountCharge;
    private double discountAmount;
    private double finalCharge;
}
