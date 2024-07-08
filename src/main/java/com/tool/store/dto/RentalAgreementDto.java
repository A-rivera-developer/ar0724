package com.tool.store.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RentalAgreementDto {
    private String toolCode;
    private String type;
    private String brand;

    private int rentalDays;
    private Date checkoutDate;
    private Date dueDate;
    private int chargeDays;

    private double dailyRentalCharge;
    private double preDiscountCharge;
    private double discountPercent;
    private double discountAmount;
    private double finalCharge;
}
