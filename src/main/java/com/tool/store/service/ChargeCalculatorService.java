package com.tool.store.service;

import com.tool.store.service.model.ChargeInfoModel;

public interface ChargeCalculatorService {
    ChargeInfoModel calculateCharges(long days, double dailyCharge, int discountPercent);
}
