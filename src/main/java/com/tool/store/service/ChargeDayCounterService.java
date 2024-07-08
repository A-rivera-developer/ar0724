package com.tool.store.service;

import com.tool.store.service.model.ToolChargeModel;

import java.time.LocalDate;

public interface ChargeDayCounterService {
    long getChargeDayCount(ToolChargeModel toolCharge, LocalDate checkoutDate, LocalDate dueDate);
}
