package com.tool.store.service.impl;

import com.tool.store.service.model.ChargeInfoModel;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

@Service
public class ChargeCalculatorServiceImpl implements com.tool.store.service.ChargeCalculatorService {

    private static double getDiscount(int discountPercent, double preDiscountCharge) {
        if (discountPercent > 0) {
            return preDiscountCharge * (discountPercent / 100.00);
        }
        return 0;
    }

    @Override
    public ChargeInfoModel calculateCharges(long days, double dailyCharge, int discountPercent) {
        double preDiscountCharge = dailyCharge * days;
        double discount = getDiscount(discountPercent, preDiscountCharge);
        double finalCharge = preDiscountCharge - discount;

        preDiscountCharge = Precision.round(preDiscountCharge, 2);
        discount = Precision.round(discount, 2);
        finalCharge = Precision.round(finalCharge, 2);

        return new ChargeInfoModel(days, dailyCharge, discountPercent, preDiscountCharge, discount, finalCharge);
    }
}
