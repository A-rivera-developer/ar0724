package com.tool.store.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChargeInfoModel {
    private long chargeDays;
    private double dailyRentalCharge;
    private double discountPercent;
    private double preDiscountCharge;
    private double discountAmount;
    private double finalCharge;
}
