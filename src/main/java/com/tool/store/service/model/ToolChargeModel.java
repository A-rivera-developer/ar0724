package com.tool.store.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ToolChargeModel {
    private String type;
    private double dailyCharge;
    private boolean chargeWeekdays;
    private boolean chargeWeekends;
    private boolean chargeHolidays;
}
