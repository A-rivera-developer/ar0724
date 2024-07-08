package com.tool.store.service.impl;

import com.tool.store.service.model.ChargeInfoModel;
import org.springframework.stereotype.Service;

@Service
public class ChargeCalculatorService {
    public ChargeInfoModel calculateCharges(long days, double dailyCharge,  int discountPercent) {
        double preDiscountCharge = dailyCharge * days;
        double discount = getDiscount(discountPercent, preDiscountCharge);
        double finalCharge = preDiscountCharge - discount;

        return new ChargeInfoModel(days, dailyCharge, discountPercent, preDiscountCharge, discount,finalCharge);
    }

    private static double getDiscount(int discountPercent, double preDiscountCharge) {
        if(discountPercent > 0) {
            return preDiscountCharge * ((double) discountPercent / 100);
        }
        return 0;
    }
}
