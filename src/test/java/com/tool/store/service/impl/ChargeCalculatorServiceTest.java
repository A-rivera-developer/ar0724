package com.tool.store.service.impl;

import com.tool.store.service.model.ChargeInfoModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ChargeCalculatorServiceTest {
    @InjectMocks
    private ChargeCalculatorService sut;

    @Test
    public void shouldCalculatePreDiscountCharge(){
        final double EXPECTED_CHARGE = 10;
        ChargeInfoModel result = sut.calculateCharges(10L, 1.00, 0);

        assertEquals(EXPECTED_CHARGE, result.getPreDiscountCharge());
    }

    @Test
    public void shouldCalculateDiscount() {
        final double EXPECTED_DISCOUNT = 1;
        ChargeInfoModel result = sut.calculateCharges(10L, 1.00, 10);

        assertEquals(EXPECTED_DISCOUNT, result.getDiscountAmount());
    }

    @Test
    public void shouldCalculateFinalCharge() {
        final double EXPECTED_CHARGE = 9;
        ChargeInfoModel result = sut.calculateCharges(10L, 1.00, 10);

        assertEquals(EXPECTED_CHARGE, result.getFinalCharge());
    }

    @Test
    public void shouldHaveCalculationFieldsInfo() {
        final double EXPECTED_PRE_DISCOUNT_CHARGE = 10;
        final double EXPECTED_CHARGE = 9;
        final long days = 10;
        final double dailyCharge = 1;
        final int discountPercent = 10;
        ChargeInfoModel result = sut.calculateCharges(days, dailyCharge, discountPercent);

        assertEquals(EXPECTED_PRE_DISCOUNT_CHARGE, result.getPreDiscountCharge());
        assertEquals(EXPECTED_CHARGE, result.getFinalCharge());
        assertEquals(days, result.getChargeDays());
        assertEquals(dailyCharge, result.getDailyRentalCharge());
        assertEquals(discountPercent, result.getDiscountPercent());
    }

}